package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class StoreAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        VariableStorage.Variable element = block.getOperandStack().get(0);
        int i = Integer.parseInt(arg);
        block.getVariables().set(i, element.getValue(), type, "local_" + arg);
        block.getOperandStack().clear();
        if(block.getDefinedVariables().add(i)) {
            block.getWriter().println(element.getType().getLabel() + " local_" + arg + " = " + element.getRefId() + ";", block.getLevel());
        } else {
            block.getWriter().println("local_" + arg + " = " + element.getRefId() + ";", block.getLevel());
        }
        return true;
    }
}