package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class DupAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        block.getOperandStack().add(block.getOperandStack().get(block.getOperandStack().size() - 1)); // duplicate
        return true;
    }
}