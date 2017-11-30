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

    ACC_PUBLIC(0x0001, "public"),
    ACC_FINAL(0x0010, "final"),
    ACC_SUPER(0x0020, "super"),
    ACC_INTERFACE(0x0200, "interface"),
    ACC_ABSTRACT(0x0400, "abstract"),
    ACC_SYNTHETIC(0x1000, "synthetic"),
    ACC_ANNOTATION(0x2000, "annotation"),
    ACC_ENUM(0x4000, "enum");

    private final int value;
    private final String tag;

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
