package me.lukas81298.decompiler.bytecode.constant;

/**
 * @author lukas
 * @since 27.11.2017
 */
public class ConstantFieldRefInfo extends FMConstantAbstract {

    public ConstantFieldRefInfo(String className, String nameAndType) {
        super(className, nameAndType);
    }

    @Override
    public ConstantType getType() {
        return ConstantType.FIELD_REF;
    }
}
