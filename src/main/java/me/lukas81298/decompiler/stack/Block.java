package me.lukas81298.decompiler.stack;

import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.util.IndentedPrintWriter;
import me.lukas81298.decompiler.util.Parser;
import me.lukas81298.decompiler.util.VariableStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lukas
 * @since 25.11.2017
 */
@RequiredArgsConstructor
@Getter
public class Block {

    private final int level;
    private final VariableStorage variables;
    private final IndentedPrintWriter writer;
    private final List<VariableStorage.Variable> operandStack = new ArrayList<>();
    private final TIntSet definedVariables;
    private final Parser parser;
    private final ConstantPool constantPool;

    public static Block newBlock(int level, IndentedPrintWriter writer, Parser parser, ConstantPool constantPool) {
        return new Block(level, new VariableStorage(), writer, new TIntHashSet(), parser, constantPool);
    }

}
