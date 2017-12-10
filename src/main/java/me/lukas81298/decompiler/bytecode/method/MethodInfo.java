package me.lukas81298.decompiler.bytecode.method;

import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeInfo;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;
import me.lukas81298.decompiler.bytecode.atrr.impl.CodeAttribute;
import me.lukas81298.decompiler.bytecode.atrr.impl.ExceptionsAttribute;
import me.lukas81298.decompiler.bytecode.atrr.impl.LocalVariableAttribute;
import me.lukas81298.decompiler.bytecode.constant.ConstantUtf8Info;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.BlockProcessor;
import me.lukas81298.decompiler.util.IndentedPrintWriter;
import me.lukas81298.decompiler.util.ProcessQueue;
import me.lukas81298.decompiler.util.VariableStorage;

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
        AttributeInfo[] attr = new AttributeInfo[input.readUnsignedShort()];
        for(int i = 0; i < attr.length; i++) {
            attr[i] = AttributeInfo.read(constantPool, input);
        }
        return new MethodInfo(flags, name, descriptor, attr, parent);
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

    public String getSignature() {
        StringBuilder sb = new StringBuilder();
        for(MethodFlag flag : this.flags) {
            if(flag.getName() != null) {
                sb.append(flag.getName()).append(" ");
            }
        }
        MethodDescriptor descriptor = MethodDescriptor.parse(this.descriptor, this.classFile);

        MethodType methodType = MethodType.byName(this.name);
        switch(methodType) {
            case METHOD:
                sb.append(descriptor.getReturnType()).append(" ").append(this.name);
                break;
            case CONSTRUCTOR:
                sb.append(classFile.getName());
                break;

        }
        if(methodType != MethodType.STATIC_CONSTRUCTOR) {
            sb.append("(");
            int i = methodType == MethodType.CONSTRUCTOR ? 1 : 0;
            CodeAttribute codeAttribute = Objects.requireNonNull(getAttributeByType(AttributeType.CODE, CodeAttribute.class));
            LocalVariableAttribute localVariableAttribute = codeAttribute.getAttributeByType(AttributeType.LOCAL_VARIABLE_TABLE, LocalVariableAttribute.class);
            for(String s : descriptor.getArgumentTypes()) {
                if(i > (methodType == MethodType.CONSTRUCTOR ? 1 : 0)) {
                    sb.append(", ");
                }
                sb.append(s).append(" ");
                LocalVariableAttribute.LocalVariable l;
                if(localVariableAttribute != null && (l = localVariableAttribute.getLocalVariables().get(i)) != null) {
                    sb.append(l.getName());
                } else {
                    sb.append("arg").append(i);
                }
                i++;
            }
            sb.append(")");
            ExceptionsAttribute exceptionsAttribute = codeAttribute.getAttributeByType(AttributeType.EXCEPTIONS, ExceptionsAttribute.class);
            if(exceptionsAttribute != null && exceptionsAttribute.getExceptionTable().length > 0) {
                sb.append(" throws ").append(String.join(", ", Arrays.stream(exceptionsAttribute.getExceptionTable()).map(e -> {
                    return MethodDescriptor.makeClassName(e.getName());
                }).collect(Collectors.toList())));
            }
        } else {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    private <K extends AttributeData> K getAttributeByType(AttributeType attributeType, Class<K> clazz) {
        for(AttributeInfo attribute : this.attributes) {
            if(attribute.getType() == attributeType) {
                return clazz.cast(attribute.getData());
            }
        }
        return null;
    }

    private void writeBody(IndentedPrintWriter output, int i, ConstantPool constantPool) {
        CodeAttribute codeAttribute = Objects.requireNonNull(getAttributeByType(AttributeType.CODE, CodeAttribute.class));
        LocalVariableAttribute localVariableAttribute = codeAttribute.getAttributeByType(AttributeType.LOCAL_VARIABLE_TABLE, LocalVariableAttribute.class);
        ProcessQueue<CodeAttribute.CodeItem> queue = new ProcessQueue<>(codeAttribute.getCode());
        Block block = Block.newBlock(classFile, i, output, constantPool, queue, localVariableAttribute == null ? new TIntObjectHashMap<>() : localVariableAttribute.getLocalVariables());
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
            block.getVariables().set(j, refName, VariableStorage.PrimitiveType.OBJECT);
        }
        BlockProcessor blockProcessor = new BlockProcessor(block);
        try {
            blockProcessor.processBlock();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    public void write(IndentedPrintWriter output, int i, ConstantPool constantPool) {
        output.println(this.getSignature() + " {", i);
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
