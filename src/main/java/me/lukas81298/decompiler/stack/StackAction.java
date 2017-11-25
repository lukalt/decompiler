package me.lukas81298.decompiler.stack;

import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public interface StackAction {

    boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block);
}
