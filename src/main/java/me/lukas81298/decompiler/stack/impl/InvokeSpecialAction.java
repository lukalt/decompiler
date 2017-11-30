package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class InvokeSpecialAction implements StackAction {


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        VariableStorage.Variable object = block.getOperandStack().remove(0);
        VariableStorage.Variable typeTag = block.getOperandStack().remove(0);
        /*if(typeTag.getRefId().contains(".") || !comment.contains("java/lang")) {
            block.getOperandStack().remove(0);

            String s = "new " + typeTag.getRefId() + "(" + String.join(", ", block.getOperandStack().stream().map(v -> {
                return v.getRefId();
            }).collect(Collectors.toList())) + ")";
            block.getOperandStack().clear();
            block.getOperandStack().add(new VariableStorage.Variable(s, VariableStorage.PrimitiveType.EXPRESSION));
        } else {
            block.getWriter().println((object.getRefId().equals("this") ? "" : (object.getRefId() + ".")) + "super(" + String.join(", ", block.getOperandStack().stream().map(v -> {
                return v.getRefId();
            }).collect(Collectors.toList())) + ");", block.getLevel());
            block.getOperandStack().clear();
        }*/

        /*
         List<String> args = new ArrayList<>();
                if(!split[1].contains(":()")) {
                    for(int i = 0; i < StringUtils.countMatches(split[1], ",") + 1; i++) {
                        args.add(block.getOperandStack().remove(0).getRefId());
                    }
                }
         */
        return true;
    }
}
