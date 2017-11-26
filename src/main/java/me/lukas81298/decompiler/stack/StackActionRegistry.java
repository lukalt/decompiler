package me.lukas81298.decompiler.stack;

import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.stack.impl.*;
import me.lukas81298.decompiler.util.VariableStorage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class StackActionRegistry {

    @RequiredArgsConstructor
    private final class Entity {

        private final StackAction action;
        private final VariableStorage.PrimitiveType type;
    }

    private final Map<String, Entity> actionMap = new HashMap<>();

    public StackActionRegistry() {
        // variable loading and storing
        this.register(LoadAction.class, "load", VariableStorage.getPrimitiveTypes());
        this.register(StoreAction.class, "store", VariableStorage.getPrimitiveTypesAndObject());
        this.register(ALoadAction.class, "aload");

        // operations between primitives on the stack
        this.register(AddAction.class, "add", VariableStorage.getPrimitiveTypes());
        this.register(SubAction.class, "sub", VariableStorage.getPrimitiveTypes());
        this.register(DivAction.class, "div", VariableStorage.getPrimitiveTypes());
        this.register(MulAction.class, "mul", VariableStorage.getPrimitiveTypes());
        this.register(RemAction.class, "rem", VariableStorage.getPrimitiveTypes()); // mod

        // constant pushing
        this.register(BiPushAction.class, "bipush"); // add const byte
        this.register(SiPushAction.class, "sipush"); // add const short
        this.register(IConstM1Action.class, "iconst_m1");
        this.register(LdcAction.class, "ldc");
        this.register(LdcAction.class, "ldc_w"); // identical as ldc, just bytecode purposes
        this.register(Ldc2WAction.class, "ldc2_w"); // ldc for double precision
        this.register(ConstAction.class, "const", VariableStorage.getPrimitiveTypes());
        this.register(AConstNullAction.class, "aconst_null");

        // method invocation
        this.register(InvokeSpecialAction.class, "invokespecial");
        this.register(InvokeVirtualAction.class, "invokevirtual");
        this.register(InvokeStaticAction.class, "invokestatic");
        this.register(InvokeInterfaceAction.class, "invokeinterface");

        this.register(GetStaticAction.class, "getstatic");
        this.register(GetFieldAction.class, "getfield");
        this.register(PutStaticAction.class, "putstatic");
        this.register(PutFieldAction.class, "putfield");

        this.register(DupAction.class, "dup"); // duplicate head of stack
        this.register(NewAction.class, "new");

        // array
        this.register(ArrayLengthAction.class, "arraylength");
        this.register(AStoreAction.class, "astore", VariableStorage.getPrimitiveTypesAndObject());
        this.register(NewArrayAction.class, "newarray");
        this.register(ANewArrayAction.class, "anewarray"); // same as before but for object arrays
        this.register(ArrayElementLoadAction.class, "aload", VariableStorage.getPrimitiveTypesAndObject());
        // control structures
        this.register(ReturnVoidAction.class, "return");
        this.register(ReturnAction.class, "return", VariableStorage.getPrimitiveTypesAndObject());
    }

    public boolean invoke(Block block, String line) {
        String[] firstSplit = line.split("//");
        String comment = firstSplit.length > 1 ? firstSplit[1].trim() : "";
        String firstPart = firstSplit[0];
        String[] subSplit = firstPart.split(":");
        int lineNumber = Integer.parseInt(subSplit[0].trim());
        String rawAction = subSplit[1].trim();
        String[] actionParts = rawAction.split(" +");
        String action = actionParts[0];
        String arg = actionParts.length == 1 ? "" : actionParts[1];

        Entity entity = this.actionMap.get(action);
        if(entity != null) {
            return entity.action.handle(entity.type, arg, comment, lineNumber, block);
        }
        if(action.contains("_")) {
            String[] split = action.split("_");
            entity = this.actionMap.get(split[0]);
            if(entity != null) {
                return entity.action.handle(entity.type, split[1], comment, lineNumber, block);
            }
        }
        System.out.println("Invalid action " + action + ":" + arg);
        return true;
    }

    private void register(Class<? extends StackAction> clazz, String tag, VariableStorage.PrimitiveType... types) {
        try {
            StackAction action = clazz.newInstance();
            if(types.length == 0) {
                this.actionMap.put(tag, new Entity(action, null));
            } else {
                for(VariableStorage.PrimitiveType type : types) {
                    this.actionMap.put(type.getPrefix() + tag, new Entity(action, type));
                }
            }
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
