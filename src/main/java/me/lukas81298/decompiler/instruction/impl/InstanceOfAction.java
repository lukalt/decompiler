package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class InstanceOfAction implements ByteCodeInstruction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        context.getStack().push(new StackItem("(" + context.getStack().pop() + " instanceof " + MethodDescriptor.makeClassName(context.getConstantPool().get(data[0]).toString()) + " ? 1 : 0)", VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
