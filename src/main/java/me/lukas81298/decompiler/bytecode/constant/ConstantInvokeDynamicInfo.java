package me.lukas81298.decompiler.bytecode.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
public class ConstantInvokeDynamicInfo implements Constant {

    private final int bootstrapMethodAttrIndex;
    private final int nameAndTypeIndex;

    @Override
    public ConstantType getType() {
        return ConstantType.INVOKE_DYNAMIC;
    }

    @Override
    public String toString() {
        return bootstrapMethodAttrIndex + "." + nameAndTypeIndex;
    }
}
