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
public class ConstantMethodTypeInfo implements Constant {

    private final ConstantPool constantPool;
    private final int descriptorIndex;

    @Override
    public ConstantType getType() {
        return ConstantType.METHOD_TYPE;
    }

    @Override
    public String toString() {
        return Integer.toString(this.descriptorIndex);
    }
}
