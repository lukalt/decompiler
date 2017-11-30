package me.lukas81298.decompiler.stack.impl;

import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
@RequiredArgsConstructor
public class BiAbstractIfAction implements StackAction {

    private final String operation;


    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
       String operation = this.operation.replace("{0}", block.getOperandStack().remove(0).getRefId()).replace("{1}", block.getOperandStack().remove(0).getRefId());

        //BlockStructure blockStructure = new IfStructure(new Block(block.getLevel() + 1, block.getVariables(), block.getWriter(), block.getDefinedVariables(), block.getParser()), Integer.parseInt(arg));
        boolean isLoop = false;
        block.getWriter().println((isLoop ? "while" : "if") + " (" + operation + ") {", block.getLevel());
       /* try {
            blockStructure.parse(block.getWriter(), block.getParser(), block.getLevel() + 1);
            block.getWriter().println("}", block.getLevel());
        } catch(DecompileException e) {
            e.printStackTrace();
        }
        System.out.println("d");
        */
        return true;
    }
}
