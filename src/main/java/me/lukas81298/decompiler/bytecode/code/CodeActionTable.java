package me.lukas81298.decompiler.bytecode.code;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * See https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html for more information about this
 *
 * @author lukas
 * @since 29.11.2017
 */
public class CodeActionTable {

    private final static TIntObjectMap<Item> byId = new TIntObjectHashMap<>();
    private final static TObjectIntMap<String> byName = new TObjectIntHashMap<>();

    public static Item getIdentifierById(int id) {
        return byId.get(id);
    }

    @RequiredArgsConstructor
    @Getter
    public final static class Item {
        private final String name;
        private final int data;
    }

    static {
        register("aaload", 0x32);
        register("aastore", 0x53);
        register("aconst_null", 0x01);
        register("aload", 0x19, 1);
        register("aload_0", 0x2a);
        register("aload_1", 0x2b);
        register("aload_2", 0x2c);
        register("aload_3", 0x2d);
        register("anewarray", 0xbd, 2);
        register("areturn", 0xb0);
        register("arraylength", 0xbe);
        register("astore", 0x3a, 1);
        register("astore_0", 0x4b);
        register("astore_1", 0x4c);
        register("astore_2", 0x4d);
        register("astore_3", 0x4e);
        register("athrow", 0xbf);
        register("baload", 0x33);
        register("bastore", 0x54);
        register("bipush", 0x10, 1);
        register("breakpoint", 0xca);
        register("caload", 0x34);
        register("castore", 0x55);
        register("checkcast", 0xc0, 2);
        register("d2f", 0x90);
        register("d2i", 0x8e);
        register("d2l", 0x8f);
        register("dadd", 0x63);
        register("daload", 0x31);
        register("dastore", 0x52);
        register("dcmpg", 0x98);
        register("dcmpl", 0x97);
        register("dconst_0", 0x0e);
        register("dconst_1", 0x0f);
        register("ddiv", 0x6f);
        register("dload", 0x18, 1);
        register("dload_0", 0x26);
        register("dload_1", 0x27);
        register("dload_2", 0x28);
        register("dload_3", 0x29);
        register("dmul", 0x6b);
        register("dneg", 0x77);
        register("drem", 0x73);
        register("dreturn", 0xaf);
        register("dstore", 0x39, 1);
        register("dstore_0", 0x47);
        register("dstore_1", 0x48);
        register("dstore_2", 0x49);
        register("dstore_3", 0x4a);
        register("dsub", 0x67);
        register("dup", 0x59);
        register("dup_x1", 0x5a);
        register("dup_x2", 0x5b);
        register("dup2", 0x5c);
        register("dup2_x1", 0x5d);
        register("dup2_x2", 0x5e);
        register("f2d", 0x8d);
        register("f2i", 0x8b);
        register("f2l", 0x8c);
        register("fadd", 0x62);
        register("faload", 0x30);
        register("fastore", 0x51);
        register("fcmpg", 0x96);
        register("fcmpl", 0x95);
        register("fconst_0", 0x0b);
        register("fconst_1", 0x0c);
        register("fconst_2", 0x0d);
        register("fdiv", 0x6e);
        register("fload", 0x17, 1);
        register("fload_0", 0x22);
        register("fload_1", 0x23);
        register("fload_2	", 0x24);
        register("fload_3	", 0x25);
        register("fmul", 0x6a);
        register("fneg", 0x76);
        register("frem", 0x72);
        register("freturn", 0xae);
        register("fstore", 0x38, 1);
        register("fstore_0", 0x43);
        register("fstore_1", 0x44);
        register("fstore_2", 0x45);
        register("fstore_3", 0x46);
        register("fsub", 0x66);
        register("getfield", 0xb4, 2);
        register("getstatic", 0xb2, 2);
        register("goto", 0xa7,2);
        register("goto_w", 0xc8, 4);
        register("i2b", 0x91);
        register("i2c", 0x92);
        register("i2d", 0x87);
        register("i2f", 0x86);
        register("i2l", 0x85);
        register("i2s", 0x93);
        register("iadd", 0x60);
        register("iaload", 0x2e);
        register("iand", 0x7e);
        register("iastore", 0x4f);
        register("iconst_m1", 0x02);
        register("iconst_0", 0x03);
        register("iconst_1", 0x04);
        register("iconst_2", 0x05);
        register("iconst_3", 0x06);
        register("iconst_4", 0x07);
        register("iconst_5", 0x08);
        register("idiv", 0x6c);
        register("if_acmpeq", 0xa5, 2);
        register("if_acmpne", 0xa6, 2);
        register("if_icmpeq", 0x9f, 2);
        register("if_icmpge", 0xa2, 2);
        register("if_icmpgt", 0xa3, 2);
        register("if_icmple", 0xa4, 2);
        register("if_icmplt", 0xa1, 2);
        register("if_icmpne", 0xa0, 2);
        register("ifeq", 0x99, 2);
        register("ifge", 0x9c, 2);
        register("ifgt", 0x9d, 2);
        register("ifle", 0x9e, 2);
        register("iflt", 0x9b, 2);
        register("ifne", 0x9a, 2);
        register("ifnonnull", 0xc7, 2);
        register("ifnull", 0xc6, 2);
        register("iinc", 0x84, 2);
        register("iload", 0x15, 1);
        register("iload_0", 0x1a);
        register("iload_1", 0x1b);
        register("iload_2", 0x1c);
        register("iload_3", 0x1d);
        register("impdep1", 0xfe);
        register("impdep2", 0xff);
        register("imul", 0x68);
        register("ineg", 0x74);
        register("instanceof", 0xc1, 2);
        register("invokedynamic", 0xba, 4);
        register("invokeinterface", 0xb9, 4);
        register("invokespecial", 0xb7, 2);
        register("invokestatic", 0xb8, 2);
        register("invokevirtual", 0xb6, 2);
        register("ior", 0x80);
        register("irem", 0x70);
        register("ireturn", 0xac);
        register("ishl", 0x78);
        register("ishr", 0x7a);
        register("istore", 0x36, 1);
        register("istore_0", 0x3b);
        register("istore_1", 0x3c);
        register("istore_2", 0x3d);
        register("istore_3", 0x3e);
        register("isub", 0x64);
        register("iushr", 0x7c);
        register("ixor", 0x82);
        register("jsr", 0xa8, 2);
        register("jsr_w", 0xc9, 4);
        register("l2d", 0x8a);
        register("l2f", 0x89);
        register("l2i", 0x88);
        register("ladd", 0x61);
        register("laload", 0x2f);
        register("land", 0x7f);
        register("lastore", 0x50);
        register("lcmp", 0x94);
        register("lconst_0", 0x09);
        register("lconst_1", 0x0a);
        register("ldc", 0x12, 1);
        register("ldc_w", 0x13, 2);
        register("ldc2_w", 0x14, 2); // maybe this have to be 4 bytes
        register("ldiv", 0x6d);
        register("lload", 0x16);
        register("lload_0", 0x1e);
        register("lload_1", 0x1f);
        register("lload_2", 0x20);
        register("lload_3", 0x21);
        register("lmul", 0x69);
        register("lneg", 0x75);
        register("lookupswitch", 0xab, 8); // todo make dynamic
        register("lor", 0x81);
        register("lrem", 0x71);
        register("lreturn", 0xad);
        register("lshl", 0x79);
        register("lshr", 0x7b);
        register("lstore", 0x37, 1);
        register("lstore_0", 0x3f);
        register("lstore_1", 0x40);
        register("lstore_2", 0x41);
        register("lstore_3", 0x42);
        register("lsub", 0x65);
        register("lushr", 0x7d);
        register("lxor", 0x83);
        register("monitorenter", 0xc2);
        register("monitorexit", 0xc3);
        register("multianewarray", 0xc5, 3);
        register("new", 0xbb, 2);
        register("newarray", 0xbc, 1);
        register("nop", 0x00);
        register("pop", 0x57);
        register("pop2", 0x58);
        register("putfield", 0xb5);
        register("putstatic", 0xb3);
        register("ret", 0xa9);
        register("return", 0xb1);
        register("saload", 0x35);
        register("sastore", 0x56);
        register("sipush", 0x11, 2);
        register("swap", 0x5f);
        register("tableswitch", 0xaa, 16); // todo make dynamic
        register("wide", 0xc4, 3);
    }

    private static void register(String s, int a, int dataBytes) {
        byId.put(a, new Item(s,dataBytes));
        byName.put(s, a);
    }

    private static void register(String s, int a) {
        register(s,a,0);
    }
}
