package me.lukas81298.decompiler.stack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test {

    private static int a;
    private int k;
    private int fieldA;
    private int fieldB;
    private Collection collection;

    public Test(int k, int fieldA, int fieldB, Collection collection) {
        this.k = k;
        this.fieldA = fieldA;
        this.fieldB = fieldB;
        this.collection = collection;
    }

    public void a(int a, int b, int c, Collection h) {
        int b = c;
        int a = b;
        this.collection = h;
        this.k = 0;
        this.fieldA = b;
        this.fieldB = a;
    }

    public static void main(String[] args) {
        int i = 118;
        int j = 1;
        int d = j + i;
        int e = i - d;
        Object object = new Object();
        System.out.println(object);
        Object object2 = new Object();
        int f = e * i;
        long s = 1324567;
        d = d % f;
        e = e * d;
        System.out.println(e);
        Test.a = d;
        Test test = new Test(i, j, e, null);
        test.k = j;
        int m = test.k;
        if (i < m) {
            System.exit(e);
            System.exit(f);
            int[] array = new int[2];
            array[0] = 1;
            array[1] = d;
            j = 0[array];
            int length = array.length;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(test);
            stringBuilder.append(b);
            List list = new ArrayList();
            list.add(stringBuilder.toString());
            list.add(object.toString());
            test.a(e, d, j, list);
        }
    }

    static {
        Test.a = 0;
    }

}
