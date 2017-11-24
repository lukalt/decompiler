package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.DecompileException;
import me.lukas81298.decompiler.IndentedPrintWriter;
import me.lukas81298.decompiler.Parser;

import java.io.PrintWriter;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class FileStructure extends AbstractStructure {

    @Override
    public void parse(IndentedPrintWriter out, Parser parser, int level) throws DecompileException {
        if(!parser.getAndRemove().startsWith("Compiled from")) {
            throw new DecompileException("Invalid start token");
        }
        do {
            String line = parser.getAndRemove();
            if(line.isEmpty()) {
                continue;
            }
            if(line.endsWith("{")) { // class start
                String[] split = line.split(" ");
                String type = split[split.length - 3];
                switch(type) {
                    case "class":
                        ClassStructure classStructure = getStructure(StructureType.CLASS);
                        out.println(line);
                        out.println();
                        classStructure.parse(out,parser,level + 1);
                        out.println("}\n");
                        break;

                    case "interface":
                        throw new DecompileException("Interfaces are not supported yet");
                    case "enum":
                        throw new DecompileException("Enums are not supported yet");
                    default:
                        throw new DecompileException("Invalid object type " + type);
                }

                if(parser.hasNext()) {
                    throw new DecompileException("File is not empty but we are done");
                }
            }
        } while(parser.hasNext());


    }

    @Override
    public StructureType getType() {
        return StructureType.FILE;
    }
}
