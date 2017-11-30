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
public class SourceFieldAttribute extends AttributeData {

    private final String sourceFile;

    @Override
    public AttributeType getType() {
        return AttributeType.SOURCE_FILE;
    }
}
