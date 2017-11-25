package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class MulAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        VariableStorage.Variable first = block.getOperandStack().get(0), second = block.getOperandStack().get(1);
        block.getOperandStack().clear();
        block.getOperandStack().add(new VariableStorage.Variable("(" + first.getRefId() + " * " + second.getRefId() + ")", type));
        return true;
    }

}