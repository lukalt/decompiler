package me.lukas81298.decompiler.stack.impl;

import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.exception.DecompileException;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.structure.BlockStructure;
import me.lukas81298.decompiler.structure.IfStructure;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
@RequiredArgsConstructor
public class BiAbstractIfAction implements StackAction {

    private final String operation;

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, String arg, String comment, int lineNumber, Block block) {
        String operation = this.operation.replace("{0}", block.getOperandStack().remove(0).getRefId()).replace("{1}", block.getOperandStack().remove(0).getRefId());
        block.getWriter().println("if (" + operation + ") {", block.getLevel());
        BlockStructure blockStructure = new IfStructure(new Block(block.getLevel() + 1, block.getVariables(), block.getWriter(), block.getDefinedVariables(), block.getParser()), Integer.parseInt(arg));
        try {
            blockStructure.parse(block.getWriter(), block.getParser(), block.getLevel() + 1);
            block.getWriter().println("}", block.getLevel());
        } catch(DecompileException e) {
            e.printStackTrace();
        }
        return true;
    }
}
