package me.lukas81298.decompiler.bytecode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.constant.Constant;

/**
 * @author lukas
 * @since 27.11.2017
 */
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class ClassFile {

    private int magic;
    private int minorVersion, majorVersion;
    private Constant[] constantPool;
    private int accessFlags;

    private String name;
    private String superClass;

    private String[] interfaces;
}
