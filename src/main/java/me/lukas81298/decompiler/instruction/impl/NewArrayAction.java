package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class NewArrayAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        String t;
        switch(data[0]) {
            case 4:
                t = "boolean";
                break;
            case 5:
                t = "char";
                break;
            case 6:
                t = "float";
                break;
            case 7:
                t = "double";
                break;
            case 8:
                t = "byte";
                break;
            case 9:
                t = "short";
                break;
            case 10:
                t = "int";
                break;
            case 11:
                t = "long";
                break;
            default:
                t = "undf" + data[0];
                break;
        }
        context.getStack().push(new StackItem("new " + t + "[" + context.getStack().pop().getRefId() + "]", VariableStorage.PrimitiveType.OBJECT));
        return true;
    }

}
