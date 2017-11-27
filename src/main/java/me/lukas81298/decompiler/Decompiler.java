package me.lukas81298.decompiler;

import me.lukas81298.decompiler.structure.AbstractStructure;
import me.lukas81298.decompiler.structure.StructureType;
import me.lukas81298.decompiler.util.IndentedPrintWriter;
import me.lukas81298.decompiler.util.Parser;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class Decompiler {

    private final static File JAVA_BINARY_FOLDER = new File(System.getenv("JAVA_HOME"), "bin");

    static {
        if(!JAVA_BINARY_FOLDER.exists()) {
            System.out.println(JAVA_BINARY_FOLDER.getAbsolutePath() + " was not found");
        }
    }

    public static void decompile(File input, OutputStream outputStream) throws IOException, InterruptedException {
        IndentedPrintWriter writer = new IndentedPrintWriter(new PrintWriter(outputStream));
        writer.println("/*");
        writer.println("  " + input.getAbsoluteFile() + " decompiled at " + new Date());
        writer.println("*/");
        try {
            LinkedList<String> lines = disassemble(input);
            for(String line : lines) {
                System.out.println(line);
            }
            Parser parser = new Parser(lines);
            try {
                AbstractStructure.getStructure(StructureType.FILE).parse(writer, parser, 0);
            } catch(Exception e) {
                writer.println("// Error: " + e.toString());
                e.printStackTrace();
            }
        } finally {
            writer.flush();
        }
    }

    private static LinkedList<String> disassemble(File file) throws IOException, InterruptedException {
        Process process = new ProcessBuilder().directory(JAVA_BINARY_FOLDER).command("javap", "-c", "-p", file.getAbsolutePath()).redirectErrorStream(true).start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        LinkedList<String> output = new LinkedList<>();
        String line;
        while((line = reader.readLine()) != null ) {
            output.add(line);
        }
        reader.close();
        return output;
    }

}
