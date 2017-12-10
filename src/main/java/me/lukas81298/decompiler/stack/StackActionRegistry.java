package me.lukas81298.decompiler.stack;

import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.atrr.impl.CodeAttribute;
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
        this.register(NegAction.class, "neg", VariableStorage.getPrimitiveTypes()); // multiply with -1

        // constant pushing
        this.register(BiPushAction.class, "bipush"); // add const byte
        this.register(SiPushAction.class, "sipush"); // add const short
        this.register(IConstM1Action.class, "iconst_m1");
        this.register(LdcAction.class, "ldc");
        this.register(LdcAction.class, "ldc_w"); // identical as ldc, just bytecode purposes
        this.register(Ldc2WAction.class, "ldc2_w"); // ldc for double precision
        this.register(ConstAction.class, "const", VariableStorage.getPrimitiveTypes());
        this.register(AConstNullAction.class, "aconst_null");
        this.register(IIncAction.class, "iinc");
        this.register(InstanceOfAction.class, "instanceof");

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
        this.register(SwapAction.class, "swap");
        this.register(NopAction.class, "nop");
        this.register(WideAction.class, "wide");

        this.register(NewAction.class, "new");

        this.register(true, PrimitiveCastAction.class,"f2", VariableStorage.PrimitiveType.INT, VariableStorage.PrimitiveType.DOUBLE, VariableStorage.PrimitiveType.LONG);
        this.register(true, PrimitiveCastAction.class, "d2", VariableStorage.PrimitiveType.FLOAT, VariableStorage.PrimitiveType.INT, VariableStorage.PrimitiveType.LONG);
        this.register(true, PrimitiveCastAction.class, "i2", VariableStorage.PrimitiveType.BYTE, VariableStorage.PrimitiveType.CHAR, VariableStorage.PrimitiveType.DOUBLE, VariableStorage.PrimitiveType.FLOAT, VariableStorage.PrimitiveType.LONG, VariableStorage.PrimitiveType.SHORT);
        this.register(true, PrimitiveCastAction.class, "l2", VariableStorage.PrimitiveType.DOUBLE, VariableStorage.PrimitiveType.FLOAT, VariableStorage.PrimitiveType.INT);

        // array
        this.register(ArrayLengthAction.class, "arraylength");
        this.register(AStoreAction.class, "astore", VariableStorage.getPrimitiveTypesAndObject());
        this.register(NewArrayAction.class, "newarray");
        this.register(ANewArrayAction.class, "anewarray"); // same as before but for object arrays
        this.register(ArrayElementLoadAction.class, "aload", VariableStorage.getPrimitiveTypesAndObject());

        // control structures
        this.register(ReturnVoidAction.class, "return");
        this.register(ReturnAction.class, "return", VariableStorage.getPrimitiveTypesAndObject());
        this.register(new AbstractIfAction("{0} < 0"), "iflt");
        this.register(new AbstractIfAction("{0} > 0"), "ifgt");
        this.register(new AbstractIfAction("{0} == 0"), "ifeq");
        this.register(new AbstractIfAction("{0} >= 0"), "ifge");
        this.register(new AbstractIfAction("{0} <= 0"), "ifle");
        this.register(new AbstractIfAction("{0} != 0"), "ifne");
        this.register(new AbstractIfAction("{0} != null"), "ifnonnull");
        this.register(new AbstractIfAction("{0} == null"), "ifnull");
        this.register(new BiAbstractIfAction("{0} == {1}" ), "if_acmpeq");
        this.register(new BiAbstractIfAction("{0} != {1}" ), "if_acmpne");
        this.register(new BiAbstractIfAction("{0} == {1}" ), "if_icmpeq");
        this.register(new BiAbstractIfAction("{0} < {1}" ), "if_icmpge");
        this.register(new BiAbstractIfAction("{0} <= {1}" ), "if_icmpgt");
        this.register(new BiAbstractIfAction("{0} >= {1}" ), "if_icmple");
        this.register(new BiAbstractIfAction("{0} > {1}" ), "if_icmplt");
        this.register(ThrowAction.class, "throw");
        this.register(GoToAction.class, "goto");
        this.register(GoToAction.class, "goto_w");
        this.register(PopAction.class, "pop");
        this.register(PopAction.class, "pop2");
    }

    public boolean invoke(Block block, CodeAttribute.CodeItem item, int[] data) {
        Entity entity = this.actionMap.get(item.getId());
        if(entity != null) {
            return entity.action.handle(entity.type, data, item.getPc(), block);
        }
        if(item.getId().contains("_")) {
            String[] split = item.getId().split("_");
            entity = this.actionMap.get(split[0]);
            if(entity != null) {
                return entity.action.handle(entity.type, new int[] {Integer.parseInt(split[1])}, item.getPc(), block);
            }
        }
        System.out.println(item.getId() + " not found");
        return true;
    }

    private void register(Class<? extends StackAction> clazz, String tag, VariableStorage.PrimitiveType... types) {
        register(false, clazz, tag, types);
    }


    private void register(boolean reverse, Class<? extends StackAction> clazz, String tag, VariableStorage.PrimitiveType... types) {
        try {
            StackAction action = clazz.newInstance();
            if(types.length == 0) {
                this.actionMap.put(tag, new Entity(action, null));
            } else {
                for(VariableStorage.PrimitiveType type : types) {
                    this.actionMap.put(reverse ? (tag + type.getPrefix()) : (type.getPrefix() + tag), new Entity(action, type));
                }
            }
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void register(StackAction action, String tag) {
        this.actionMap.put(tag, new Entity(action,null));
    }

}
