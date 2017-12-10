package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantFieldRefInfo;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class GetStaticAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        ConstantFieldRefInfo fieldRef = block.getConstantPool().get(Helpers.mergeFirst(data), ConstantFieldRefInfo.class);
        String s = MethodDescriptor.makeClassName(fieldRef.getClassName() + "." + fieldRef.getNameAndType().getName());
        block.getStack().push(new StackItem(s, VariableStorage.PrimitiveType.OBJECT));
        return true;
    }
}