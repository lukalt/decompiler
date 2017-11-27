package me.lukas81298.decompiler.bytecode.constant;

/**
 * @author lukas
 * @since 27.11.2017
 */
public class ConstantMethodRefInfo extends FMConstantAbstract {

    public ConstantMethodRefInfo(String className, String nameAndType) {
        super(className, nameAndType);
    }

    @Override
    public ConstantType getType() {
        return ConstantType.METHOD_REF;
    }
}
