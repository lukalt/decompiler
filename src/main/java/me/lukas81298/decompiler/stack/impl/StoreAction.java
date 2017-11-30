package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.atrr.impl.LocalVariableAttribute;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class StoreAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        VariableStorage.Variable element = block.getOperandStack().remove(0);
        int i = data[0];
        String name = "local" + i;
        String typeName = element.getType().getLabel();
        LocalVariableAttribute.LocalVariable local;
        if((local = block.getLocalVariables().get(i)) != null) {
            name = local.getName();
            typeName = local.getDescriptor();
        }
        block.getVariables().set(i, element.getValue(), type, name);
        if(block.getDefinedVariables().add(i)) {
            block.getWriter().println( typeName + " " + name + " = " + element.getRefId() + ";", block.getLevel());
        } else {
            block.getWriter().println(name + " = " + element.getRefId() + ";", block.getLevel());
        }
        return true;
    }
}
