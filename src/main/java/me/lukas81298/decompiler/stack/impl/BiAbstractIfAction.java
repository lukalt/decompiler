package me.lukas81298.decompiler.stack.impl;

import gnu.trove.set.hash.TIntHashSet;
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
        String operation = this.operation.replace("{0}", block.getStack().pop().getRefId()).replace("{1}", block.getStack().pop().getRefId());

        boolean isLoop = false;
        block.getWriter().println((isLoop ? "while" : "if") + " (" + operation + ") {", block.getLevel());
        int index = block.getQueue().getCounter();
        int i = Helpers.mergeFirst(data);
        int target = index + i;

        BlockProcessor processor = new BlockProcessor(new Block(block.getClassFile(),block.getLevel() + 1, block.getVariables(), block.getWriter(), new TIntHashSet(block.getDefinedVariables()), block.getConstantPool(), block.getLocalVariables(), block.getQueue()));
        processor.setLimit(target);
        processor.processBlock();
        block.getWriter().println("}", block.getLevel());
        return true;
    }
}
