package me.lukas81298.decompiler;

import me.lukas81298.decompiler.bytecode.ClassFileReader;
import me.lukas81298.decompiler.structure.AbstractStructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
            System.out.println(ClassFileReader.read(new FileInputStream("Test.class")).toString());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
