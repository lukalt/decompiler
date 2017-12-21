package me.lukas81298.decompiler.instruction.impl;

import me.lukas81298.decompiler.instruction.ByteCodeInstruction;
import me.lukas81298.decompiler.instruction.Context;
import me.lukas81298.decompiler.util.Helpers;
import me.lukas81298.decompiler.util.VariableStorage;

import java.util.Arrays;

/**
 * @author lukas
 * @since 26.11.2017
 */
public class GoToAction implements ByteCodeInstruction {

    @Override
    public boolean handle(VariableStorage.PrimitiveType type, int[] data, int pc, Context context) {
        return true;
    }
}
