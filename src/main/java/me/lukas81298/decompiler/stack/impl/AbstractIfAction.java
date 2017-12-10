package me.lukas81298.decompiler.stack.impl;

import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
@RequiredArgsConstructor
public class AbstractIfAction implements StackAction {

    private final String operation;

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        block.getStack().push(new StackItem("if (" + operation.replace("{0}", block.getStack().pop().getRefId()) + ") {", VariableStorage.PrimitiveType.OBJECT));
        /*BlockStructure blockStructure = new IfStructure(new Block(block.getLevel() + 1, block.getVariables(), block.getWriter(), block.getDefinedVariables(), block.getParser()), Integer.parseInt(arg));
        try {
            blockStructure.parse(block.getWriter(), block.getParser(), block.getLevel() + 1);
            block.getWriter().println("}", block.getLevel());
        } catch(DecompileException e) {
            e.printStackTrace();
        }*/
        return true;
    }
}
