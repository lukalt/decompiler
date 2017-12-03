package me.lukas81298.decompiler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class TestRunner {

    public static void main(String[] args)  {
        try {
            Decompiler.decompile(new FileInputStream("Test.class"), new FileOutputStream("Test_decompiled.java"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
