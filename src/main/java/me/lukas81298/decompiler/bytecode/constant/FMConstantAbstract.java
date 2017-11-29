package me.lukas81298.decompiler.bytecode.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.ConstantPool;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
public abstract class FMConstantAbstract implements Constant {

    @Getter
    protected final ConstantPool constantPool;
    protected final int className;
    protected final int nameAndType;

    public ConstantClassInfo getClassName() {
        return this.constantPool.get(this.className, ConstantClassInfo.class);
    }

    public ConstantNameAndType getNameAndType() {
        return this.constantPool.get(this.nameAndType, ConstantNameAndType.class);
    }

    @Override
    public String toString() {
        return this.getClassName() + "." + this.getNameAndType();
    }
}
