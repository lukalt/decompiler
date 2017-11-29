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
public class ConstantString implements Constant {

    private final ConstantPool constantPool;
    private final int value;

    public String getValue() {
        return this.constantPool.get(this.value, ConstantUtf8Info.class).getValue();
    }

    @Override
    public ConstantType getType() {
        return ConstantType.STRING;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
