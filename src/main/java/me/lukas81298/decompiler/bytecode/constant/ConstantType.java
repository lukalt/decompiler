package me.lukas81298.decompiler.bytecode.constant;

import com.google.common.base.Charsets;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.util.SneakyThrow;

import java.io.DataInput;
import java.io.IOException;
import java.util.function.BiFunction;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
public enum ConstantType {

    CLASS(7, (in,c) -> {
        try {
            return new ConstantClassInfo(c[in.readUnsignedShort()].toString());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    FIELD_REF(9, (in,c) -> {
        try {
            return new ConstantFieldRefInfo(c[in.readUnsignedShort()].toString(),c[in.readUnsignedShort()].toString());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    METHOD_REF(10, (in,c) -> {
        try {
            return new ConstantMethodRefInfo(c[in.readUnsignedShort()].toString(),c[in.readUnsignedShort()].toString());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    INTERFACE_METHOD_REF(11, (in,c) -> {
        try {
            return new ConstantInterfaceMethodRefInfo(c[in.readUnsignedShort()].toString(),c[in.readUnsignedShort()].toString());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    STRING(8, (in,c) -> {
        try {
            return new ConstantString(c[in.readUnsignedShort()].toString());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    INTEGER(3, (in,c) -> {
        try {
            return new ConstantInteger(in.readInt());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    FLOAT(4, (in,c) -> {
        try {
            return new ConstantFloat(in.readFloat());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    LONG(5, (in,c) -> {
        try {
            return new ConstantLongInfo(in.readLong());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    DOUBLE(6, (in,c) -> {
        try {
            return new ConstantDouble(in.readDouble());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    NAME_AND_TYPE(12, (in,c) -> {
        try {
            return new ConstantNameAndType(c[in.readUnsignedShort()].toString(), c[in.readUnsignedShort()].toString());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    UTF_8(1, (in,c) -> {
        try {
            int length = in.readInt();
            byte[] bytes = new byte[length];
            in.readFully(bytes);
            return new ConstantUtf8Info(new String(bytes, Charsets.UTF_8));
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    METHOD_HANDLE(15, (in,c) -> {
        try {
            return new ConstantMethodHandle(in.readUnsignedByte(), in.readUnsignedShort());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    METHOD_TYPE(16, (in,c) -> {
        try {
            return new ConstantMethodTypeInfo(in.readUnsignedShort());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    }),
    INVOKE_DYNAMIC(18, (in,c) -> {
        try {
            return new ConstantInvokeDynamicInfo(in.readUnsignedShort(), in.readUnsignedShort());
        } catch(IOException e) {
            return SneakyThrow.sneaky(e);
        }
    });

    private final int id;
    private final BiFunction<DataInput,Constant[], Constant> readFunction;

    private final static TIntObjectMap<ConstantType> index = new TIntObjectHashMap<>();

    static {
        for(ConstantType constantType : values()) {
            index.put(constantType.getId(), constantType);
        }
    }

    public static Constant readSingleConstant(DataInput input, Constant[] constants) throws IOException {
        int i = input.readUnsignedByte();
        System.out.println(i);
        ConstantType type = index.get(i);
        if(type == null) {
            System.out.println("unk " + i);
        }
        System.out.println(type.name());

        return type.readFunction.apply(input, constants);
    }
}
