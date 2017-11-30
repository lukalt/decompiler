package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class InstanceOfAction implements StackAction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        block.getOperandStack().add(new VariableStorage.Variable("(" + block.getOperandStack().remove(0) + " instanceof " + MethodDescriptor.makeClassName(block.getConstantPool().get(data[0]).toString()) + " ? 1 : 0)", VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
