package me.lukas81298.decompiler.bytecode.method;

import gnu.trove.list.TCharList;
import gnu.trove.list.array.TCharArrayList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.util.StackItem;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

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
        input = input.substring(1); // remove initial '('
        String[] split = input.split("\\)");
        String[] args;
        if(split[0].isEmpty()) {
            args = new String[0];
        } else {
            List<String> out = new ArrayList<>(parseArgumentTypes(split[0], classFile));
            out.remove("");
            args = out.toArray(new String[out.size()]);
        }
        return new MethodDescriptor(parseType(split[1], classFile), args);
    }

    public static String makeClassName(String fqn, ClassFile classFile) {
        fqn = fqn.replace("/", ".");
        if(fqn.startsWith("java.lang.")) { // automatically imported
            return fqn.substring("java.lang.".length());
        }
        if(classFile != null && fqn.contains(".")) {
            String packageName = StringUtils.substringBeforeLast(fqn, ".");
            String typeName = StringUtils.substringAfterLast(fqn, ".");
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

    private static List<String> parseArgumentTypes(String s, ClassFile classFile) {
        Queue<Character> queue = new LinkedList<>();
        for(char q : s.toCharArray()) {
            queue.add(q);
        }
        ArrayList<String> list = new ArrayList<>();
        parseArgumentTypes(queue, list, classFile );
        return list;
    }

    private static void parseArgumentTypes(Queue<Character> queue, List<String> list, ClassFile classFile) {
        if(queue.isEmpty()) {
            return;
        }
        list.add(parseArgumentType(queue, classFile));
        parseArgumentTypes(queue, list, classFile);
    }

    private static String parseArgumentType(Queue<Character> queue, ClassFile classFile) {
        char firstToken = queue.poll();
        if(firstToken == '[') {
            return parseArgumentType(queue, classFile) + "[]";
        } else if(firstToken == 'L') {
            TCharList li = new TCharArrayList();
            char c;
            while((c = queue.poll()) != ';') {
                li.add(c);
            }
            return makeClassName(new String(li.toArray()), classFile);
        } else {
            return getType(firstToken);
        }
    }

    public static String parseType(String s, ClassFile classFile) {
        char firstToken = s.charAt(0);
        if(firstToken == '[') {
            return parseType(s.substring(1), classFile) + "[]"; // add one array dimension
        }
        if(firstToken == 'L' || firstToken == 'T') { // T => generic type
            return makeClassName(s.substring(1).replace(";", ""), classFile);
        }
        return getType(firstToken);
    }

    private static String getType(char i) {
        switch(i) {
            case 'V':
                return "void";
            case 'B':
                return "byte";
            case 'C':
                return "char";
            case 'D':
                return "double";
            case 'F':
                return "float";
            case 'I':
                return "int";
            case 'J':
                return "long";
            case 'S':
                return "short";
            case 'Z':
                return "boolean";
        }
        return "unknown";
    }

    public static String parseArgumentSignature(String[] argumentTypes, Stack<StackItem> stack) {
        StringBuilder argumentBuilder = new StringBuilder("(");
        StackItem[] argumentItems = new StackItem[argumentTypes.length];
        for(int i = 1; i <= argumentTypes.length; i++) {
            argumentItems[argumentTypes.length - i] = stack.pop();
        }
        for(int i = 0; i < argumentTypes.length; i++) {
            if(i > 0) {
                argumentBuilder.append(", ");
            }
            argumentBuilder.append(argumentItems[i].getRefId());
        }
        argumentBuilder.append(")");
        return argumentBuilder.toString();
    }


}
