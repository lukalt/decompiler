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
        String value = element.getRefId();
        while(value.startsWith("(") && value.endsWith(")")) {
            value = value.substring(1, value.length() - 1);
        }
        if(block.getDefinedVariables().add(i)) {
            block.getWriter().println( typeName + " " + name + " = " + value + ";", block.getLevel());
        } else {
            block.getWriter().println(name + " = " + value + ";", block.getLevel());
        }
        return true;
    }
}
