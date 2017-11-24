package me.lukas81298.decompiler;

import me.lukas81298.decompiler.structure.AbstractStructure;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class Bootstrap {

    public static void main(String[] args)  {
        try {
            AbstractStructure.init();
            Decompiler.decompile(new File("Aufzug.class"), new FileOutputStream("Aufzug.java"));
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

}
