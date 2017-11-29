package me.lukas81298.decompiler.bytecode.constant;

import me.lukas81298.decompiler.bytecode.ConstantPool;

/**
 * @author lukas
 * @since 27.11.2017
 */
public class ConstantMethodRefInfo extends FMConstantAbstract {

    public ConstantMethodRefInfo(ConstantPool constantPool, int className, int nameAndType) {
        super(constantPool, className, nameAndType);
    }

    @Override
    public ConstantType getType() {
        return ConstantType.METHOD_REF;
    }
}
