package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.stack.Block;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class IfStructure extends BlockStructure {

    public IfStructure(Block block, int endOfBlock) {
        super(block, endOfBlock, false);
    }

    @Override
    public StructureType getType() {
        return StructureType.IF;
    }

}
