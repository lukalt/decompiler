package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.Constant;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class ANewArrayAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        Constant constant = block.getConstantPool().get(Helpers.mergeFirst(data));
        block.getStack().push(new StackItem("new " + MethodDescriptor.makeClassName(constant.toString()) + "[" + block.getStack().pop().getRefId() + "]", VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
