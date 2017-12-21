package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.bytecode.atrr.impl.LocalVariableAttribute;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.util.StackItem;
import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class StoreAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        StackItem element = context.getStack().pop();
        int i = data[0];
        String name = "local" + i;
        String typeName = element.getType().getLabel();
        LocalVariableAttribute.LocalVariable local;
        if((local = context.getLocalVariables().get(i)) != null) {
            name = local.getName();
            typeName = local.getDescriptor();
        }
        context.getVariables().set(i, element.getValue(), type, name);
        String value = element.getRefId();
      /*  while(value.startsWith("(") && value.endsWith(")")) {
            value = value.substring(1, value.length() - 1);
        }*/
        typeName = MethodDescriptor.makeClassName(typeName, context.getClassFile());
        if(context.getDefinedVariables().add(i)) {
            context.getWriter().println( typeName + " " + name + " = " + value + ";", context.getLevel());
        } else {
            context.getWriter().println(name + " = " + value + ";", context.getLevel());
        }
        return true;
    }
}
