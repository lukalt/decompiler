package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.DecompileException;
import me.lukas81298.decompiler.IndentedPrintWriter;
import me.lukas81298.decompiler.Parser;
import me.lukas81298.decompiler.data.Modifiers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class ClassStructure extends AbstractStructure {

    @Override
    public void parse(IndentedPrintWriter out, Parser parser, int level) throws DecompileException {
        do {
            String firstLine = parser.getAndRemove();
            if(firstLine.isEmpty()) {
                continue;
            }

            if(firstLine.equals("}")) {
                out.println("}\n", level);
                return; // end of class
            }

            if(!firstLine.endsWith(";")) {
                throw new DecompileException(firstLine + " did not end with a simicolon");
            }

            if(!firstLine.endsWith(");")) { // probably field
                out.println(firstLine, level);
            } else if(firstLine.endsWith("{};")) { // static "constructor"
                StaticConstructorStructure constructorStructure = getStructure(StructureType.STATIC_CONSTRUCTOR);
                constructorStructure.parse(out, parser, level + 1);
                out.println("}\n", level);
            } else { // constructor or method
                String[] split = firstLine.split("\\(\\)")[0].split(" ");
                int index = 0;
                List<String> modifiers = new ArrayList<>();
                for(String s : split) {
                    if(Modifiers.isModifier(s)) {
                        index++;
                        modifiers.add(s);
                    } else {
                        break;
                    }
                }


                if(index == split.length - 1) {
                    // constructor
                    out.println(firstLine, level);
                    ConstructorStructure constructorStructure = getStructure(StructureType.CONSTRUCTOR);
                    constructorStructure.parse(out, parser, level + 1);
                    out.println("}\n", level);
                } else {
                    // method declaration
                    String type = split[index];
                    String[] rawName = split[index + 1].split("\\(");
                    String name = rawName[0];
                    List<String> types = new ArrayList<>();
                    int counter = 0;
                    if(rawName.length > 1) {
                        for(String s : rawName[1].replace(");", "").split(", ")) {
                            types.add(s + " p_" + s + "_" + counter);
                            counter++;
                        }
                    }
                    out.println(String.join(" ", modifiers) + " " + type + " " + name + " (" + String.join(", ", types) + "){" , level);
                    MethodStructure methodStructure = getStructure(StructureType.METHOD);
                    methodStructure.parse(out, parser, level + 1);
                    out.println("}\n", level);
                }

            }

        } while(parser.hasNext());
    }

    @Override
    public StructureType getType() {
        return StructureType.CLASS;
    }
}
