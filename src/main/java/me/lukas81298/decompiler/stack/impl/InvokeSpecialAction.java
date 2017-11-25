package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

import java.util.stream.Collectors;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class InvokeSpecialAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        VariableStorage.Variable object = block.getOperandStack().get(0);
        block.getOperandStack().remove(0);
        block.getWriter().println((object.getRefId().equals("this") ? "" : (object.getRefId() + ".")) + "super(" + String.join(", ", block.getOperandStack().stream().map(v -> {
            return v.getRefId();
        }).collect(Collectors.toList())) + ");", block.getLevel());
        block.getOperandStack().clear();
        return true;
    }
}
