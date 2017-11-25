package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.exception.DecompileException;
import me.lukas81298.decompiler.util.IndentedPrintWriter;
import me.lukas81298.decompiler.util.Parser;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class InterfaceStructure extends AbstractStructure {

    @Override
    public void parse(IndentedPrintWriter out, Parser parser, int level) throws DecompileException {

    }

    @Override
    public StructureType getType() {
        return StructureType.INTERFACE;
    }
}
