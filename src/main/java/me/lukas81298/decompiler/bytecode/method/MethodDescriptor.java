package me.lukas81298.decompiler.bytecode.method;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.ClassFile;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lukas
 * @since 30.11.2017
 */
@RequiredArgsConstructor
@Getter
public class MethodDescriptor {

    private final String returnType;
    private final String[] argumentTypes;

    public static MethodDescriptor parse(String input, ClassFile classFile) {
        input = input.substring(1);
        String[] split = input.split("\\)");
        String[] argsSplit = split[0].split(",");

        String[] args;
        if(argsSplit[0].isEmpty()) {
            args = new String[0];
        } else {
            args = new String[argsSplit.length];
            for(int i = 0; i < argsSplit.length; i++) {
                args[i] = parseType(argsSplit[i], classFile);
            }
        }
        return new MethodDescriptor(parseType(split[1], classFile), args);
    }

    public static String makeClassName(String fqn, ClassFile classFile) {
        fqn = fqn.replace("/", ".");
        if(fqn.startsWith("java.lang.")) { // automatically imported
            return fqn.substring("java.lang.".length());
        }
        if(classFile != null && fqn.contains(".")) {
            System.out.println(fqn);
            String packageName = StringUtils.substringBeforeLast(fqn, ".");
            String typeName = StringUtils.substringAfterLast(fqn, ".");
            System.out.println(packageName);
            System.out.println(typeName);
            if(packageName.equals(classFile.getPackageName())) {
                return typeName;
            }
            if(classFile.getImportedTypeNames().containsKey(fqn)) {
                return typeName; // check if already imported
            }
            String old = classFile.getImportedTypeNames().get(typeName);
            if(old == null || fqn.equals(old)) {
                classFile.getImports().add(fqn);
                classFile.getImportedTypeNames().put(typeName, fqn);
                return typeName;
            }
        }
        return fqn;
    }

    public static String makeClassName(String s) {
        return makeClassName(s, null);
    }

    public static String parseType(String s, ClassFile classFile) {
        if(s.startsWith("[")) {
            return parseType(s.substring(1), classFile) + "[]"; // add one array dimension
        }
        if(s.startsWith("L")) {
            return makeClassName(s.substring(1).replace(";", ""), classFile);
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
