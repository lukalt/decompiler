package me.lukas81298.decompiler.util;

import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeInfo;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author lukas
 * @since 11.12.2017
 */
public class AttributeCollections {

    public static  <K extends AttributeData> K getAttributeByType(AttributeInfo[] attributes, AttributeType attributeType, Class<K> clazz) {
        for(AttributeInfo attribute : attributes) {
            if(attribute.getType() == attributeType) {
                return clazz.cast(attribute.getData());
            }
        }
        return null;
    }

    public static AttributeInfo[] readAttributeArray(DataInput input, ConstantPool constantPool) throws IOException {
        AttributeInfo[] attr = new AttributeInfo[input.readUnsignedShort()];
        for(int i = 0; i < attr.length; i++) {
            attr[i] = AttributeInfo.read(constantPool, input);
        }
        return attr;
    }
}
