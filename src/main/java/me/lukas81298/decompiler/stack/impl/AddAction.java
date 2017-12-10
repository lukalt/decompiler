package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class AddAction implements StackAction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        StackItem first = block.getStack().pop(), second = block.getStack().pop();
        block.getStack().push(new StackItem("(" + first.getRefId() + " + " + second.getRefId() + ")", type));
        return true;
    }
}
