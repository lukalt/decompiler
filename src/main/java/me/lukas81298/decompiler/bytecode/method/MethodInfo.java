package me.lukas81298.decompiler.bytecode.method;

import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.atrr.AttributeInfo;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;
import me.lukas81298.decompiler.bytecode.atrr.impl.*;
import me.lukas81298.decompiler.bytecode.constant.ConstantUtf8Info;
import me.lukas81298.decompiler.instruction.BlockProcessor;
import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.util.AttributeCollections;
import me.lukas81298.decompiler.util.IndentedPrintWriter;
import me.lukas81298.decompiler.util.ProcessQueue;
import me.lukas81298.decompiler.util.VariableStorage;
import org.apache.commons.lang3.StringUtils;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lukas
 * @since 29.11.2017
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodInfo {

    public static MethodInfo read(ConstantPool constantPool, DataInput input, ClassFile parent) throws IOException {
        List<MethodFlag> flags = MethodFlag.fromBitMask(input.readUnsignedShort());
        String name = constantPool.get(input.readUnsignedShort(), ConstantUtf8Info.class).getValue();
        String descriptor = constantPool.get(input.readUnsignedShort(), ConstantUtf8Info.class).getValue();
        return new MethodInfo(flags, name, descriptor, AttributeCollections.readAttributeArray(input, constantPool), parent);
    }

    private final List<MethodFlag> flags;
    private final String name;
    private final String descriptor;
    private final AttributeInfo[] attributes;
    private final ClassFile classFile;

    public boolean isSynthetic() {
        return this.flags.contains(MethodFlag.ACC_SYNTHETIC);
    }

    public boolean isFinal() {
        return this.flags.contains(MethodFlag.ACC_FINAL);
    }

    public boolean isDeprecated() {
        return AttributeCollections.getAttributeByType(this.attributes, AttributeType.DEPRECATED, DeprecatedAttribute.class) != null;
    }

    public String getSignature(ClassFile classFile) {
        StringBuilder sb = new StringBuilder();
        SignatureAttribute signatureAttribute = AttributeCollections.getAttributeByType(attributes, AttributeType.SIGNATURE, SignatureAttribute.class);
        String sig = signatureAttribute != null && !signatureAttribute.getSignature().isEmpty() ? signatureAttribute.getSignature() : this.descriptor;
    //    System.out.println(sig);
        for(MethodFlag flag : this.flags) {
            if(flag.getName() != null) {
                sb.append(flag.getName()).append(" ");
            }
        }
        MethodDescriptor descriptor = MethodDescriptor.parse(sig, this.classFile);

        MethodType methodType = MethodType.byName(this.name);
        switch(methodType) {
            case METHOD:
                sb.append(MethodDescriptor.makeClassName(descriptor.getReturnType(),classFile)).append(" ").append(this.name);
                break;
            case CONSTRUCTOR:
                sb.append(classFile.getName());
                break;

        }
        if(methodType != MethodType.STATIC_CONSTRUCTOR) {
            sb.append("(");
            int i = flags.contains(MethodFlag.ACC_STATIC) ? 0 : 1;
            CodeAttribute codeAttribute = Objects.requireNonNull(AttributeCollections.getAttributeByType(this.attributes, AttributeType.CODE, CodeAttribute.class));
            LocalVariableAttribute localVariableAttribute = codeAttribute.getAttributeByType(AttributeType.LOCAL_VARIABLE_TABLE, LocalVariableAttribute.class);
            int j = 0;
            for(String s : descriptor.getArgumentTypes()) {
                if(i > (flags.contains(MethodFlag.ACC_STATIC) ? 0 : 1)) {
                    sb.append(", ");
                }
                if(j == descriptor.getArgumentTypes().length - 1 && flags.contains(MethodFlag.ACC_VARARGS) && s.endsWith("[]")) {
                    sb.append(StringUtils.substringBeforeLast(s, "[]")).append("...");
                } else {
                    sb.append(s);
                }
                sb.append(" ");
                LocalVariableAttribute.LocalVariable l;
                if(localVariableAttribute != null && (l = localVariableAttribute.getLocalVariables().get(i)) != null) {
                    sb.append(l.getName());
                } else {
                    sb.append("arg").append(i);
                }
                i++;
                j++;
            }
            sb.append(")");
            ExceptionsAttribute exceptionsAttribute = codeAttribute.getAttributeByType(AttributeType.EXCEPTIONS, ExceptionsAttribute.class);
            if(exceptionsAttribute != null && exceptionsAttribute.getExceptionTable().length > 0) {
                sb.append(" throws ").append(String.join(", ", Arrays.stream(exceptionsAttribute.getExceptionTable()).map(e -> {
                    return MethodDescriptor.makeClassName(e.getName(), classFile);
                }).collect(Collectors.toList())));
            }
        } else {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    private void writeBody(IndentedPrintWriter output, int i, ConstantPool constantPool) {
        CodeAttribute codeAttribute = Objects.requireNonNull(AttributeCollections.getAttributeByType(this.attributes, AttributeType.CODE, CodeAttribute.class));
        LocalVariableAttribute localVariableAttribute = codeAttribute.getAttributeByType(AttributeType.LOCAL_VARIABLE_TABLE, LocalVariableAttribute.class);
        ProcessQueue<CodeAttribute.CodeItem> queue = new ProcessQueue<>(codeAttribute.getCode(), size -> new CodeAttribute.CodeItem[size]);
        Context context = Context.createContext(this.classFile, i, output, constantPool, queue, localVariableAttribute == null ? new TIntObjectHashMap<>() : localVariableAttribute.getLocalVariables());
        MethodDescriptor descriptor = MethodDescriptor.parse(this.descriptor, this.classFile);
        // init variable map with method attributes
        for(int j = 1; j <= descriptor.getArgumentTypes().length; j++) {
            String refName = "par" + j;
            if(localVariableAttribute != null) {
                LocalVariableAttribute.LocalVariable localVariable;
                if((localVariable = localVariableAttribute.getLocalVariables().get(j)) != null) {
                    refName = localVariable.getName();
                }
            }
            context.getVariables().set(j, refName, VariableStorage.PrimitiveType.OBJECT);
            context.getDefinedVariables().add(j);
        }
        BlockProcessor blockProcessor = new BlockProcessor(context);
        blockProcessor.processBlock();
    }

    public void write(IndentedPrintWriter output, int i, ConstantPool constantPool) {
        if(isDeprecated()) {
            output.println("@Deprecated", i);
        }
        output.println(this.getSignature(classFile) + " {", i);
        try {
            writeBody(output, i + 1, constantPool);
        } catch(Throwable t) {
            t.printStackTrace();
            output.println("// Exception: " + t.toString(), i + 1);
        }
        output.println("}", i);
        output.println();
    }
}
