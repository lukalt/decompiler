package me.lukas81298.decompiler.bytecode.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
public class ConstantNameAndType implements Constant {

    private final String name;
    private final String descriptor;

    @Override
    public ConstantType getType() {
        return ConstantType.NAME_AND_TYPE;
    }

    @Override
    public String toString() {
        return this.name + " " + this.descriptor;
    }
}
