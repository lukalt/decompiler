package me.lukas81298.decompiler.bytecode;

import lombok.RequiredArgsConstructor;

/**
 * @author lukas
 * @since 30.11.2017
 */
@RequiredArgsConstructor
public class MethodDescriptor {

    private final String returnType;
    private final String[] argumentTypes;

    public static MethodDescriptor parse(String input) {
        input = input.substring(1);
        String[] split = input.split("\\)");

        String[] argsSplit = split[0].split(",");
        String[] args = new String[argsSplit.length];
        for(int i = 0; i < argsSplit.length; i++) {
            args[i] = parseType(argsSplit[i]);
        }
        return new MethodDescriptor(parseType(args[1]), args);
    }

    private static String parseType(String s) {
        if(s.startsWith("[")) {
            return parseType(s.substring(1) + "[]");
        }
        if(s.startsWith("L")) {
            return s.substring(1);
        }
        switch(s) {
            case "V":
                return "void";
            case "B":
                return "byte";
            case "C":
                return "char";
            case "D":
                return "double";
            case "F":
                return "float";
            case "I":
                return "int";
            case "J":
                return "long";
            case "S":
                return "short";
            case "Z":
                return "boolean";
        }
        return "unknown";
    }
}
