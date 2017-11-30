package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class SwapAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int pc, Block block) {
        VariableStorage.Variable first = block.getOperandStack().remove(0), second = block.getOperandStack().remove(0);
        block.getOperandStack().add(second);
        block.getOperandStack().add(first);
        return true;
    }
}
