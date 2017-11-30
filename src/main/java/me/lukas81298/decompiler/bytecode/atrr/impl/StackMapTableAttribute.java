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
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
public class StackMapTableAttribute extends AttributeData {

    private final StackMapFrame[] entries;

    @Override
    public AttributeType getType() {
        return AttributeType.STACK_MAP_TABLE;
    }

    public final class StackMapFrame {

    }
}
