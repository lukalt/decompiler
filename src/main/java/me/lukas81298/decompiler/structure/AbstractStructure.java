package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.DecompileException;
import me.lukas81298.decompiler.IndentedPrintWriter;
import me.lukas81298.decompiler.Parser;

import java.util.EnumMap;

/**
 * @author lukas
 * @since 24.11.2017
 */
public abstract class AbstractStructure {

    private final static EnumMap<StructureType, AbstractStructure> structures = new EnumMap<>(StructureType.class);

    public static void init() {
        structures.put(StructureType.CLASS, new ClassStructure());
        structures.put(StructureType.FILE, new FileStructure());
        structures.put(StructureType.METHOD, new MethodStructure());
        structures.put(StructureType.CONSTRUCTOR, new ConstructorStructure());
        structures.put(StructureType.STATIC_CONSTRUCTOR, new StaticConstructorStructure());
    }

    @SuppressWarnings("unchecked")
    public static <K extends AbstractStructure> K getStructure(StructureType type) {
        return (K) structures.get(type);
    }

    public abstract void parse(IndentedPrintWriter out, Parser parser, int level) throws DecompileException;

    public abstract StructureType getType();
}
