package me.lukas81298.decompiler.instruction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class Test<K,V extends List> {

    private static int a = 0;

    private int k;
    private int fieldA, fieldB;
    private List<K> fieldOfTypeK;
    private Collection collection;

    public Test(int k, int fieldA, int fieldB, Collection collection) {
        this.k = k;
        this.fieldA = fieldA;
        this.fieldB = fieldB;
        this.collection = collection;
        this.fieldOfTypeK = new ArrayList<>();
        this.fieldOfTypeK.addAll(collection);
    }

    private K test(V arg1, boolean b, Object h, List<V> list) {
        return this.fieldOfTypeK.get(0);
    }

    private void testVarArgs(int a, int... b) {
        for(int i : b) {
            a += i;
        }
    }

    private void testNoVarArgs(int a, int[] b) {
        testVarArgs(a, b);
        testNoVarArgs(a, b[0]);
        testNoVarArgs(a, b[1]);
        if(this.collection instanceof List) {
            testNoVarArgs(a, b[2]);
        }
    }

    private void testNoVarArgs(int a, int b) {

    }

    @Deprecated
    public void a(int a, int b, int c, Collection h) {
        b = c;
        a = b;
        this.collection = h;
        this.k = 0;
        this.fieldA = b;
        this.fieldB = a;
    }

    public static void main(String[] args) {

        int i = 118;
        int j = 1;
        int d = i + j;
        int e = d - i;
        Object object = new Object();
        System.out.println(object);
        Object object2 = new Object();
        int f = i * e;

        long s = 1324567L;

        d = f % d;
        e = d * e;

        System.out.println(e);

        a = d;

        Test test = new Test(i, j, e, null);
        test.k = j;

        int m = test.k;
        if(m < i) {
            System.exit(e);
        } else {
            System.exit(f);
        }

        int[] array = new int[2];
        array[0] = 1;
        array[1] = d;

        j = array[0];
        int length = array.length;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        stringBuilder.append("b");

        List list = new ArrayList();
        list.add(stringBuilder.toString());
        list.add(object.toString());

        new Consumer() {

            @Override
            public void accept(Object o) {
                System.out.println(o);
                System.out.println(list);
            }
        };
        test.a(e, d, j, list);

        while(j < i) {
            j++;
        }
    }

}
