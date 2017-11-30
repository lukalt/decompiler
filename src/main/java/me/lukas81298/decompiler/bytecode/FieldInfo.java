package me.lukas81298.decompiler.bytecode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.atrr.AttributeInfo;
import me.lukas81298.decompiler.bytecode.constant.ConstantUtf8Info;

import java.io.DataInput;
import java.io.IOException;
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

    private final Set<ClassFlag> flags;
    private final String name;
    private final String type;
    private final AttributeInfo[] attributes;

    public boolean isSynthetic() {
        return this.flags.contains(ClassFlag.ACC_SYNTHETIC);
    }

    public boolean isFinal() {
        return this.flags.contains(ClassFlag.ACC_FINAL);
    }

    public static FieldInfo read(ConstantPool constantPool, DataInput input) throws IOException {
        Set<ClassFlag> flags = ClassFlag.fromBitMask(input.readUnsignedShort());
        String name = constantPool.get(input.readUnsignedShort(), ConstantUtf8Info.class).getValue();
        String descriptor = constantPool.get(input.readUnsignedShort(), ConstantUtf8Info.class).getValue();
        AttributeInfo[] attr = new AttributeInfo[input.readUnsignedShort()];
        for(int i = 0; i < attr.length; i++) {
            attr[i] = AttributeInfo.read(constantPool,input);
        }
        return new FieldInfo(flags, name, descriptor, attr);
    }

}