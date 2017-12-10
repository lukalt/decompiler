package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.Constant;
import me.lukas81298.decompiler.bytecode.constant.ConstantString;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class LdcAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        int datum = data.length == 1 ? data[0] : Helpers.mergeFirst(data);
        Constant constant = block.getConstantPool().get(datum);
        block.getStack().push(new StackItem(constant instanceof ConstantString ? ("\"" + constant.toString() + "\"") : constant.toString(), VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}
