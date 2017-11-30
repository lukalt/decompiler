package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class GetStaticAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        String s = MethodDescriptor.makeClassName(block.getConstantPool().get(Helpers.mergeFirst(data)).toString());
        block.getOperandStack().add(new VariableStorage.Variable(s, VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
