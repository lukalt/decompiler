package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class GoToAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        int target = Helpers.mergeFirst(data);
        if(target > pc) {
            // some kind of else structure
        } else {
           return false;
        }
        return true;
    }
}
