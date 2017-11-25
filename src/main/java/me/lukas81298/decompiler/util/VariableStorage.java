package me.lukas81298.decompiler.util;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class VariableStorage {

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
        INT("int"),
        DOUBLE("double"),
        FLOAT("float"),
        LONG("lomg"),
        BYTE("byte"),
        OBJECT("Object"); // ik this is not a primitive :D

        private final String label;

        public static PrimitiveType byTag(String s) {
            if(s.isEmpty()) {
                return OBJECT;
            }
            switch(s.charAt(0)) {
                case 'l':
                    return LONG;
                case 'i':
                    return INT;
                case 'd':
                    return DOUBLE;
                case 'f':
                    return FLOAT;
                case 'b':
                    return BYTE;

            }
            return OBJECT; // fallback
        }
    }
}
