package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class PutStaticAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        String[] split = comment.split(" ");
        if(split[0].equals("Field")) {
            String variable = split[1].split(":")[0];
            block.getWriter().println(variable + " = " + block.getOperandStack().remove(0).getRefId() + ";", block.getLevel());
        }
        return true;
    }

}
