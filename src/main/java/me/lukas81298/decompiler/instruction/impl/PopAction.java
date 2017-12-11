package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 02.12.2017
 */
public class PopAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        context.getWriter().println(context.getStack().pop().getRefId() + ";", context.getLevel());
        return true;
    }
}
