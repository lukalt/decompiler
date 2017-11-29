package me.lukas81298.decompiler.bytecode;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.procedure.TIntObjectProcedure;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.bytecode.constant.Constant;

/**
 * @author lukas
 * @since 28.11.2017
 */
@RequiredArgsConstructor
public class ConstantPool {

    @Getter
    private final int size;
    private final TIntObjectMap<Constant> constants = new TIntObjectHashMap<>();

    public Constant get(int id) {
        return this.constants.get(id);
    }

    public <K extends Constant> K get(int id, Class<K> clazz) {
        return clazz.cast(this.constants.get(id)); // hopefully this works
    }

    public void set(int id, Constant constant) {
        this.constants.put(id, constant);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        this.constants.forEachEntry(new TIntObjectProcedure<Constant>() {
            @Override
            public boolean execute(int i, Constant constant) {
                builder.append("\n").append(i).append(": ").append(constant.getType().name()).append(" ").append(constant.toString());
                return true;
            }
        });
        return builder.append("\n").toString();
    }
}
