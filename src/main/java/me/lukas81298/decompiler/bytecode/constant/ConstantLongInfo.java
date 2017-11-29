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
public class ConstantLongInfo implements Constant {

    private final ConstantPool constantPool;
    private final long value;

    @Override
    public ConstantType getType() {
        return ConstantType.LONG;
    }

    @Override
    public String toString() {
        return Long.toString(this.value);
    }

    @Override
    public int getSpace() {
        return 2;
    }
}
