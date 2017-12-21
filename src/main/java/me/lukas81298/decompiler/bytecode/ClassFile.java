package me.lukas81298.decompiler.bytecode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeInfo;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;
import me.lukas81298.decompiler.bytecode.atrr.impl.SignatureAttribute;
import me.lukas81298.decompiler.bytecode.field.FieldInfo;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.bytecode.method.MethodInfo;
import me.lukas81298.decompiler.util.AttributeCollections;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

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
    private String packageName = "";
    private String superClass;

    private String[] interfaces;
    private FieldInfo[] fields;
    private MethodInfo[] methods;
    private AttributeInfo[] attributes;

    private final Set<String> imports = new HashSet<>();
    // maps the name of the type to its fqn
    private final Map<String, String> importedTypeNames = new HashMap<>();

    public String getSignature() {
        StringBuilder stringBuilder = new StringBuilder();
        String signatureString = "";
        SignatureAttribute signatureAttribute;
        if((signatureAttribute = AttributeCollections.getAttributeByType(this.attributes, AttributeType.SIGNATURE, SignatureAttribute.class)) != null) {
            signatureString = signatureAttribute.getClassGenericString(this);
        }
        if(this.accessFlags.contains(ClassFlag.ACC_PUBLIC)) {
            stringBuilder.append("public ");
        }
        if(this.accessFlags.contains(ClassFlag.ACC_FINAL)) {
            stringBuilder.append("final ");
        } else if(this.accessFlags.contains(ClassFlag.ACC_ABSTRACT)) {
            stringBuilder.append("abstract");
        }
        if(this.accessFlags.contains(ClassFlag.ACC_INTERFACE)) {
            stringBuilder.append("interface");
        } else if(this.accessFlags.contains(ClassFlag.ACC_ENUM)) {
            stringBuilder.append("enum ");
        } else if(this.accessFlags.contains(ClassFlag.ACC_ANNOTATION)) {
            stringBuilder.append("@interface");
        } else {
            stringBuilder.append("class");
        }
        stringBuilder.append(" ").append(this.name).append(signatureString);
        if(superClass != null && this.accessFlags.contains(ClassFlag.ACC_SUPER) && !this.superClass.equals("Object")) {
            stringBuilder.append(" extends ").append(MethodDescriptor.makeClassName(this.superClass, this));
        }
        if(interfaces.length > 0) {
            stringBuilder.append(" implements ").append(String.join(", ", Arrays.stream(this.interfaces).map(s -> MethodDescriptor.makeClassName(s, this)).collect(Collectors.toList()))).append(" ");
        }
        return stringBuilder.toString();
    }

    public void writeHeader(PrintWriter output) {
        if(!this.packageName.isEmpty()) {
            output.println("package " + this.packageName + ";\n");
        }
        List<String> imports = new ArrayList<>(this.imports);
        imports.sort(String.CASE_INSENSITIVE_ORDER);
        for(String anImport : imports) {
            output.println("import " + anImport + ";");
        }
        if(!this.imports.isEmpty()) {
            output.println();
        }

    }

}
