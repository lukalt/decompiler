package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class PutFieldAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        /*String[] split = comment.split(" ");
        if(split[0].equals("Field")) {
            String fieldName = split[1].split(":")[0];
            block.getWriter().println(block.getOperandStack().remove(0).getRefId() + "." + fieldName + " = " + block.getOperandStack().remove(0).getRefId() + ";", block.getLevel());
        }*/
        return true;
    }
}
