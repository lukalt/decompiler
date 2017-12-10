package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class SwapAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        StackItem first = block.getStack().pop(), second = block.getStack().pop();
        if(first == null) {
            throw new RuntimeException( "first == null" );
        }
        if(second == null) {
            throw new RuntimeException( "first == null" );
        }
        block.getStack().push(second);
        block.getStack().push(first);
        return true;
    }
}
