package me.lukas81298.decompiler.bytecode.atrr.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;
import me.lukas81298.decompiler.bytecode.constant.ConstantClassInfo;

/**
 * @author lukas
 * @since 29.11.2017
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ExceptionsAttribute extends AttributeData {

    private final ConstantClassInfo[] exceptionTable;

    @Override
    public AttributeType getType() {
        return AttributeType.EXCEPTIONS;
    }
}
