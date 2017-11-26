package me.lukas81298.decompiler.structure;

import me.lukas81298.decompiler.exception.DecompileException;
import me.lukas81298.decompiler.stack.Block;
import me.lukas81298.decompiler.util.IndentedPrintWriter;
import me.lukas81298.decompiler.util.Parser;
import me.lukas81298.decompiler.data.Modifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class ClassStructure extends AbstractStructure {

    @Override
    public void parse(IndentedPrintWriter out, Parser parser, int level) throws DecompileException {
        boolean firstMethod = true;
        do {
            String firstLine = parser.getAndRemove();
            if(firstLine.isEmpty()) {
                continue;
            }

            if(firstLine.equals("}")) {
                return; // end of class
            }

            if(!firstLine.endsWith(";")) {
                throw new DecompileException(firstLine + " did not end with a simicolon " + parser.available());
            }

            if(firstLine.equals("static {};")) { // static "constructor"
                if(firstMethod) {
                    firstMethod = false;
                    out.println();
                }
                out.println("static {", level);
                StaticConstructorStructure constructorStructure = new StaticConstructorStructure(Block.newBlock(level + 1, out, parser));
                constructorStructure.parse(out, parser, level + 1);
                out.println("}\n", level);
            } else if(!firstLine.endsWith(");")) { // probably field
                out.println(firstLine, level);
            } else { // constructor or method
                if(firstMethod) {
                    firstMethod = false;
                    out.println();
                }
                String[] globalSplit = firstLine.split("\\(");
                String s1 = globalSplit[0];
                String[] split = s1.split(" ");
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
                    // method declaration
                    String name = split[index];
                    List<String> types = new ArrayList<>();
                    int counter = 0;
                    for(String s : globalSplit[1].replace(");", "").split(", ")) {
                        if(s.isEmpty() || s.equals(" ")) {
                            continue;
                        }
                        types.add(s + " param" + counter);
                        counter++;
                    }
                    out.println(String.join(" ", modifiers) + " " + name + "(" + String.join(", ", types) + "){", level);
                    ConstructorStructure constructorStructure = new ConstructorStructure(Block.newBlock(level + 1, out, parser));
                    constructorStructure.parse(out, parser, level + 1);
                    out.println("}\n", level);
                } else {
                    // method declaration
                    String type = split[index];
                    String name = split[index + 1];
                    List<String> types = new ArrayList<>();
                    int counter = 0;

                    for(String s : globalSplit[1].replace(");", "").split(", ")) {
                        types.add(s + " param" + counter);
                        counter++;
                    }
                    out.println(String.join(" ", modifiers) + " " + type + " " + name + "(" + String.join(", ", types) + "){", level);
                    MethodStructure methodStructure = new MethodStructure(Block.newBlock(level + 1, out, parser));
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
