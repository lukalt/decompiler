package me.lukas81298.decompiler;

import org.apache.commons.lang3.StringUtils;

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
            System.out.println(File.separator);
            if(input.getName().endsWith(".jar")) {
                if(!output.exists()) {
                    assert output.mkdir();
                }
                try {
                    JarFile jarFile = new JarFile(input);
                    Enumeration<JarEntry> enumeration = jarFile.entries();
                    while(enumeration.hasMoreElements()) {
                        JarEntry entry = enumeration.nextElement();
                        String name1 = entry.getName();
                        System.out.println(name1);
                        if(name1.endsWith(".class")) {
                            InputStream inputStream = jarFile.getInputStream(entry);
                            String path = StringUtils.substringBeforeLast(name1, "/");
                            String name = StringUtils.substringAfterLast(name1, "/" ).replace(".class", ".java");
                            File dir = new File(output, path);
                            if(!dir.exists()) {
                                dir.mkdirs();
                            }
                            File file = new File(dir, name);
                            Decompiler.decompile(inputStream, new FileOutputStream(file));
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
