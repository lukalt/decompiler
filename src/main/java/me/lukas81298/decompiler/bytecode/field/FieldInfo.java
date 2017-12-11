package me.lukas81298.decompiler.bytecode.field;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.atrr.AttributeInfo;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;
import me.lukas81298.decompiler.bytecode.atrr.impl.SignatureAttribute;
import me.lukas81298.decompiler.bytecode.constant.ConstantUtf8Info;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.util.AttributeCollections;
import org.w3c.dom.Attr;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class FieldInfo {

    private final List<FieldFlag> flags;
    private final String name;
    private final String type;
    private final AttributeInfo[] attributes;

    public boolean isSynthetic() {
        return this.flags.contains(FieldFlag.ACC_SYNTHETIC);
    }

    public boolean isFinal() {
        return this.flags.contains(FieldFlag.ACC_FINAL);
    }

    public static FieldInfo read(ConstantPool constantPool, DataInput input, ClassFile classFile) throws IOException {
        List<FieldFlag> flags = FieldFlag.fromBitMask(input.readUnsignedShort());
        String name = constantPool.get(input.readUnsignedShort(), ConstantUtf8Info.class).getValue();
        String descriptor = MethodDescriptor.parseType(constantPool.get(input.readUnsignedShort(), ConstantUtf8Info.class).getValue(), classFile);
        AttributeInfo[] attr = new AttributeInfo[input.readUnsignedShort()];
        for(int i = 0; i < attr.length; i++) {
            attr[i] = AttributeInfo.read(constantPool,input);
        }
        return new FieldInfo(flags, name, descriptor, attr);
    }

    public String getSignature(ClassFile classFile) {
        StringBuilder sb = new StringBuilder();
        for(FieldFlag flag : this.flags) {
            sb.append(flag.getName()).append(" ");
        }
        sb.append(type);
        SignatureAttribute signatureAttribute = AttributeCollections.getAttributeByType(this.attributes, AttributeType.SIGNATURE, SignatureAttribute.class);
        if(signatureAttribute != null) {
            sb.append(signatureAttribute.getFieldGenericString(classFile));
        }
        sb.append(" ").append(name).append("");
        return sb.toString();
    }
}
