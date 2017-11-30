package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class InvokeStaticAction implements StackAction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        /*String[] split = comment.split(" ");
        if(split[0].equals("Method")) {
            StringBuilder methodRef = new StringBuilder(split[1].split(":")[0].replace("/", ".")).append("(");
            boolean first = true;
            for(VariableStorage.Variable variable : block.getOperandStack()) {
                if(first) {
                    first = false;
                } else {
                    methodRef.append(", ");
                }
                methodRef.append(variable.getRefId());
            }
            methodRef.append(")");
            block.getOperandStack().clear();
            if(split[1].endsWith("V")) {
                block.getWriter().println(methodRef.append(";").toString(), block.getLevel());
            } else {
                block.getOperandStack().add(new VariableStorage.Variable(methodRef.toString(), VariableStorage.PrimitiveType.OBJECT));
            }
        }*/
        return true;
    }
}
