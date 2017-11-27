package me.lukas81298.decompiler.bytecode;

import me.lukas81298.decompiler.bytecode.constant.Constant;
import me.lukas81298.decompiler.bytecode.constant.ConstantType;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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
        Constant[] constants = new Constant[input.readUnsignedShort()];
        for(int i = 0; i < constants.length; i++) {
            constants[i] = ConstantType.readSingleConstant(input,constants);
            System.out.println(Arrays.toString(constants));

        }
        classFile.setConstantPool(constants);
        return classFile;
    }
}
