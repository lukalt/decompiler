package me.lukas81298.decompiler.bytecode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.atrr.AttributeInfo;

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
}
