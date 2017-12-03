package me.lukas81298.decompiler.bytecode.constant;

import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;

/**
 * @author lukas
 * @since 27.11.2017
 */
public class ConstantMethodRefInfo extends FMConstantAbstract {

    public ConstantMethodRefInfo(ConstantPool constantPool, int className, int nameAndType) {
        super(constantPool, className, nameAndType);
    }

    public String getName() {
        return this.getNameAndType().getName();
    }

    public MethodDescriptor getMethodDescriptor(ClassFile classFile) {
        return MethodDescriptor.parse(this.getNameAndType().getDescriptor(), classFile);
    }

    @Override
    public ConstantType getType() {
        return ConstantType.METHOD_REF;
    }
}
