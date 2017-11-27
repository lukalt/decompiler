package me.lukas81298.decompiler.bytecode.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
public class ConstantDouble implements Constant {

    private final double value;


    @Override
    public ConstantType getType() {
        return ConstantType.DOUBLE;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }
}
