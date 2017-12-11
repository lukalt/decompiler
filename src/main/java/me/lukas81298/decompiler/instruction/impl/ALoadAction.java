package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class ALoadAction implements ByteCodeInstruction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        if(data[0] == 0) {
            context.getStack().push(new StackItem("this", VariableStorage.PrimitiveType.OBJECT));
        } else {
            context.getStack().push(context.getVariables().get(data[0]));

        }
        return true;
    }
}
