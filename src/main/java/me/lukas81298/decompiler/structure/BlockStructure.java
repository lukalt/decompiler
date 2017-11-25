package me.lukas81298.decompiler.structure;

import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import me.lukas81298.decompiler.exception.DecompileException;
import me.lukas81298.decompiler.util.IndentedPrintWriter;
import me.lukas81298.decompiler.util.Parser;
import me.lukas81298.decompiler.util.VariableStorage;

import java.util.ArrayList;
import java.util.List;

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
        List<VariableStorage.Variable> operandStack = new ArrayList<>();
        VariableStorage variables = new VariableStorage();
        TIntSet defined = new TIntHashSet();
        do {
            String line = parser.getAndRemove();
            if(line.isEmpty()) {
                continue;
            }
            if(line.equals("}")) {
                return;
            }
            String[] firstSplit = line.split("//");
            String comment = firstSplit.length > 1 ? firstSplit[1].trim() : "";
            String firstPart = firstSplit[0];
            String[] subSplit = firstPart.split(":");
            int lineNumber = Integer.parseInt(subSplit[0].trim());
            String rawAction = subSplit[1].trim();
            String[] actionParts = rawAction.split(" +");
            String action = actionParts[0];
            String args = actionParts.length == 1 ? "" : actionParts[1];
            if(action.startsWith("iconst_") || action.startsWith("lconst_") || action.startsWith("dconst_") || action.startsWith("fconst_")) {
                operandStack.add(new VariableStorage.Variable(action.split("_")[1], VariableStorage.PrimitiveType.byTag(action)));
            } else if(action.startsWith("istore_") || action.startsWith("lstore_") || action.startsWith("dstore_") || action.startsWith("fstore_")) {
                VariableStorage.Variable element = operandStack.get(0);
                int variableId = Integer.parseInt(action.split("_")[1]);
                variables.set(variableId, element.getValue(), VariableStorage.PrimitiveType.byTag(action), "local_" + variableId);
                operandStack.clear();
                if(defined.add(variableId)) {
                    out.println(element.getType().getLabel() + " local_" + variableId + " = " + element.getRefId() + ";", level);
                } else {
                    out.println("local_" + variableId + " = " + element.getRefId() + ";", level);
                }
            } else if(action.startsWith("iload_") || action.startsWith("dload_") || action.startsWith("lload_" ) || action.startsWith("fload_") ) {
                int variableId = Integer.parseInt(action.split("_")[1]);
                operandStack.add(variables.get(variableId));
            }
            else {
                switch(action) {
                    case "istore": // store the current primite from the current stack to the specified local variable
                    case "dstore":
                    case "lstore":
                    case "fstore":
                    case "bstore":
                        VariableStorage.Variable element = operandStack.get(0);
                        int i = Integer.parseInt(args);
                        variables.set(i, element.getValue(), VariableStorage.PrimitiveType.byTag(action));
                        operandStack.clear();
                        if(defined.add(i)) {
                            out.println(element.getType().getLabel() + " local_" + args + " = " + element.getRefId() + ";", level);
                        } else {
                            out.println("local_" + args + " = " + element.getRefId() + ";", level);
                        }
                        break;
                    case "iload":
                    case "dload":
                    case "lload":
                    case "fload":
                    case "bload":
                        operandStack.add(variables.get(Integer.parseInt(args)));
                        break;
                    case "iadd":
                    case "dadd":
                    case "ladd":
                    case "fadd":
                    case "badd":
                        VariableStorage.Variable first = operandStack.get(0), second = operandStack.get(1);
                        operandStack.clear();
                        operandStack.add(new VariableStorage.Variable("(" +first.getRefId() + " + " + second.getRefId() + ")", VariableStorage.PrimitiveType.byTag(action)));
                        break;
                    case "isub":
                    case "dsub":
                    case "lsub":
                    case "fsub":
                    case "bsub":
                        first = operandStack.get(0);
                        second = operandStack.get(1);
                        operandStack.clear();
                        operandStack.add(new VariableStorage.Variable("(" + first.getRefId() + " - " + second.getRefId() + ")", VariableStorage.PrimitiveType.byTag(action)));
                        break;
                    case "idiv":
                    case "ddiv":
                    case "ldiv":
                    case "fdiv":
                    case "bdiv":
                        first = operandStack.get(0);
                        second = operandStack.get(1);
                        operandStack.clear();
                        operandStack.add(new VariableStorage.Variable("(" + first.getRefId() + " / " + second.getRefId() + ")", VariableStorage.PrimitiveType.byTag(action)));
                        break;
                    case "irem":
                    case "drem":
                    case "lrem":
                    case "frem":
                    case "brem":
                        first = operandStack.get(0);
                        second = operandStack.get(1);
                        operandStack.clear();
                        operandStack.add(new VariableStorage.Variable("(" + first.getRefId() + " % " + second.getRefId() + ")", VariableStorage.PrimitiveType.byTag(action)));
                        break;
                    case "imul":
                    case "dmul":
                    case "lmul":
                    case "fmul":
                    case "bmul":
                        first = operandStack.get(0);
                        second = operandStack.get(1);
                        operandStack.clear();
                        operandStack.add(new VariableStorage.Variable("(" + first.getRefId() + " * " + second.getRefId() + ")", VariableStorage.PrimitiveType.byTag(action)));
                        break;
                    case "ireturn":
                    case "return":
                        out.println("return;", level);
                        return;
                }
            }

          //  out.println("//" + action + "; " + comment + " @ " + lineNumber, level);
        } while(parser.hasNext());
        throw new DecompileException("Unexpected end of statement collection");
    }

}
