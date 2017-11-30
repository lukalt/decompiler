package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class ArrayElementLoadAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int pc, Block block) {
        String ref = block.getOperandStack().remove(0).getRefId();
        String index = block.getOperandStack().remove(0).getRefId();
        block.getOperandStack().add(new VariableStorage.Variable(ref + "[" + index + "]", type));
        return true;
    }
}
