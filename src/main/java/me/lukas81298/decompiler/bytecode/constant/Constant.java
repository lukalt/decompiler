package me.lukas81298.decompiler.bytecode.constant;

import me.lukas81298.decompiler.bytecode.ConstantPool;

/**
 * @author lukas
 * @since 27.11.2017
 */
public interface Constant {

    ConstantType getType();

    String toString();

    ConstantPool getConstantPool();

    default int getSpace() {
        return 1;
    }

}
