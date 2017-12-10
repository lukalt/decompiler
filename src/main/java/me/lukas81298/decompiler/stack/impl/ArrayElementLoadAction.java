package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class ArrayElementLoadAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        String ref = block.getStack().pop().getRefId();
        String index = block.getStack().pop().getRefId();
        block.getStack().push(new StackItem(ref + "[" + index + "]", type));
        return true;
    }

}
