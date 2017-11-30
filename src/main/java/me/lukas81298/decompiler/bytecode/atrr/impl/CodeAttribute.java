package me.lukas81298.decompiler.bytecode.atrr.impl;

import lombok.*;
import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeInfo;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;
import me.lukas81298.decompiler.bytecode.constant.ConstantClassInfo;

/**
 * @author lukas
 * @since 29.11.2017
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class CodeAttribute extends AttributeData {

    private final int maxStack;
    private final int maxLocals;
    private final CodeItem[] code;
    private final ExceptionItem[] exceptionTable;
    private final AttributeInfo[] attributes;

    @Override
    public AttributeType getType() {
        return AttributeType.CODE;
    }

    @RequiredArgsConstructor
    @Getter
    @ToString
    @EqualsAndHashCode(callSuper = false)
    public final static class CodeItem {
        private final String id;
        private final byte[] additionalData;
    }

    @RequiredArgsConstructor
    @Getter
    @ToString
    @EqualsAndHashCode(callSuper = false)
    public final static class ExceptionItem {
        private final int startPointer,endPointer,handlerPointer;
        private final ConstantClassInfo catchType;
    }
}
