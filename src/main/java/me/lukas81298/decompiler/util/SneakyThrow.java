package me.lukas81298.decompiler.util;

/**
 * @author lukas
 * @since 27.11.2017
 */
public class SneakyThrow {

    public static <K> K sneaky(Throwable t) {
        throw new RuntimeException(t);
    }
}
