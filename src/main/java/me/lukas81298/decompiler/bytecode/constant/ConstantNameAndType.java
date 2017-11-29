package me.lukas81298.decompiler.bytecode.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.ConstantPool;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
@Getter
public class ConstantNameAndType implements Constant {

    private final ConstantPool constantPool;
    private final int name;
    private final int descriptor;

    public String getName() {
        return this.constantPool.get(this.name, ConstantUtf8Info.class).getValue();
    }

    public String getDescriptor() {
        return this.constantPool.get(this.descriptor, ConstantUtf8Info.class).getValue();
    }

    @Override
    public ConstantType getType() {
        return ConstantType.NAME_AND_TYPE;
    }

    @Override
    public String toString() {
        return this.getName() + "." + this.getDescriptor();
    }
}
