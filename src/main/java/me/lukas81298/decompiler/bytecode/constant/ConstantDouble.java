package me.lukas81298.decompiler.bytecode.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.ConstantPool;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
public class ConstantDouble implements Constant {

    private final ConstantPool constantPool;
    private final double value;

    @Override
    public ConstantType getType() {
        return ConstantType.DOUBLE;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    @Override
    public int getSpace() {
        return 2;
    }
}
