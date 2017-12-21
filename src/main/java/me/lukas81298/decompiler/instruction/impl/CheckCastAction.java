package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantClassInfo;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 21.12.2017
 */
public class CheckCastAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        String s = context.getStack().pop().getRefId();
        String t = MethodDescriptor.makeClassName(context.getConstantPool().get(Helpers.mergeFirst(data), ConstantClassInfo.class).getName(), context.getClassFile());
        context.getStack().push(new StackItem("((" + t + ") " + s + ")", VariableStorage.PrimitiveType.OBJECT));
        return true;
    }

}
