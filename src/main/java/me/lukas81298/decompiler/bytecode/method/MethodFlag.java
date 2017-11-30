package me.lukas81298.decompiler.bytecode.method;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lukas
 * @since 27.11.2017
 */
@Getter
@RequiredArgsConstructor
public enum MethodFlag {

    ACC_PUBLIC(0x0001, "public"),
    ACC_PRIVATE(0x0002, "private"),
    ACC_PROTECTED(0x0004, "protected"),
    ACC_STATIC(0x0008, "static"),
    ACC_FINAL(0x0010, "final"),
    ACC_SYNCHRONIZED(0x0020, "synchronized"),
    ACC_BRIDGE(0x0040),
    ACC_VARARGS(0x0080),
    ACC_NATIVE(0x0100, "native"),
    ACC_ABSTRACT(0x4000, "abstract"),
    ACC_STRICT(0x800),
    ACC_SYNTHETIC(0x1000);

    private final int value;
    private final String name;

    MethodFlag(int value) {
        this.value = value;
        this.name = null;
    }

    public static List<MethodFlag> fromBitMask(int bitMask) {
        List<MethodFlag> list = new ArrayList<>();
        for(MethodFlag classFlag : values()) {
            if((bitMask & classFlag.getValue()) == classFlag.getValue()) {
                list.add(classFlag);
            }
        }
        return list;
    }
}
