package me.lukas81298.decompiler.util;

import lombok.RequiredArgsConstructor;
import me.lukas81298.decompiler.exception.DecompileException;

import java.util.LinkedList;

/**
 * @author lukas
 * @since 24.11.2017
 */
@RequiredArgsConstructor
public class Parser {

    private final LinkedList<String> lines;

    public boolean hasNext() {
        return !this.lines.isEmpty();
    }

    public String get() throws DecompileException {
        if(!this.hasNext()) {
            throw new DecompileException("Attempt to read empty head of queue");
        }
        return this.lines.getFirst().trim();
    }

    public String getAndRemove() throws DecompileException {
        if(!this.hasNext()) {
            throw new DecompileException("Attempt to read empty head of queue");
        }
        String first = this.lines.getFirst().trim();
        this.lines.removeFirst();
        return first;
    }

    public void removeHeader() {
        this.lines.removeFirst();
    }

    public void add(String s) {
        this.lines.add(s);
    }

    public int available() {
        return this.lines.size();
    }
}
