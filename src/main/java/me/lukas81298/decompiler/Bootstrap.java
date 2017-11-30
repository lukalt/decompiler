package me.lukas81298.decompiler;

import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.ClassFileReader;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class Bootstrap {

    public static void main(String[] args)  {
        /*try {
            AbstractStructure.init();
            Decompiler.decompile(new File("Test.class"), new FileOutputStream("Test.java"));
        } catch(Throwable t) {
            t.printStackTrace();
        }*/

        try {
            ClassFile classFile = ClassFileReader.read(new FileInputStream("Test.class"));
            new Decompiler(classFile, System.out).decompile();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
