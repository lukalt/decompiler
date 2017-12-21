package me.lukas81298.decompiler.util;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class VariableStorage {

    @Getter
    private final static PrimitiveType[] primitiveTypes = new PrimitiveType[]{
            PrimitiveType.INT, PrimitiveType.DOUBLE, PrimitiveType.FLOAT, PrimitiveType.BYTE, PrimitiveType.LONG
    };

    @Getter
    private final static PrimitiveType[] primitiveTypesAndObject = new PrimitiveType[]{
            PrimitiveType.INT, PrimitiveType.DOUBLE, PrimitiveType.FLOAT, PrimitiveType.BYTE, PrimitiveType.LONG, PrimitiveType.OBJECT, PrimitiveType.CHAR
    };

    private final TIntObjectMap<StackItem> variables = new TIntObjectHashMap<>();

    public void set(int index, Object object, PrimitiveType type) {
        this.variables.put(index, new StackItem(object, type));
    }

    public void set(int index, Object object, PrimitiveType type, String name) {
        this.variables.put(index, new StackItem(object, type, name));
    }

    public Object getValue(int index) {
        return this.variables.get(index).getValue();
    }

    public PrimitiveType getType(int index) {
        return this.variables.get(index).getType();
    }

    public StackItem get(int index) {
        return this.variables.get(index);
    }

    @RequiredArgsConstructor
    @Getter
    public enum PrimitiveType {
        INT("int", "i", s -> Integer.parseInt(s)),
        DOUBLE("double", "d", s -> Double.parseDouble(s)),
        FLOAT("float", "f", s -> Float.parseFloat(s)),
        LONG("long", "l", l -> Long.parseLong(l)),
        BYTE("byte", "b", b -> Byte.parseByte(b)),
        SHORT("short", "s", s -> Short.parseShort(s)),
        OBJECT("Object", "a", s -> s),
        CHAR("char", "c", c -> c + ""),
        NULL("null", "", s -> s),
        EXPRESSION("Object", "", s -> s),
        INSTANCE_OF_EXPRESSION("instanceof", "", s -> s); // ik this is not a primitive :D

        private final String label;
        private final String prefix;
        private final Function<String, Object> parse;
    }
}
