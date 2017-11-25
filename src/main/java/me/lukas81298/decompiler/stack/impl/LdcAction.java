package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class LdcAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        if(arg.startsWith("\"")) { // handle as string
            block.getOperandStack().add(new VariableStorage.Variable(arg, VariableStorage.PrimitiveType.OBJECT));
        } else if(arg.contains(".")) { // handle as float
            block.getOperandStack().add(new VariableStorage.Variable(Float.parseFloat(arg), VariableStorage.PrimitiveType.FLOAT));
        } else { // handle as int
            block.getOperandStack().add(new VariableStorage.Variable(Integer.parseInt(arg), VariableStorage.PrimitiveType.INT));
        }
        return true;
    }
}
