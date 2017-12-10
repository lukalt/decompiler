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
            stackActionRegistry.invoke(this.block, item, item.getAdditionalData());
            if(this.block.getQueue().available()) {
                if(limit > -1 && this.block.getQueue().peek().getPc() == limit) {
                    System.out.println("RETURN AT " + item.getPc());
                    return;
                }
            }

        }
    }
}
