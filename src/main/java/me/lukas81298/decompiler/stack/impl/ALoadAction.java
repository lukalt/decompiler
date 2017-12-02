package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

import java.util.Arrays;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class ALoadAction implements StackAction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        if(data[0] == 0) {
            block.getOperandStack().add(new VariableStorage.Variable("this", VariableStorage.PrimitiveType.OBJECT));
        } else {
            VariableStorage.Variable e = block.getVariables().get(data[0]);
            block.getOperandStack().add(e);

        }
        return true;
    }
}
