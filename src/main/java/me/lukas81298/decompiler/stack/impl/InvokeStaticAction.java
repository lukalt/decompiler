package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantMethodRefInfo;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class InvokeStaticAction implements StackAction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {

        ConstantMethodRefInfo methodRefInfo = block.getConstantPool().get(Helpers.mergeFirst(data), ConstantMethodRefInfo.class);
        MethodDescriptor methodDescriptor = methodRefInfo.getMethodDescriptor();
        StringBuilder sb = new StringBuilder(MethodDescriptor.makeClassName(methodRefInfo.getClassName().getName()) + "." + methodRefInfo.getName() + "(");
        for(int i = 0; i < methodDescriptor.getArgumentTypes().length; i++) {
            if(i > 0) {
                sb.append(", ");
            }
            sb.append(block.getOperandStack().remove(0).getRefId());
        }
        sb.append(")");
        if(methodDescriptor.getReturnType().equals("void")) {
            block.getWriter().println(sb.append(";").toString(), block.getLevel());
        } else {
            block.getOperandStack().add(new VariableStorage.Variable(sb.toString(), VariableStorage.PrimitiveType.OBJECT));
        }

        return true;
    }
}
