package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class SwapAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        StackItem first = context.getStack().pop(), second = context.getStack().pop();
        if(first == null) {
            throw new RuntimeException( "first == null" );
        }
        if(second == null) {
            throw new RuntimeException( "first == null" );
        }
        context.getStack().push(second);
        context.getStack().push(first);
        return true;
    }
}
