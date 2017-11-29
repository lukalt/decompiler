package me.lukas81298.decompiler.bytecode.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.ConstantPool;

/**
 * @author lukas
 * @since 27.11.2017
 */
@RequiredArgsConstructor
public class ConstantClassInfo implements Constant {

    @Getter
    private final ConstantPool constantPool;
    private final int name;

    public String getName() {
        return this.constantPool.get(this.name, ConstantUtf8Info.class).getValue();
    }

    @Override
    public ConstantType getType() {
        return ConstantType.CLASS;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
