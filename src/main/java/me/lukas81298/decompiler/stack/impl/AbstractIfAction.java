package me.lukas81298.decompiler.stack.impl;

import gnu.trove.set.hash.TIntHashSet;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.BlockProcessor;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
@RequiredArgsConstructor
public class AbstractIfAction implements StackAction {

    protected final String operation;

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        writeSubBlock(this.operation.replace("{0}", block.getStack().pop().getRefId()), block, data, pc);
        return true;
    }

    protected void writeSubBlock(String operand, Block block, int[] data, int pc) {
        boolean isLoop = false;
        block.getWriter().println((isLoop ? "while" : "if") + " (" + operand + ") {", block.getLevel());
        int i = Helpers.mergeFirst(data);
        int target = pc + i;
        BlockProcessor processor = new BlockProcessor(new Block(block.getClassFile(),block.getLevel() + 1, block.getVariables(), block.getWriter(), new TIntHashSet(block.getDefinedVariables()), block.getConstantPool(), block.getLocalVariables(), block.getQueue()));
        processor.setLimit(target);
        processor.processBlock();
        block.getWriter().println("}", block.getLevel());
    }
}
