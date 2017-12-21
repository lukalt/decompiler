package me.lukas81298.decompiler.bytecode.field;

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
public enum FieldFlag {

    ACC_PUBLIC(0x0001, "public"),
    ACC_PRIVATE(0x0002, "private"),
    ACC_PROTECTED(0x0004, "protected"),
    ACC_STATIC(0x0008, "static"),
    ACC_FINAL(0x0010, "final"),
    ACC_VOLATILE(0x0040, "volatile"),
    ACC_TRANSIENT(0x0080, "transient"),
    ACC_SYNTHETIC(0x1000, "/* synthetic */"),
    ACC_ENUM(0x4000, "enum");

    private final int value;
    private final String name;

    public static List<FieldFlag> fromBitMask(int bitMask) {
        List<FieldFlag> list = new ArrayList<>();
        for(FieldFlag classFlag : values()) {
            if((bitMask & classFlag.getValue()) == classFlag.getValue()) {
                list.add(classFlag);
            }
        }
        return list;
    }
}
