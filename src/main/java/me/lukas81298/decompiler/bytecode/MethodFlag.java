package me.lukas81298.decompiler.bytecode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lukas
 * @since 27.11.2017
 */
@Getter
@RequiredArgsConstructor
public enum MethodFlag {

    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    ACC_FINAL(0x0010),
    ACC_SYNCHRONIZED(0x0020),
    ACC_BRIDGE(0x0040),
    ACC_VARARGS(0x0080),
    ACC_NATIVE(0x0100),
    ACC_ABSTRACT(0x4000),
    ACC_STRICT(0x800),
    ACC_SYNTHETIC(0x1000);

    private final int value;

    public static Set<MethodFlag> fromBitMask(int bitMask) {
        Set<MethodFlag> set = new HashSet<>();
        for(MethodFlag classFlag : values()) {
            if((bitMask & classFlag.getValue()) == classFlag.getValue()) {
                set.add(classFlag);
            }
        }
        return set;
    }
}
