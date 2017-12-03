package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantMethodRefInfo;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class InvokeInterfaceAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        String toInvoke = block.getOperandStack().remove(0).getRefId();
        ConstantMethodRefInfo methodRef = block.getConstantPool().get(Helpers.mergeFirst(data), ConstantMethodRefInfo.class);
        String name = methodRef.getNameAndType().getName();
        StringBuilder result = new StringBuilder(toInvoke + "." + name + "(");
        for(int i = 0; i < methodRef.getMethodDescriptor().getArgumentTypes().length; i++) {
            if(i > 0) {
                result.append(", ");
            }
            result.append(block.getOperandStack().remove(0).getRefId());
        }
        result.append(")");
        block.getOperandStack().add(new VariableStorage.Variable(result, VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
