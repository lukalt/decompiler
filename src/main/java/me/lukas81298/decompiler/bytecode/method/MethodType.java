package me.lukas81298.decompiler.bytecode.method;

/**
 * @author lukas
 * @since 30.11.2017
 */
public enum MethodType {

    METHOD,
    CONSTRUCTOR,
    STATIC_CONSTRUCTOR;

    public static MethodType byName(String name) {
        switch(name) {
            case "<init>":
                return CONSTRUCTOR;
            case "<clinit>":
                return STATIC_CONSTRUCTOR;
        }
        return METHOD;
    }
}
