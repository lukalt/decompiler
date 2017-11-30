package me.lukas81298.decompiler.bytecode.atrr;

import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.atrr.impl.*;
import me.lukas81298.decompiler.bytecode.code.CodeActionTable;
import me.lukas81298.decompiler.bytecode.constant.ConstantClassInfo;
import me.lukas81298.decompiler.bytecode.constant.ConstantUtf8Info;
import me.lukas81298.decompiler.util.BiFunctionException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lukas
 * @since 29.11.2017
 */
@RequiredArgsConstructor
@Getter
public enum AttributeType {

    CONSTANT_VALUE("ConstantValue", (in,c) -> {
        return new ConstantValueAttribute(c.get(in.readUnsignedShort()));
    }),
    CODE("Code", (in,c) -> {
        int maxStack = in.readUnsignedShort();
        int maxLocals = in.readUnsignedShort();
        System.out.println(maxStack);
        System.out.println(maxLocals);
        byte[] code = new byte[in.readInt()];
        in.readFully(code);
        DataInputStream buffer = new DataInputStream(new ByteArrayInputStream(code));
        List<CodeAttribute.CodeItem> codeItemList = new ArrayList<>();
        while(buffer.available() > 0) {
            int id = buffer.readUnsignedByte();
            System.out.println("opcode: " + id + " " + Integer.toHexString(id));
            CodeActionTable.Item item = CodeActionTable.getIdentifierById(id);
            byte[] data = new byte[item.getData()];
            buffer.read(data);
            codeItemList.add(new CodeAttribute.CodeItem(item.getName(), data));
        }
        CodeAttribute.ExceptionItem[] exceptions = new CodeAttribute.ExceptionItem[in.readUnsignedShort()];
        for(int i = 0; i < exceptions.length; i++) {
            exceptions[i] = new CodeAttribute.ExceptionItem(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), c.get(in.readUnsignedShort(), ConstantClassInfo.class));
        }
        AttributeInfo[] attributes = new AttributeInfo[in.readUnsignedShort()];
        for(int i = 0; i < attributes.length; i++) {
            attributes[i] = AttributeInfo.read(c, in);
        }
        return new CodeAttribute(maxStack,maxLocals, codeItemList.toArray(new CodeAttribute.CodeItem[codeItemList.size()]), exceptions, attributes);
    }),

    STACK_MAP_TABLE("StackMapTable", null),
    EXCEPTIONS("Exceptions", (in,c) -> {
        ConstantClassInfo[] exceptions = new ConstantClassInfo[in.readUnsignedShort()];
        for(int i = 0; i < exceptions.length; i++) {
            exceptions[i] = c.get(in.readUnsignedShort(), ConstantClassInfo.class);
        }
        return new ExceptionsAttribute(exceptions);
    }),
    INNER_CLASSES("InnerClasses", null),
    ENCLOSING_METHOD("EnclosingMethod", null),
    SYNTHETIC("Synthetic", null),
    SIGNATURE("Signature", (in,c) -> {
        return new SignatureAttribute(c.getString(in.readUnsignedShort()));
    }),
    SOURCE_FILE("SourceFile", (in,c) -> {
        return new SourceFieldAttribute(c.getString(in.readUnsignedShort()));
    }),
    SOURCE_DEBUG_EXTENSION("SourceDebugExtension", null),
    LINE_NUMBER_TABLE("LineNumberTable", (in,c) -> {
        LineNumberTableAttribute.LineNumberEntry[] e = new LineNumberTableAttribute.LineNumberEntry[in.readUnsignedShort()];
        for(int i = 0; i < e.length; i++) {
            e[i] = new LineNumberTableAttribute.LineNumberEntry(in.readUnsignedShort(), in.readUnsignedShort());
        }
        return new LineNumberTableAttribute(e);
    }),
    LOCAL_VARIABLE_TABLE("LocalVariableTable", null),
    LOCAL_VARIABLE_TYPE_TABLE("LocalVariableTypeTable", null),
    DEPRECATED("Deprecated", null),
    RUNTIME_VISIBLE_ANNOTATIONS("RuntimeVisibleAnnotations", null),
    RUNTIME_INVISIBLE_ANNOTATIONS("RuntimeInvisibleAnnotations", null),
    RUNTIME_VARIABLE_PARAMETER_ANNOTATIONS("RuntimeVisibleParameterAnnotations", null),
    RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS("RuntimeInvisibleParameterAnnotations", null),
    ANNOTATION_DEFAULT("AnnotationDefault", null),
    BOOTSTRAP_METHODS("BootstrapMethods", null);

    private final static Map<String, AttributeType> nameMapping = new HashMap<>();

    static {
        for(AttributeType attributeType : values()) {
            nameMapping.put(attributeType.name, attributeType);
        }
    }

    public static AttributeType byName(String name) {
        return nameMapping.get(name);
    }

    private final String name;
    private final BiFunctionException<DataInput, ConstantPool, AttributeData, IOException> applyFunction;





}
