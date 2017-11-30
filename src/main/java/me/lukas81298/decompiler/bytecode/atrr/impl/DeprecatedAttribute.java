package me.lukas81298.decompiler.bytecode.atrr.impl;

import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;

/**
 * @author lukas
 * @since 30.11.2017
 */
public class DeprecatedAttribute extends AttributeData {

    @Override
    public AttributeType getType() {
        return AttributeType.DEPRECATED;
    }
}
