package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantFieldRefInfo;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class PutFieldAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        ConstantFieldRefInfo fieldRef = block.getConstantPool().get(Helpers.mergeFirst(data), ConstantFieldRefInfo.class);
        block.getWriter().println(block.getOperandStack().remove(0).getRefId() + "." + Helpers.lastSplitItem(fieldRef.getNameAndType().getName(), "\\.")+ " = " + block.getOperandStack().remove(0).getRefId() + ";", block.getLevel());
        return true;
    }
}
