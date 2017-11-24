package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.DecompileException;
import me.lukas81298.decompiler.IndentedPrintWriter;
import me.lukas81298.decompiler.Parser;

/**
 * @author lukas
 * @since 24.11.2017
 */
public abstract class BlockStructure extends AbstractStructure {

    @Override
    public void parse(IndentedPrintWriter out, Parser parser, int level) throws DecompileException {
        if(!parser.getAndRemove().equals("Code:")) {
            throw  new DecompileException("Missing 'Code:'");
        }
        do {
            String line = parser.getAndRemove();
            if(line.equals("}")) {
                return;
            }
            if(line.isEmpty()) {
                out.println("return;", level);
                return;
            }
            String[] firstSplit = line.split("//");
            String comment = firstSplit.length > 1 ? firstSplit[1].trim() : "";
            String firstPart = firstSplit[0];
            String[] subSplit = firstPart.split(":");
            int lineNumber = Integer.parseInt(subSplit[0].trim());
            String action = subSplit[1].trim();
            switch(action) {
                case "ireturn":
                case "return":
                    out.println("return;", level);
                    break;
            }
            out.println(action + "; // " + comment + " @ " + lineNumber, level);
        } while(parser.hasNext());
        throw new DecompileException("Unexpected end of statement collection");
    }

}
