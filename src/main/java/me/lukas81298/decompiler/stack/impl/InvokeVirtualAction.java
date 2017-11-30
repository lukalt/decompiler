package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

import java.util.stream.Collectors;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class InvokeVirtualAction implements StackAction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        /*String[] split = comment.split(" ");
        if(split[0].equals("Method")) {
            String methodName = split[1].split(":")[0].split("\\.")[1];
            String refName = block.getOperandStack().remove(0).getRefId();
            String result = refName + "." + methodName + "(" + String.join(", ", block.getOperandStack().stream().map(s -> s.getRefId()).collect(Collectors.toList()) ) + ")";
            if(comment.endsWith("V")) {
                block.getOperandStack().clear();
                block.getWriter().println(result + ";", block.getLevel());
            } else {
                block.getOperandStack().clear();
                block.getOperandStack().add(new VariableStorage.Variable(result, VariableStorage.PrimitiveType.EXPRESSION));
            }
        }*/
        return true;
    }
}
