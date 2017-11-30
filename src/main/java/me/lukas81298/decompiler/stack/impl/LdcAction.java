package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class LdcAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        int datum = data.length == 1 ? data[0] : Helpers.mergeFirst(data);
        block.getOperandStack().add(new VariableStorage.Variable(block.getConstantPool().get(datum).toString(), VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
