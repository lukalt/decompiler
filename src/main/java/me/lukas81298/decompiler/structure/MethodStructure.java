package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.stack.Block;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class MethodStructure extends BlockStructure {

    public MethodStructure(Block block) {
        super(block);
    }

    @Override
    public StructureType getType() {
        return StructureType.METHOD;
    }
}
