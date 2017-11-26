package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.stack.Block;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class ConstructorStructure extends BlockStructure {

    public ConstructorStructure(Block block) {
        super(block);
    }

    @Override
    public StructureType getType() {
        return StructureType.CONSTRUCTOR;
    }
}
