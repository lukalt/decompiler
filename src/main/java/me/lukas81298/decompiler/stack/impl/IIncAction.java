package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class IIncAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        /*String[] split = arg.split(",");
        int var = Integer.parseInt(split[0]);
        int mod = Integer.parseInt(split[1]);
        VariableStorage.Variable v = block.getVariables().get(var);
        if(mod == 1) {
            block.getWriter().println(v.getRefId() + "++;", block.getLevel());
        } else if(mod == -1) {
            block.getWriter().println(v.getRefId() + "--;", block.getLevel());
        } else if(mod >= 0){
            block.getWriter().println(v.getRefId() + " += " + mod + ";", block.getLevel());
        } else {
            block.getWriter().println(v.getRefId() + " -= " + (mod * -1) + ";", block.getLevel());
        }*/
        return true;
    }
}
