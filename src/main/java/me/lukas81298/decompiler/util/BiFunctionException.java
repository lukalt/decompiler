package me.lukas81298.decompiler.util;

/**
 * @author lukas
 * @since 29.11.2017
 */
public interface BiFunctionException<A,B,C,D extends Throwable> {

    C apply(A a, B b ) throws D;
}
