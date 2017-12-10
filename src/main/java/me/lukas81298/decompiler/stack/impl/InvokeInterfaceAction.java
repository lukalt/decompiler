package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantInterfaceMethodRefInfo;
import me.lukas81298.decompiler.bytecode.constant.ConstantMethodRefInfo;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class InvokeInterfaceAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        ConstantInterfaceMethodRefInfo methodRef = block.getConstantPool().get(Helpers.mergeFirst(data), ConstantInterfaceMethodRefInfo.class);
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < methodRef.getMethodDescriptor(block.getClassFile()).getArgumentTypes().length; i++) {
            if(i > 0) {
                result.append(", ");
            }
            result.append(block.getStack().pop().getRefId());
        }
        String name = methodRef.getNameAndType().getName();

        String refName = block.getStack().pop().getRefId();
        result.append(")");
        String resultString = refName + "." + name + "(" + result;
        if(methodRef.getMethodDescriptor(block.getClassFile()).getReturnType().equals("void")) {
            block.getWriter().println(resultString + ";", block.getLevel());
        } else {
            block.getStack().push(new StackItem(resultString, VariableStorage.PrimitiveType.OBJECT));
        }
        return true;
    }
}
