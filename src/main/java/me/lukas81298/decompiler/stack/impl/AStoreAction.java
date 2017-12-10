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
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        String value = block.getStack().pop().getRefId();
        String index = block.getStack().pop().getRefId();
        String array = block.getStack().pop().getRefId();
        block.getWriter().println(array + "[" + index + "] = " + value + ";", block.getLevel());
        return true;
    }
}
