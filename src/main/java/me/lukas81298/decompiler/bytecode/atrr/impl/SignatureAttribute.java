package me.lukas81298.decompiler.bytecode.atrr.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lukas
 * @since 29.11.2017
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SignatureAttribute extends AttributeData {

    private final String signature;

    public String getMethodGenericString(ClassFile classFile) {
        return this.signature; // todo parse stuff maybe we have to change the return type to support args
    }

    public String getFieldGenericString(ClassFile classFile) {
        /* Ljava/util/List<TK;>; */
        String s = StringUtils.substringBeforeLast(StringUtils.substringAfter(this.signature,"<"), ">");
        String[] split = s.split(";");
        List<String> out = new ArrayList<>();
        for(String s1 : split) {
            if(!s1.isEmpty()) {
                out.add(MethodDescriptor.parseType(s1, classFile));
            }
        }
        return out.isEmpty() ? "" : ("<" + String.join(", ", out) + ">");
    }

    public String getClassGenericString(ClassFile classFile) {
        /* Signature: <K:Ljava/lang/Object;V::Ljava/util/List;>Ljava/lang/Object;*/

        String first = this.signature.substring(1).split(">")[0]; // omit type
        String[] types = first.split(";");
        List<String> out = new ArrayList<>();
        System.out.println(Arrays.toString(types));
        for(String type : types) {
            if(type.isEmpty()) {
                continue;
            }
            String[] split = type.split(":");
            String name = split[0];
            for(int i = 1; i < split.length; i++) {
                if(split[i].isEmpty()) {
                    continue;
                }
                if(split[i].equals("Ljava/lang/Object")) {
                    out.add(name); // extends Object not needed
                } else {
                    out.add(name + " extends " + MethodDescriptor.parseType(split[i], classFile));
                }
            }

        }
        return out.isEmpty() ? "" : ("<" + String.join(", ", out) + ">");
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SIGNATURE;
    }
}
