package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class BiAbstractIfAction extends AbstractIfAction {

    public BiAbstractIfAction(String operation) {
        super(operation);
    }

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        String operation = this.operation.replace("{0}", block.getStack().pop().getRefId()).replace("{1}", block.getStack().pop().getRefId());
        writeSubBlock(operation, block, data, pc);
        return true;
    }
}
