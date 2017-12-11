package me.lukas81298.decompiler.bytecode.atrr.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.lukas81298.decompiler.bytecode.ClassFile;
import me.lukas81298.decompiler.bytecode.atrr.AttributeData;
import me.lukas81298.decompiler.bytecode.atrr.AttributeType;
import me.lukas81298.decompiler.bytecode.method.MethodDescriptor;

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
