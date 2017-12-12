package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class InstanceOfAction implements ByteCodeInstruction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        context.getStack().push(new StackItem( context.getStack().pop().getRefId() + " instanceof " + MethodDescriptor.makeClassName(context.getConstantPool().get(Helpers.mergeFirst(data)).toString(), context.getClassFile()), VariableStorage.PrimitiveType.INSTANCE_OF_EXPRESSION));
        return true;
    }
}
