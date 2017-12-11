package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class PrimitiveCastAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        context.getStack().push(new StackItem("(" + type.getLabel() + ") " + context.getStack().pop().getRefId(), type));
        return true;
    }
}
