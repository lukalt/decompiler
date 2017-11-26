package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class IIncAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        String[] split = arg.split(" ");
        int var = Integer.parseInt(split[0]);
        int mod = Integer.parseInt(split[1]);
        VariableStorage.Variable v = block.getVariables().get(var);
        block.getVariables().set(var, ((Number)v.getValue()).intValue() + mod, v.getType(), v.getVariableName());
        return true;
    }
}
