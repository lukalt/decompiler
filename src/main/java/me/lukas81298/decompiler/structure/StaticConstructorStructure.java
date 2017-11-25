package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.exception.DecompileException;
import me.lukas81298.decompiler.util.IndentedPrintWriter;
import me.lukas81298.decompiler.util.Parser;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class StaticConstructorStructure extends BlockStructure {

    @Override
    public void parse(IndentedPrintWriter out, Parser parser, int level) throws DecompileException {
        super.parse(out, parser, level);
    }

    @Override
    public StructureType getType() {
        return StructureType.STATIC_CONSTRUCTOR;
    }
}
