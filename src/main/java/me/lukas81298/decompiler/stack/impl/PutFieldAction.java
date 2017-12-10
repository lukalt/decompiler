package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantFieldRefInfo;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class PutFieldAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        ConstantFieldRefInfo fieldRef = block.getConstantPool().get(Helpers.mergeFirst(data), ConstantFieldRefInfo.class);
        StackItem val = block.getStack().pop();
        StackItem ref = block.getStack().pop();
        String s = Helpers.lastSplitItem(fieldRef.getNameAndType().getName(), "\\.");
        block.getWriter().println(ref.getRefId() + "." + s + " = " + val.getRefId() + ";", block.getLevel());
        return true;
    }
}
