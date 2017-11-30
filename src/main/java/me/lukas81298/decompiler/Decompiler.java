package me.lukas81298.decompiler;

import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.FieldInfo;
import me.lukas81298.decompiler.bytecode.MethodInfo;
import me.lukas81298.decompiler.util.IndentedPrintWriter;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author lukas
 * @since 30.11.2017
 */
public class Decompiler {

    private final ClassFile classFile;
    private final IndentedPrintWriter output;

    public Decompiler(ClassFile classFile, OutputStream outputStream) {
        this.classFile = classFile;
        this.output = new IndentedPrintWriter(new OutputStreamWriter(outputStream));
    }

    public void decompile() {
        this.output.println(this.classFile.getSignature() + " {", 0);
        this.output.println();
        for(FieldInfo fieldInfo : this.classFile.getFields()) {
            this.output.println(fieldInfo.getSignature() + ";", 1);
        }
        if(this.classFile.getFields().length > 0) {
            this.output.println();
        }
        for(MethodInfo methodInfo : this.classFile.getMethods()) {
            this.output.println(methodInfo.getSignature() + " {", 1);
            methodInfo.write(output, 2);
            this.output.println("}", 1);
            this.output.println();
        }
        this.output.println("}", 0);
        this.output.flush();
    }
}
