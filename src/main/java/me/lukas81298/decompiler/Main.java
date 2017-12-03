package me.lukas81298.decompiler;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author lukas
 * @since 03.12.2017
 */
public class Main {

    public static void main(String[] args) {
        if(args.length == 3) {
            File input = new File(args[0]);
            if(!input.exists()) {
                System.out.println("File " + input.getAbsolutePath() + " does not exist");
                return;
            }
            File output = new File(args[1]);

            if(input.getName().endsWith(".jar")) {
                if(!output.exists()) {
                    assert output.mkdir();
                }
                try {
                    JarFile jarFile = new JarFile(input);
                    Enumeration<JarEntry> enumeration = jarFile.entries();
                    while(enumeration.hasMoreElements()) {
                        JarEntry entry = enumeration.nextElement();
                        if(entry.getName().endsWith(".class")) {
                            InputStream inputStream = jarFile.getInputStream(entry);
                            Decompiler.decompile(inputStream, new FileOutputStream(new File(output, entry.getName())));
                        }
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Decompiler.decompile(new FileInputStream(input), new FileOutputStream(output));
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            switch(args[2].toLowerCase()) {
                case "dir":

                    break;
                case "file":

                    break;
                case "jar":

                    break;
                default:
                    printHelp();
            }
        } else {
            printHelp();
        }
    }

    private static void printHelp() {
        System.out.println("USAGE: java -jar decompiler.jar [file.class|file.jar] [output] [dir|file|jar]");
    }
}
