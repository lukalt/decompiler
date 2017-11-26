package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class ANewArrayAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        String[] split = comment.split(" ");
        if(split[0].equals("class")) {
            block.getOperandStack().add(new VariableStorage.Variable("new " + split[1].replace("/", ".") + "[" + block.getOperandStack().remove(0).getRefId() + "]", VariableStorage.PrimitiveType.OBJECT));
        }
        return true;
    }
}
