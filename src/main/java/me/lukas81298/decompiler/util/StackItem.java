package me.lukas81298.decompiler.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Objects;

/**
 * @author lukas
 * @since 05.12.2017
 */
@RequiredArgsConstructor
@Getter
@ToString
public final class StackItem {

    private final Object value;
    private final VariableStorage.PrimitiveType type;
    private final String variableName;

    public StackItem(Object value, VariableStorage.PrimitiveType type) {
        this.value = value;
        this.type = type;
        this.variableName = null;
    }

    public boolean hasName() {
        return this.variableName != null;
    }

    public String getRefId() {
        return this.hasName() ? this.variableName : Objects.toString(this.value);
    }
}
