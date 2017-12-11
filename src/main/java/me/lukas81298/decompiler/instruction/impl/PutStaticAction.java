package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantFieldRefInfo;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class PutStaticAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        ConstantFieldRefInfo constantFieldRefInfo = context.getConstantPool().get(Helpers.mergeFirst(data), ConstantFieldRefInfo.class);
        String variable = MethodDescriptor.makeClassName(constantFieldRefInfo.getClassName().getName(), context.getClassFile()) + "." + constantFieldRefInfo.getNameAndType().getName();
        context.getWriter().println(variable + " = " + context.getStack().pop().getRefId() + ";", context.getLevel());
        return true;
    }
}
