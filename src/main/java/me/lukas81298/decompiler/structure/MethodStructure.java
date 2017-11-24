package me.lukas81298.decompiler.structure;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class MethodStructure extends BlockStructure {

    @Override
    public StructureType getType() {
        return StructureType.METHOD;
    }
}
