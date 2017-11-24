package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.DecompileException;
import me.lukas81298.decompiler.IndentedPrintWriter;
import me.lukas81298.decompiler.Parser;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class StaticConstructorStructure extends BlockStructure {

    @Override
    public void parse(IndentedPrintWriter out, Parser parser, int level) throws DecompileException {
        super.parse(out, parser, level);
        System.out.println("s parsed");
    }

    @Override
    public StructureType getType() {
        return StructureType.STATIC_CONSTRUCTOR;
    }
}
