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
 * @since 25.11.2017
 */
public class InvokeSpecialAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        int index = Helpers.mergeFirst(data);
        ConstantMethodRefInfo methodRef = context.getConstantPool().get(index, ConstantMethodRefInfo.class);
        String[] argumentTypes = methodRef.getMethodDescriptor(context.getClassFile()).getArgumentTypes();
        String arguments = MethodDescriptor.parseArgumentSignature(argumentTypes, context.getStack());
        StackItem object = context.getStack().pop();
        if(object.getRefId().contains("$")) {
            context.getWriter().println("// Warning: Currently anonymous inner classes are not supported, please decompile " + object.getRefId() + " yourself", context.getLevel());
        }
        if(context.isSuperChecker()) {
            context.getStack().push(new StackItem("new " + object.getRefId() + arguments, VariableStorage.PrimitiveType.EXPRESSION));
        } else if(argumentTypes.length > 0) { // prevent useless super() calls
            context.getWriter().println((!object.getRefId().equals("this") ? (object.getRefId() + ".") : "") + "super" + arguments, context.getLevel());
        }

        context.setSuperChecker(false);
        return true;
    }
}
