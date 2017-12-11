package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.bytecode.constant.Constant;
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
public class ANewArrayAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        Constant constant = context.getConstantPool().get(Helpers.mergeFirst(data));
        context.getStack().push(new StackItem("new " + MethodDescriptor.makeClassName(constant.toString()) + "[" + context.getStack().pop().getRefId() + "]", VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
