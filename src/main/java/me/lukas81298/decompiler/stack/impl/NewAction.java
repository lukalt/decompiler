package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

import java.util.Arrays;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class NewAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        block.setSuperChecker(true);
        block.getOperandStack().add(new VariableStorage.Variable(MethodDescriptor.makeClassName(block.getConstantPool().get(Helpers.mergeFirst(data)).toString()), VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
