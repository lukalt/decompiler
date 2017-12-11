package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantMethodRefInfo;
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
public class InvokeStaticAction implements ByteCodeInstruction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        ConstantMethodRefInfo methodRefInfo = context.getConstantPool().get(Helpers.mergeFirst(data), ConstantMethodRefInfo.class);
        MethodDescriptor methodDescriptor = methodRefInfo.getMethodDescriptor(context.getClassFile());
        StringBuilder sb = new StringBuilder(MethodDescriptor.makeClassName(methodRefInfo.getClassName().getName()) + "." + methodRefInfo.getName());
        sb.append(MethodDescriptor.parseArgumentSignature(methodDescriptor.getArgumentTypes(), context.getStack()));
        if(methodDescriptor.getReturnType().equals("void")) {
            context.getWriter().println(sb.append(";").toString(), context.getLevel());
        } else {
            context.getStack().push(new StackItem(sb.toString(), VariableStorage.PrimitiveType.OBJECT));
        }
        return true;
    }
}
