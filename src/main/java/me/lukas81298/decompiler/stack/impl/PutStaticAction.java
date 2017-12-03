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
public class PutStaticAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        ConstantFieldRefInfo constantFieldRefInfo = block.getConstantPool().get(Helpers.mergeFirst(data), ConstantFieldRefInfo.class);
        String variable = MethodDescriptor.makeClassName(constantFieldRefInfo.getClassName().getName(), block.getClassFile()) + "." + constantFieldRefInfo.getNameAndType().getName();
        block.getWriter().println(variable + " = " + block.getOperandStack().remove(0).getRefId() + ";", block.getLevel());
        return true;
    }
}
