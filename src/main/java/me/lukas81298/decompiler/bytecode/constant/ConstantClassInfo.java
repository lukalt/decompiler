package me.lukas81298.decompiler.bytecode.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
public class ConstantClassInfo implements Constant {

    private final String name;

    @Override
    public ConstantType getType() {
        return ConstantType.CLASS;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
