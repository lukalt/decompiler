package me.lukas81298.decompiler.bytecode.atrr.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;

/**
 * @author lukas
 * @since 29.11.2017
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SignatureAttribute extends AttributeData {

    private final String signature;

    @Override
    public AttributeType getType() {
        return AttributeType.SIGNATURE;
    }
}
