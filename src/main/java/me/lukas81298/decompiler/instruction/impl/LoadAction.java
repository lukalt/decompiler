package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class LoadAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        context.getStack().push(context.getVariables().get(data[0]));
        return true;
    }
}
