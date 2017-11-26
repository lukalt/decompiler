/*
  G:\projects\decompiler\Test.class decompiled at Sun Nov 26 01:31:20 CET 2017
*/
public class Test {

    private static int a;
    private int k;

    public Test(){
        super();
    }

    public static void main(java.lang.String[] param0){
        int local1 = 118;
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
        java.lang.System.exit(local11, local1, local4);
        java.lang.System.exit(local7);
    }

    static {
        a = 0;
        return;
    }

}

