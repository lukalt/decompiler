package me.lukas81298.decompiler.bytecode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.atrr.AttributeInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lukas
 * @since 27.11.2017
 */
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class ClassFile {

    private int magic;
    private int minorVersion, majorVersion;
    private ConstantPool constantPool;
    private Set<ClassFlag> accessFlags = new HashSet<>();

    private String name;
    private String superClass;

    private String[] interfaces;
    private FieldInfo[] fields;
    private MethodInfo[] methods;
    private AttributeInfo[] attributes;

    public String getSignature() {
        StringBuilder stringBuilder = new StringBuilder();
        if(this.accessFlags.contains(ClassFlag.ACC_PUBLIC)) {
            stringBuilder.append("public ");
        }
        if(this.accessFlags.contains(ClassFlag.ACC_FINAL)) {
            stringBuilder.append("final ");
        } else if(this.accessFlags.contains(ClassFlag.ACC_ABSTRACT)) {
            stringBuilder.append("abstract ");
        }
        if(this.accessFlags.contains(ClassFlag.ACC_INTERFACE)) {
            stringBuilder.append("interface ");
        } else if(this.accessFlags.contains(ClassFlag.ACC_ENUM)) {
            stringBuilder.append("enum ");
        } else if(this.accessFlags.contains(ClassFlag.ACC_ANNOTATION)) {
            stringBuilder.append("@interface ");
        } else {
            stringBuilder.append("class ");
        }
        stringBuilder.append(name).append(" ");
        if(superClass != null && this.accessFlags.contains(ClassFlag.ACC_SUPER)) {
            stringBuilder.append("extends ").append(superClass.replace("/", "."));
        }
        if(interfaces.length > 0) {
            stringBuilder.append("implements ").append(String.join(", ", Arrays.asList(this.interfaces))).append(" ");
        }
        return stringBuilder.toString();
    }
}
