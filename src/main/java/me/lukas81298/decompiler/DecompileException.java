package me.lukas81298.decompiler;

/**
 * @author lukas
 * @since 24.11.2017
 */

public class DecompileException extends Exception {

    public DecompileException() {
    }

    public DecompileException(String message) {
        super(message);
    }

    public DecompileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecompileException(Throwable cause) {
        super(cause);
    }

    public DecompileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
