package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class ALoadAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        if(arg.equals("0")) {
            block.getOperandStack().add(new VariableStorage.Variable("this", VariableStorage.PrimitiveType.OBJECT));
        }
        // todo handle other cases
        return true;
    }

}
