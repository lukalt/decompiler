package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class NewAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        context.setSuperChecker(true);
        context.getStack().push(new StackItem(MethodDescriptor.makeClassName(context.getConstantPool().get(Helpers.mergeFirst(data)).toString(), context.getClassFile()), VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
