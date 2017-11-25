package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class NewAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        String[] split = comment.split(" ");
        switch(split[0]) {
            case "class":
                block.getOperandStack().clear();
                block.getOperandStack().add(new VariableStorage.Variable(split[1].replace("/","."), VariableStorage.PrimitiveType.OBJECT));
                break;

                // todo handle other cases ?!
        }
        return true;
    }
}
