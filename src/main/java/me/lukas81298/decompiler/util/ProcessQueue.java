package me.lukas81298.decompiler.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author lukas
 * @since 30.11.2017
 */
@RequiredArgsConstructor
public class ProcessQueue<K> {

    private int index = 0;
    private final K[] items;

    @Setter
    @Getter
    private int counter = 0;

    public K poll() {
        if(this.index >= this.items.length) {
            return null;
        }
        K k = this.items[this.index];
        this.index++;
        return k;
    }

    public K peek() {
        if(this.index >= this.items.length) {
            return null;
        }
        return this.items[this.index];
    }

    public boolean available() {
        return this.index < this.items.length;
    }

    public int index() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
