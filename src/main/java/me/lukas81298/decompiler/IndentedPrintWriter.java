package me.lukas81298.decompiler;

import com.google.common.base.Strings;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Objects;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class IndentedPrintWriter extends PrintWriter {

    public IndentedPrintWriter(Writer out) {
        super(out);
    }

    public void println(String string, int indentionLevel) {
        this.println(this.indent(indentionLevel) + string);
    }

    public void println(Object object, int indentionLevel) {
        this.println(this.indent(indentionLevel) + Objects.toString(object));
    }

    public void println(boolean bool, int indentionLevel) {
        this.println(this.indent(indentionLevel) + (bool ? "true" : "false" ));
    }

    public void println(int i, int indentionLevel) {
        this.println(this.indent(indentionLevel) + i);
    }

    private String indent(int i) {
        return Strings.repeat("  ", i);
    }
}
