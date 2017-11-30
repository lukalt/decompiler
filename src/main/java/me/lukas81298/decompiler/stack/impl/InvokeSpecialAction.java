package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantMethodRefInfo;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class InvokeSpecialAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        int index = (data[0] << 8) + data[1];
        VariableStorage.Variable object = block.getOperandStack().remove(0);
        ConstantMethodRefInfo methodRef = block.getConstantPool().get(index, ConstantMethodRefInfo.class);
        if(block.isSuperChecker()) {
            StringBuilder s = new StringBuilder();
            s.append("new ").append(object.getRefId()).append("(");
            for(int i = 0; i < methodRef.getMethodDescriptor().getArgumentTypes().length; i++) {
                if(i > 0) {
                    s.append(", ");
                }
                s.append(block.getOperandStack().remove(0).getRefId());
            }
            block.getOperandStack().remove(0);
            block.getOperandStack().add(new VariableStorage.Variable(s.append(")").toString(), VariableStorage.PrimitiveType.EXPRESSION));

        } else {
            block.getWriter().println((object.getRefId().equals("this") ? "" : (object.getRefId() + ".")) + "super(" + String.join(", ", block.getOperandStack().stream().map(v -> {
                return v.getRefId();
            }).collect(Collectors.toList())) + ");", block.getLevel());
            block.getOperandStack().clear();
        }

        block.setSuperChecker(false);
        return true;
    }
}
