package me.lukas81298.decompiler.bytecode.constant;

import me.lukas81298.decompiler.bytecode.ConstantPool;

/**
 * @author lukas
 * @since 27.11.2017
 */
public class ConstantFieldRefInfo extends FMConstantAbstract {

    public ConstantFieldRefInfo(ConstantPool constantPool, int className, int nameAndType) {
        super(constantPool, className, nameAndType);
    }

    @Override
    public ConstantType getType() {
        return ConstantType.FIELD_REF;
    }
}
