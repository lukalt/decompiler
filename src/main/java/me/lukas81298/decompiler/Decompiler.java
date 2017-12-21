package me.lukas81298.decompiler;

import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.ClassFileReader;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.field.FieldInfo;
import me.lukas81298.decompiler.bytecode.method.MethodInfo;
import me.lukas81298.decompiler.util.IndentedPrintWriter;

import java.io.*;

/**
 * @author lukas
 * @since 30.11.2017
 */
public class Decompiler {

    private final ClassFile classFile;

    private final OutputStream outputStream;

    private final ConstantPool constantPool;

    public Decompiler(ClassFile classFile, OutputStream outputStream, ConstantPool constantPool) {
        this.outputStream = outputStream;
        this.classFile = classFile;
        this.constantPool = constantPool;
    }

    public void decompile() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IndentedPrintWriter buffer = new IndentedPrintWriter(new PrintWriter(byteArrayOutputStream));
        buffer.println(this.classFile.getSignature() + " {", 0);
        buffer.println();
        for(FieldInfo fieldInfo : this.classFile.getFields()) {
            buffer.println(fieldInfo.getSignature(classFile) + ";", 1);
        }
        if(this.classFile.getFields().length > 0) {
            buffer.println();
        }
        for(MethodInfo methodInfo : this.classFile.getMethods()) {
            methodInfo.write(buffer, 1, this.constantPool);
        }
        buffer.println("}", 0);
        buffer.flush();
        PrintWriter printWriter = new PrintWriter(this.outputStream);
        this.classFile.writeHeader(printWriter);
        printWriter.flush();
        this.outputStream.write(byteArrayOutputStream.toByteArray());
        this.outputStream.flush();
    }

    public static void decompile(InputStream inputStream, OutputStream outputStream) throws IOException {
        try {
            ClassFile classFile = ClassFileReader.read(inputStream);
            Decompiler decompiler = new Decompiler(classFile, outputStream, classFile.getConstantPool());
            decompiler.decompile();
        } catch(Throwable t) {
            t.printStackTrace(new PrintStream(outputStream));
            outputStream.flush();
        }
    }

}
