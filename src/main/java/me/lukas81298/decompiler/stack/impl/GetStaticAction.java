package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class GetStaticAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        String[] split = comment.split(" ");
        if(split[0].equals("Field")) {
            String s = split[1].split(":")[0].replace("/", ".");
            block.getOperandStack().add(new VariableStorage.Variable(s, VariableStorage.PrimitiveType.OBJECT));
        }
        return true;
    }
}
