package me.lukas81298.decompiler.util;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;

/**
 * @author lukas
 * @since 30.11.2017
 */
@RequiredArgsConstructor
public class ProcessQueue<K> implements Iterable<K> {

    private int index = 0;
    private final K[] items;
    private final Function<Integer, K[]> arrayBuilder;

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

    public Iterator<K> iterator() {
        return Arrays.asList(this.items).iterator();
    }

    public ProcessQueue<K> slice() {
        K[] sl = this.arrayBuilder.apply(this.items.length - index);
        for(int i = 0; i < sl.length; i++) {
            sl[i] = this.items[this.index + i];
        }
        return new ProcessQueue<>(sl, this.arrayBuilder);
    }

    public int count() {
        return this.items.length - this.index;
    }
}
