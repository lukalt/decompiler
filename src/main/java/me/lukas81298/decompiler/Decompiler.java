package me.lukas81298.decompiler;

import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.field.FieldInfo;
import me.lukas81298.decompiler.bytecode.method.MethodInfo;
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
    private final ConstantPool constantPool;

    public Decompiler(ClassFile classFile, OutputStream outputStream, ConstantPool constantPool) {
        this.classFile = classFile;
        this.constantPool = constantPool;
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
            methodInfo.write(this.output, 1, this.constantPool);
        }
        this.output.println("}", 0);
        this.output.flush();
    }
}
