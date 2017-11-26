package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class Ldc2WAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        String[] split = comment.split(" ");
        if(split[0].equals("long")) {
            if(split[1].endsWith("l")) {
                split[1] = split[1].substring(0, split[1].length() - 1);
            }
            block.getOperandStack().add(new VariableStorage.Variable(Long.parseLong(split[1]), VariableStorage.PrimitiveType.LONG));
        } else {
            if(split[1].endsWith("d")) {
                split[1] = split[1].substring(0, split[1].length() - 1);
            }
            block.getOperandStack().add(new VariableStorage.Variable(Double.parseDouble(split[1]), VariableStorage.PrimitiveType.DOUBLE));
        }
        return true;
    }
}
