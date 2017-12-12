package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.util.StackItem;
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
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        StackItem first = context.getStack().pop();
        StackItem second = context.getStack().pop();
        String operation = this.operation.replace("{0}", first.getRefId()).replace("{1}", second.getRefId());
        writeSubBlock(operation, context, data, pc);
        return true;
    }
}
