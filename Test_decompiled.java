package me.lukas81298.decompiler.stack;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private static int a;
    private int k;

    public Test() {
        super();
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
        long s = 1324567;
        d = f % d;
        e = d * e;
        System.out.println(e);
        Test.a = d;
        Test test = new Test();
        test.k = j;
        int m = test.k;
        if (m < i) {
            System.exit(e);
            System.exit(f);
            int[] array = new int[2];
            array[0] = 1;
            array[1] = d;
            j = array[0];
            int length = array.length;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(test);
            stringBuilder.append(b);
            List list = new ArrayList();
            stringBuilder.add(list.toString());
            object.add(list.toString());
        }
    }

    static {
        Test.a = 0;
    }

}
