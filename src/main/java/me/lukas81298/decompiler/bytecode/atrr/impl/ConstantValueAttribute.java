package me.lukas81298.decompiler.bytecode.atrr.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;
import me.lukas81298.decompiler.bytecode.constant.Constant;

/**
 * @author lukas
 * @since 29.11.2017
 */
@RequiredArgsConstructor
@Getter
public class ConstantValueAttribute extends AttributeData {

    private final Constant constant;

    @Override
    public AttributeType getType() {
        return AttributeType.CONSTANT_VALUE;
    }
}
