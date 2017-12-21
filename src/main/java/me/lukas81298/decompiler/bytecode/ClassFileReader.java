package me.lukas81298.decompiler.bytecode;

import me.lukas81298.decompiler.bytecode.atrr.AttributeInfo;
import me.lukas81298.decompiler.bytecode.constant.Constant;
import me.lukas81298.decompiler.bytecode.constant.ConstantClassInfo;
import me.lukas81298.decompiler.bytecode.constant.ConstantType;
import me.lukas81298.decompiler.bytecode.field.FieldInfo;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import me.lukas81298.decompiler.bytecode.method.MethodInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lukas
 * @since 27.11.2017
 */
public class ClassFileReader {

    public static ClassFile read(InputStream stream) throws IOException {
        DataInputStream input = new DataInputStream(stream);
        ClassFile classFile = new ClassFile();
        classFile.setMagic(input.readInt());
        classFile.setMinorVersion(input.readUnsignedShort());
        classFile.setMajorVersion(input.readUnsignedShort());

        ConstantPool constants = new ConstantPool(input.readUnsignedShort());
        for(int i = 1; i < constants.getSize(); i++) {
            Constant constant = ConstantType.readSingleConstant(input, constants);
            constants.set(i, constant);
            for(int j = 1; j < constant.getSpace(); j++)  {
                constants.set(i + j, constant);
                i++;
            }
        }

        classFile.setConstantPool(constants);
        classFile.setFlags(ClassFlag.fromBitMask(input.readUnsignedShort()));

        classFile.setName(constants.get(input.readUnsignedShort(), ConstantClassInfo.class).getName().replace("/", "."));
        if(classFile.getName().contains(".")) {
            String full = classFile.getName();
            classFile.setName(StringUtils.substringAfterLast(full, "."));
            classFile.setPackageName(StringUtils.substringBeforeLast(full, "."));
        }
        classFile.setSuperClass(MethodDescriptor.makeClassName(constants.get(input.readUnsignedShort(), ConstantClassInfo.class).getName()));

        if(classFile.getFlags().contains(ClassFlag.ACC_INTERFACE) || classFile.getFlags().contains(ClassFlag.ACC_INTERFACE) ||classFile.getFlags().contains(ClassFlag.ACC_INTERFACE) ) {
            return classFile;
        }

        classFile.setInterfaces(new String[input.readUnsignedShort()]);
        for(int i = 0; i < classFile.getInterfaces().length; i++) {
            classFile.getInterfaces()[i] = MethodDescriptor.makeClassName(constants.get(input.readUnsignedShort(), ConstantClassInfo.class).getName());
        }

        classFile.setFields(new FieldInfo[input.readUnsignedShort()]);
        for(int i = 0; i < classFile.getFields().length; i++) {
            classFile.getFields()[i] = FieldInfo.read(constants, input, classFile);
        }

        classFile.setMethods(new MethodInfo[input.readUnsignedShort()]);
        for(int i = 0; i < classFile.getMethods().length; i++) {
            classFile.getMethods()[i] = MethodInfo.read(constants, input, classFile);
        }

        classFile.setAttributes(new AttributeInfo[input.readUnsignedShort()]);
        for(int i = 0; i < classFile.getAttributes().length; i++) {
            classFile.getAttributes()[i] = AttributeInfo.read(constants, input);
        }
        return classFile;
    }
}
