package me.lukas81298.decompiler.stack;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.lukas81298.decompiler.bytecode.atrr.impl.CodeAttribute;

/**
 * @author lukas
 * @since 30.11.2017
 */
@RequiredArgsConstructor
public class BlockProcessor {

    private final static StackActionRegistry stackActionRegistry = new StackActionRegistry();

    private final Block block;

    @Setter
    private int limit =  -1;

    public void processBlock() {
        CodeAttribute.CodeItem item;
        while((item = block.getQueue().poll()) != null) {
            stackActionRegistry.invoke(this.block, item.getId(), item.getAdditionalData(), this.block.getQueue().index());
            if(limit >= 0 && block.getQueue().index() > limit + 1) {
                return;
            }
        }
    }
}
