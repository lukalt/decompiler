package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.exception.DecompileException;
import me.lukas81298.decompiler.util.IndentedPrintWriter;
import me.lukas81298.decompiler.util.Parser;
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
                throw new DecompileException(firstLine + " did not end with a simicolon " + parser.available());
            }

            if(firstLine.equals("static {};")) { // static "constructor"
                out.println("static {", level);
                StaticConstructorStructure constructorStructure = getStructure(StructureType.STATIC_CONSTRUCTOR);
                constructorStructure.parse(out, parser, level + 1);
                out.println("}\n", level);
            } else if(!firstLine.endsWith(");")) { // probably field
                out.println(firstLine, level);
            } else { // constructor or method
                String[] split = firstLine.split("\\(")[0].split(" ");
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
                if((split.length - index) <= 1) {
                    // constructor
                    List<String> args = new ArrayList<>();
                    String[] argSplit = firstLine.split("\\(");
                    String[] nameSplit = argSplit[0].split(" ");
                    String className = nameSplit[nameSplit.length - 1];
                    if(argSplit.length > 1) {
                        String argLine = argSplit[1].replace(");", "");
                        int counter = 0;
                        for(String s : argLine.split(", ")) {
                            args.add(s + " c_" + s + "_" + counter);
                            counter++;
                        }
                    }
                    out.println(String.join(" ", modifiers) + " " + className + "(" + String.join(", ", args) + "){", level);
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
                    out.println(String.join(" ", modifiers) + " " + type + " " + name + "(" + String.join(", ", types) + "){", level);
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
