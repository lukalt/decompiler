package me.lukas81298.decompiler.util;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
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

    private final TIntObjectMap<Variable> variables = new TIntObjectHashMap<>();

    public void set(int index, Object object, PrimitiveType type) {
        this.variables.put(index, new Variable(object, type));
    }

    public void set(int index, Object object, PrimitiveType type, String name) {
        this.variables.put(index, new Variable(object, type, name));
    }

    public Object getValue(int index) {
        return this.variables.get(index).getValue();
    }

    public PrimitiveType getType(int index) {
        return this.variables.get(index).getType();
    }

    public Variable get(int index) {
        return this.variables.get(index);
    }

    @RequiredArgsConstructor
    @Getter
    public static final class Variable {

        private final Object value;
        private final PrimitiveType type;
        private final String variableName;

        public Variable(Object value, PrimitiveType type) {
            this.value = value;
            this.type = type;
            this.variableName = null;
        }

        public boolean hasName() {
            return this.variableName != null;
        }

        public String getRefId() {
            return this.hasName() ? this.variableName : Objects.toString(this.value);
        }
    }

    @RequiredArgsConstructor
    @Getter
    public enum PrimitiveType {
        INT("int", "i", s -> {
            return Integer.parseInt(s);
        }),
        DOUBLE("double", "d", s-> {
            return Double.parseDouble(s);
        }),
        FLOAT("float", "f", s-> {
            return Float.parseFloat(s);
        }),
        LONG("long", "l", l-> {
            return Long.parseLong(l);
        }),
        BYTE("byte", "b", b-> {
            return Byte.parseByte(b);
        }),
        OBJECT("Object", "a", s -> s); // ik this is not a primitive :D

        private final String label;
        private final String prefix;
        private final Function<String, Object> parse;
    }
}
