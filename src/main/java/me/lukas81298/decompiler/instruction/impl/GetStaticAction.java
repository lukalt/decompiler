package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantFieldRefInfo;
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
public class GetStaticAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        ConstantFieldRefInfo fieldRef = context.getConstantPool().get(Helpers.mergeFirst(data), ConstantFieldRefInfo.class);
        String s = MethodDescriptor.makeClassName(fieldRef.getClassName() + "." + fieldRef.getNameAndType().getName());
        context.getStack().push(new StackItem(s, VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}