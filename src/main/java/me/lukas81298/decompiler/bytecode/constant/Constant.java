package me.lukas81298.decompiler.bytecode.constant;

/**
 * @author lukas
 * @since 27.11.2017
 */
public interface Constant {

    ConstantType getType();

    String toString();

}
