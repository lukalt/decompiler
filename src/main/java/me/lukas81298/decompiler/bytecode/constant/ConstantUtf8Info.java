package me.lukas81298.decompiler.bytecode.constant;

/**
 * @author lukas
 * @since 27.11.2017
 */
public class ConstantUtf8Info extends ConstantString {

    public ConstantUtf8Info(String value) {
        super(value);
    }

    @Override
    public ConstantType getType() {
        return ConstantType.UTF_8;
    }
}
