package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.bytecode.constant.Constant;
import me.lukas81298.decompiler.bytecode.constant.ConstantString;
import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class LdcAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        int datum = data.length == 1 ? data[0] : Helpers.mergeFirst(data);
        Constant constant = context.getConstantPool().get(datum);
        context.getStack().push(new StackItem(constant instanceof ConstantString ? ("\"" + constant.toString() + "\"") : constant.toString(), VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
