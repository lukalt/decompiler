package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class AStoreAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        String array = block.getOperandStack().remove(0).getRefId();
        String index = block.getOperandStack().remove(0).getRefId();
        String value = block.getOperandStack().remove(0).getRefId();
        block.getWriter().println(array + "[" + index + "] = " + value + ";", block.getLevel());
        return true;
    }
}
