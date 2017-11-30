package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class PrimitiveCastAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int pc, Block block) {
        block.getOperandStack().add(new VariableStorage.Variable("(" + type.getLabel() + ") " + block.getOperandStack().remove(0).getRefId(), type));
        return true;
    }
}
