package me.lukas81298.decompiler.bytecode.atrr;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.constant.ConstantUtf8Info;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author lukas
 * @since 29.11.2017
 */
@ToString
@Getter
@RequiredArgsConstructor
public class AttributeInfo {

    private final AttributeType type;
    private final AttributeData data;

    public static AttributeInfo read(ConstantPool constantPool, DataInput input) throws IOException {
        String name = constantPool.get(input.readUnsignedShort(), ConstantUtf8Info.class).getValue();
        AttributeType type = AttributeType.byName(name);
        byte[] data = new byte[input.readInt()];
        input.readFully(data);
        if(type.getApplyFunction() == null) { // not implemented yet
            return new AttributeInfo(type, null);
        }
        return new AttributeInfo(type,type.getApplyFunction().apply(new DataInputStream(new ByteArrayInputStream(data)), constantPool));
    }

}
