package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class IIncAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        int var = data[0];
        int mod = data[1];
        StackItem v = block.getVariables().get(var);
        if(mod == 1) {
            block.getWriter().println(v.getRefId() + "++;", block.getLevel());
        } else if(mod == -1) {
            block.getWriter().println(v.getRefId() + "--;", block.getLevel());
        } else if(mod >= 0){
            block.getWriter().println(v.getRefId() + " += " + mod + ";", block.getLevel());
        } else {
            block.getWriter().println(v.getRefId() + " -= " + (mod * -1) + ";", block.getLevel());
        }
        return true;
    }
}
