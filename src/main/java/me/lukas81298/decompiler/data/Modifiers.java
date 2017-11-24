package me.lukas81298.decompiler.data;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author lukas
 * @since 24.11.2017
 */
public class Modifiers {

    private final static Set<String> modifiers = Sets.newHashSet("public","private","protected","final","abstract","static","volatile","default");

    public static boolean isModifier(String input) {
        return modifiers.contains(input);
    }

}
