package me.lukas81298.decompiler.stack.impl;

import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.BlockProcessor;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
@RequiredArgsConstructor
public class BiAbstractIfAction implements StackAction {

    private final String operation;

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        String operation = this.operation.replace("{0}", block.getOperandStack().remove(0).getRefId()).replace("{1}", block.getOperandStack().remove(0).getRefId());

        boolean isLoop = false;
        block.getWriter().println((isLoop ? "while" : "if") + " (" + operation + ") {", block.getLevel());
        int target = block.getQueue().index() + Helpers.mergeFirst(data);

        BlockProcessor processor = new BlockProcessor(new Block(block.getLevel() + 1, block.getVariables(), block.getWriter(), block.getDefinedVariables(), block.getConstantPool(), block.getLocalVariables(), block.getQueue()));
        processor.setLimit(target);
        System.out.println("limit " + target);
        processor.processBlock();
        block.getWriter().println("}", block.getLevel());
        return true;
    }
}
