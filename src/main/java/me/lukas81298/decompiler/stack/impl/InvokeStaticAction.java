package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantMethodRefInfo;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class InvokeStaticAction implements StackAction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        ConstantMethodRefInfo methodRefInfo = block.getConstantPool().get(Helpers.mergeFirst(data), ConstantMethodRefInfo.class);
        MethodDescriptor methodDescriptor = methodRefInfo.getMethodDescriptor(block.getClassFile());
        StringBuilder sb = new StringBuilder(MethodDescriptor.makeClassName(methodRefInfo.getClassName().getName()) + "." + methodRefInfo.getName());
        sb.append(MethodDescriptor.parseArgumentSignature(methodDescriptor.getArgumentTypes(), block.getStack()));
        if(methodDescriptor.getReturnType().equals("void")) {
            block.getWriter().println(sb.append(";").toString(), block.getLevel());
        } else {
            block.getStack().push(new StackItem(sb.toString(), VariableStorage.PrimitiveType.OBJECT));
        }
        return true;
    }
}
