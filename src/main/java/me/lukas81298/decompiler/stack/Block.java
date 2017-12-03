package me.lukas81298.decompiler.stack;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.ConstantPool;
import me.lukas81298.decompiler.bytecode.atrr.impl.CodeAttribute;
import me.lukas81298.decompiler.bytecode.atrr.impl.LocalVariableAttribute;
import me.lukas81298.decompiler.util.IndentedPrintWriter;
import me.lukas81298.decompiler.util.ProcessQueue;
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

    private final ClassFile classFile;
    private final int level;
    private final VariableStorage variables;
    private final IndentedPrintWriter writer;
    private final List<VariableStorage.Variable> operandStack = new ArrayList<>();
    private final TIntSet definedVariables;
    private final ConstantPool constantPool;
    private final TIntObjectMap<LocalVariableAttribute.LocalVariable> localVariables;
    private final ProcessQueue<CodeAttribute.CodeItem> queue;

    @Setter
    private boolean superChecker = false;

    public static Block newBlock(ClassFile classFile, int level, IndentedPrintWriter writer, ConstantPool constantPool, ProcessQueue<CodeAttribute.CodeItem> queue, TIntObjectMap<LocalVariableAttribute.LocalVariable> localVariables) {
        return new Block(classFile, level, new VariableStorage(), writer, new TIntHashSet(), constantPool, localVariables, queue);
    }

}
