/*
  G:\projects\decompiler\Test.class decompiled at Sun Nov 26 17:39:10 CET 2017
*/
public class Test {

    private static int a;
    private int k;

    public Test(){
        super();
    }

    public static void main(java.lang.String[] param0){
        byte local1 = 118;
        int local2 = 1;
        int local3 = (local1 + local2);
        int local4 = (local3 - local1);
        Object local5 = new java.lang.Object();
        java.lang.System.out.println(local5);
        Object local6 = new java.lang.Object();
        int local7 = (local1 * local4);
        long local8 = 1324567;
        local3 = (local7 % local3);
        local4 = (local3 * local4);
        java.lang.System.out.println(local4);
        a = local3;
        Object local10 = new Test();
        local10.k = local2;
        Object local11 = local10.k;
        if (local11 < local1) {
            java.lang.System.exit(local4);
        }
        java.lang.System.exit();
        Object local12 = new int[2];
        local12[0] = 1;
        local12[1] = local3;
        local2 = local12[0];
        Object local13 = local12.length;
        new byte[3][new byte[3]] = 0;
        1[1] = 1;
        2[2] = 2;
        int local14 = 3;
        Object local15 = new java.lang.Object[4];
    }

    static {
        a = 0;
        return;
    }

}

