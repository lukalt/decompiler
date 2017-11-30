package me.lukas81298.decompiler.bytecode.atrr;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.atrr.impl.*;
import me.lukas81298.decompiler.bytecode.code.CodeActionTable;
import me.lukas81298.decompiler.bytecode.constant.ConstantClassInfo;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
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
        byte[] code = new byte[in.readInt()];
        in.readFully(code);
        DataInputStream buffer = new DataInputStream(new ByteArrayInputStream(code));
        List<CodeAttribute.CodeItem> codeItemList = new ArrayList<>();
        boolean wide = false;
        while(buffer.available() > 0) {
            int id = buffer.readUnsignedByte();
            CodeActionTable.Item item = CodeActionTable.getIdentifierById(id);
            int[] data = new int[item.getData()];
            if(wide) {
                for(int i = 0; i < data.length; i++) {
                    data[i] = buffer.readUnsignedShort();
                }
                wide = false;
            } else {
                for(int i = 0; i < data.length; i++) {
                    data[i] = buffer.readUnsignedByte();
                }
            }
            codeItemList.add(new CodeAttribute.CodeItem(item.getName(), data));
            if(id == 0xC4) {
                wide = true;
            }
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
        return new SourceFileAttribute(c.getString(in.readUnsignedShort()));
    }),
    SOURCE_DEBUG_EXTENSION("SourceDebugExtension", null),
    LINE_NUMBER_TABLE("LineNumberTable", (in,c) -> {
        LineNumberTableAttribute.LineNumberEntry[] e = new LineNumberTableAttribute.LineNumberEntry[in.readUnsignedShort()];
        for(int i = 0; i < e.length; i++) {
            e[i] = new LineNumberTableAttribute.LineNumberEntry(in.readUnsignedShort(), in.readUnsignedShort());
        }
        return new LineNumberTableAttribute(e);
    }),
    LOCAL_VARIABLE_TABLE("LocalVariableTable", (in,c) -> {
        final TIntObjectMap<LocalVariableAttribute.LocalVariable> locals = new TIntObjectHashMap<>();
        final int length = in.readUnsignedShort();
        for(int i = 0; i < length; i++) {
            LocalVariableAttribute.LocalVariable localVariable = new LocalVariableAttribute.LocalVariable();
            localVariable.setStartPc(in.readUnsignedShort());
            localVariable.setLength(in.readUnsignedShort());
            localVariable.setName(c.get(in.readUnsignedShort()).toString());
            localVariable.setDescriptor(MethodDescriptor.parseType(c.get(in.readUnsignedShort()).toString()));
            localVariable.setIndex(in.readUnsignedShort());
            locals.put(localVariable.getIndex(), localVariable);
        }
        return new LocalVariableAttribute(locals);
    }),
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
    private final BiFunctionException<DataInputStream, ConstantPool, AttributeData, IOException> applyFunction;





}
