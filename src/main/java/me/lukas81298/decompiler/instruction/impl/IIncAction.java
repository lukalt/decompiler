package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class IIncAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        int var = data[0];
        int mod = data[1];
        StackItem v = context.getVariables().get(var);
        if(mod == 1) {
            context.getWriter().println(v.getRefId() + "++;", context.getLevel());
        } else if(mod == -1) {
            context.getWriter().println(v.getRefId() + "--;", context.getLevel());
        } else if(mod >= 0){
            context.getWriter().println(v.getRefId() + " += " + mod + ";", context.getLevel());
        } else {
            context.getWriter().println(v.getRefId() + " -= " + (mod * -1) + ";", context.getLevel());
        }
        return true;
    }
}
