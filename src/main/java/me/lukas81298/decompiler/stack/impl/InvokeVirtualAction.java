package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantMethodRefInfo;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class InvokeVirtualAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        String refName = block.getOperandStack().remove(0).getRefId();

        ConstantMethodRefInfo methodRef = block.getConstantPool().get(Helpers.mergeFirst(data), ConstantMethodRefInfo.class);
        String name = methodRef.getNameAndType().getName();
        StringBuilder result = new StringBuilder(refName + "." + name + "(");
        for(int i = 0; i < methodRef.getMethodDescriptor(block.getClassFile()).getArgumentTypes().length; i++) {
            if(i > 0) {
                result.append(", ");
            }
            result.append(block.getOperandStack().remove(0).getRefId());
        }
        result.append(")");
        if(methodRef.getMethodDescriptor(block.getClassFile()).getReturnType().equals("void")) {
            block.getWriter().println(result + ";", block.getLevel());
        } else {
            block.getOperandStack().add(new VariableStorage.Variable(result, VariableStorage.PrimitiveType.OBJECT));
        }
        return true;
    }
}
