package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.stack.Block;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class IfStructure extends BlockStructure {

    private final int endOfBlock;

    public IfStructure(Block block, int endOfBlock) {
        super(block);
        this.endOfBlock = endOfBlock;
    }

    @Override
    public StructureType getType() {
        return StructureType.IF;
    }

}
