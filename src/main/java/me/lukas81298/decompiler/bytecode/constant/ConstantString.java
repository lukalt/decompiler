package me.lukas81298.decompiler.bytecode.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
public class ConstantString implements Constant {

    private final String value;

    @Override
    public ConstantType getType() {
        return ConstantType.STRING;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
