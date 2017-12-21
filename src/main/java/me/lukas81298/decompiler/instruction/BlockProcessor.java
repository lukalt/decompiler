package me.lukas81298.decompiler.instruction;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.lukas81298.decompiler.bytecode.atrr.impl.CodeAttribute;

/**
 * @author lukas
 * @since 30.11.2017
 */
@RequiredArgsConstructor
public class BlockProcessor {

    private final static InstructionRegistry INSTRUCTION_REGISTRY = new InstructionRegistry();

    private final Context context;

    @Setter
    private int limit =  -1;

    public void processBlock() {
        CodeAttribute.CodeItem item;
        while((item = context.getQueue().poll()) != null) {
            INSTRUCTION_REGISTRY.invoke(this.context, item, item.getAdditionalData());
            if(this.context.getQueue().available()) {
                if(limit > -1 && this.context.getQueue().peek().getPc() == limit) {
                    return;
                }
            }

        }
    }
}
