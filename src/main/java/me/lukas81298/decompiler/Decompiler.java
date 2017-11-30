package me.lukas81298.decompiler;

import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.FieldInfo;
import me.lukas81298.decompiler.util.IndentedPrintWriter;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author lukas
 * @since 30.11.2017
 */
public class Decompiler {

    private final ClassFile classFile;
    private final IndentedPrintWriter ouput;

    public Decompiler(ClassFile classFile, OutputStream outputStream) {
        this.classFile = classFile;
        this.ouput = new IndentedPrintWriter(new OutputStreamWriter(outputStream));
    }

    public void decompile() {
        this.ouput.println(this.classFile.getSignature(), 0);
        this.ouput.println();
        for(FieldInfo fieldInfo : this.classFile.getFields()) {

        }
        this.ouput.println("}", 0);
    }
}
