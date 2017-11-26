package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.exception.DecompileException;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class ReturnVoidAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        try {
            if(!block.getParser().hasNext() || !block.getParser().get().isEmpty() ) {
                block.getWriter().println("return;", block.getLevel());
            }
        } catch(DecompileException e) {
            e.printStackTrace();
        }
        return false; // end code block
    }
}
