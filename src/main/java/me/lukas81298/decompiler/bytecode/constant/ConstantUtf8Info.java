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
public class ConstantUtf8Info implements Constant {

    private final ConstantPool constantPool;
    private final String value;

    @Override
    public ConstantType getType() {
        return ConstantType.UTF_8;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
