package me.lukas81298.decompiler.bytecode.constant;

import com.google.common.base.Charsets;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.util.BiFunctionException;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
public enum ConstantType {

    CLASS(7, (in, c) -> {
        return new ConstantClassInfo(c, in.readUnsignedShort());
    }),
    FIELD_REF(9, (in, c) -> {
        return new ConstantFieldRefInfo(c, in.readUnsignedShort(), in.readUnsignedShort());
    }),
    METHOD_REF(10, (in, c) -> {
        return new ConstantMethodRefInfo(c, in.readUnsignedShort(), in.readUnsignedShort());
    }),
    INTERFACE_METHOD_REF(11, (in, c) -> {
        return new ConstantInterfaceMethodRefInfo(c, in.readUnsignedShort(), in.readUnsignedShort());
    }),
    STRING(8, (in, c) -> {
        return new ConstantString(c, in.readUnsignedShort());
    }),
    INTEGER(3, (in, c) -> {
        return new ConstantInteger(c, in.readInt());
    }),
    FLOAT(4, (in, c) -> {
        return new ConstantFloat(c, in.readFloat());
    }),
    LONG(5, (in, c) -> {
        return new ConstantLongInfo(c, in.readLong());
    }),
    DOUBLE(6, (in, c) -> {
        return new ConstantDouble(c, in.readDouble());
    }),
    NAME_AND_TYPE(12, (in, c) -> {
        return new ConstantNameAndType(c, in.readUnsignedShort(), in.readUnsignedShort());
    }),
    UTF_8(1, (in, c) -> {
        int length = in.readShort();
        byte[] bytes = new byte[length];
        in.readFully(bytes);
        String value = new String(bytes, Charsets.UTF_8);
        return new ConstantUtf8Info(c, value);
    }),
    METHOD_HANDLE(15, (in, c) -> {
        return new ConstantMethodHandle(c, in.readUnsignedByte(), in.readUnsignedShort());
    }),
    METHOD_TYPE(16, (in, c) -> {
        return new ConstantMethodTypeInfo(c, in.readUnsignedShort());
    }),
    INVOKE_DYNAMIC(18, (in, c) -> {
        return new ConstantInvokeDynamicInfo(c, in.readUnsignedShort(), in.readUnsignedShort());
    });

    private final int id;
    private final BiFunctionException<DataInput, ConstantPool, Constant, IOException> readFunction;

    private final static TIntObjectMap<ConstantType> index = new TIntObjectHashMap<>();

    static {
        for(ConstantType constantType : values()) {
            index.put(constantType.getId(), constantType);
        }
    }

    public static Constant readSingleConstant(DataInput input, ConstantPool constants) throws IOException {
        int i = input.readUnsignedByte();
        ConstantType type = index.get(i);
        if(type == null) {
            throw new RuntimeException("Invalid constant pool type " + i);
        }
        return type.readFunction.apply(input, constants);
    }
}
