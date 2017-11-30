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
 * @since 29.11.2017
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodInfo {

    private final Set<MethodFlag> flags;
    private final String name;
    private final String descriptor;
    private final AttributeInfo[] attributes;

    public boolean isSynthetic() {
        return this.flags.contains(MethodFlag.ACC_SYNTHETIC);
    }

    public boolean isFinal() {
        return this.flags.contains(MethodFlag.ACC_FINAL);
    }

    public String getSignature() {
        StringBuilder sb = new StringBuilder();
        for(MethodFlag flag : this.flags) {

        }
        return sb.toString();
    }

    public static MethodInfo read(ConstantPool constantPool, DataInput input) throws IOException {
        Set<MethodFlag> flags = MethodFlag.fromBitMask(input.readUnsignedShort());
        String name = constantPool.get(input.readUnsignedShort(), ConstantUtf8Info.class).getValue();
        String descriptor = constantPool.get(input.readUnsignedShort(), ConstantUtf8Info.class).getValue();
        AttributeInfo[] attr = new AttributeInfo[input.readUnsignedShort()];
        for(int i = 0; i < attr.length; i++) {
            attr[i] = AttributeInfo.read(constantPool,input);
        }
        return new MethodInfo(flags, name, descriptor, attr);
    }
}
