package me.lukas81298.decompiler.bytecode.atrr.impl;

import gnu.trove.map.TIntObjectMap;
import lombok.*;
import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;

/**
 * @author lukas
 * @since 30.11.2017
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class LocalVariableAttribute extends AttributeData {

    private final TIntObjectMap<LocalVariable> localVariables;

    @Override
    public AttributeType getType() {
        return AttributeType.LOCAL_VARIABLE_TABLE;
    }

    @Getter
    @Setter
    @ToString
    public static final class LocalVariable {
        private int startPc,length, index;
        private String name;
        private String descriptor;
    }
}
