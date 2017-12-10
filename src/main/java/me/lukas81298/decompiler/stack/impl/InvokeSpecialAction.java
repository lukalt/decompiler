package me.lukas81298.decompiler.stack.impl;

import me.lukas81298.decompiler.bytecode.constant.ConstantMethodRefInfo;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.stack.StackAction;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class InvokeSpecialAction implements StackAction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Block block) {
        int index = Helpers.mergeFirst(data);
        ConstantMethodRefInfo methodRef = block.getConstantPool().get(index, ConstantMethodRefInfo.class);
        String[] argumentTypes = methodRef.getMethodDescriptor(block.getClassFile()).getArgumentTypes();
        String arguments = MethodDescriptor.parseArgumentSignature(argumentTypes, block.getStack());
        StackItem object = block.getStack().pop();
        if(block.isSuperChecker()) {
            block.getStack().push(new StackItem("new " + object.getRefId() + arguments, VariableStorage.PrimitiveType.EXPRESSION));
        } else if(argumentTypes.length > 0) { // prevent useless super() calls
            block.getWriter().println((!object.getRefId().equals("this") ? (object.getRefId() + ".") : "") + "super" + arguments, block.getLevel());
        }

        block.setSuperChecker(false);
        return true;
    }
}
