package me.lukas81298.decompiler.instruction.impl;

import gnu.trove.set.hash.TIntHashSet;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.atrr.impl.CodeAttribute;
import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.BlockProcessor;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.ProcessQueue;
import me.lukas81298.decompiler.util.StructureType;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 26.11.2017
 */
@RequiredArgsConstructor
public class AbstractIfAction implements ByteCodeInstruction {

    protected final String operation;

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        writeSubBlock(this.operation.replace("{0}", context.getStack().pop().getRefId()), context, data, pc);
        return true;
    }

    protected void writeSubBlock(String operand, Context context, int[] data, int pc) {
        int i = Helpers.mergeFirst(data);
        int target = pc + i;
        int elseTarget = -1;
        StructureType type = StructureType.IF;
        ProcessQueue<CodeAttribute.CodeItem> slice = context.getQueue().slice();
        System.out.println(slice.count() + "c");
        CodeAttribute.CodeItem codeItem;
        while((codeItem = slice.poll()) != null) {
            if(codeItem.getId().equals("goto")) {
                int gotoTarget = Helpers.mergeFirst(codeItem.getAdditionalData()) + codeItem.getPc();
                System.out.println("goto " + gotoTarget + " " + target + " ");
                if(gotoTarget < target) {
                    type = StructureType.WHILE;
                } else {
                    CodeAttribute.CodeItem next = slice.peek();
                    if(next != null ) {
                        System.out.println(next.getPc());
                        if(next.getPc() == gotoTarget){
                            type = StructureType.IF_ELSE;
                            elseTarget = codeItem.getPc();
                        }
                    }
                }
                break;
            }
        }
        System.out.println(type.name());
        context.getWriter().println((type == StructureType.WHILE ? "while" : "if") + " (" + operand + ") {", context.getLevel());

        BlockProcessor processor = new BlockProcessor(new Context(context.getClassFile(), context.getLevel() + 1, context.getVariables(), context.getWriter(), new TIntHashSet(context.getDefinedVariables()), context.getConstantPool(), context.getLocalVariables(), context.getQueue()));
        processor.setLimit(target);
        processor.processBlock();
        if(type == StructureType.IF_ELSE) {
            context.getWriter().println("} else {", context.getLevel());
            processor = new BlockProcessor(new Context(context.getClassFile(), context.getLevel() + 1, context.getVariables(), context.getWriter(), new TIntHashSet(context.getDefinedVariables()), context.getConstantPool(), context.getLocalVariables(), context.getQueue()));
            processor.setLimit(elseTarget);
            processor.processBlock();
        }
        context.getWriter().println("}", context.getLevel());
    }
}
