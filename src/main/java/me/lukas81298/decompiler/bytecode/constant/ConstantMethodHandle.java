package me.lukas81298.decompiler.bytecode.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
public class ConstantMethodHandle implements Constant {

    private final int referenceKind,referenceIndex; // todo

    @Override
    public ConstantType getType() {
        return ConstantType.METHOD_HANDLE;
    }

    @Override
    public String toString() {
        return referenceKind + "." + referenceIndex;
    }
}
