package me.lukas81298.decompiler.instruction;

import me.lukas81298.decompiler.util.VariableStorage;

/**
 * @author lukas
 * @since 25.11.2017
 */
public interface ByteCodeInstruction {

    boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context);
}
