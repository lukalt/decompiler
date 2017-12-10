package me.lukas81298.decompiler.stack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lukas
 * @since 25.11.2017
 */
public class Test {

    private static int a = 0;

    private int k;
    private int fieldA, fieldB;
    private Collection collection;

    public Test(int k, int fieldA, int fieldB, Collection collection) {
        this.k = k;
        this.fieldA = fieldA;
        this.fieldB = fieldB;
        this.collection = collection;
    }

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
        }
        System.exit(f);


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

        test.a(e, d, j, list);

        while(j < i) {
            j++;
        }
    }

}
