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
public enum ClassFlag {

    ACC_PUBLIC(0x0001),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000);

    private final int value;

    public static Set<ClassFlag> fromBitMask(int bitMask) {
        Set<ClassFlag> set = new HashSet<>();
        for(ClassFlag classFlag : values()) {
            if((bitMask & classFlag.getValue()) == classFlag.getValue()) {
                set.add(classFlag);
            }
        }
        return set;
    }
}
