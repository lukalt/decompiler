package me.lukas81298.decompiler.util;

/**
 * @author lukas
 * @since 30.11.2017
 */
public class Helpers {

    public static int mergeFirst(int[] d) {
        return (d[0] << 8) + d[1];
    }
}
