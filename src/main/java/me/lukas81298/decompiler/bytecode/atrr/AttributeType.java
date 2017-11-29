package me.lukas81298.decompiler.bytecode.atrr;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.util.BiFunctionException;

import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lukas
 * @since 29.11.2017
 */
@RequiredArgsConstructor
@Getter
public enum AttributeType {

    CONSTANT_VALUE("ConstantValue", null),
    CODE("Code", null),
    STACK_MAP_TABLE("StackMapTable", null),
    EXCEPTIONS("Exceptions", null),
    INNER_CLASSES("InnerClasses", null),
    ENCLOSING_METHOD("EnclosingMethod", null),
    SYNTHETIC("Synthetic", null),
    SIGNATURE("Signature", null),
    SOURCE_FILE("SourceFile", null),
    SOURCE_DEBUG_EXTENSION("SourceDebugExtension", null),
    LINE_NUMBER_TABLE("LineNumberTable", null),
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
