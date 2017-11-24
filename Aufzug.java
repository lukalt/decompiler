/*
G:\projects\decompiler\Aufzug.class decompiled at Sat Nov 25 00:26:24 CET 2017
*/
public class Aufzug {

  private static int idCounter;
  private int aktuellesStockwerk;
  private int anzahlPersonen;
  private boolean tuerAuf;
  private final int minStockwerk;
  private final int maxStockwerk;
  private final int maxPersonen;
  private final int id;
  public Aufzug(int, int, (){
    aload_0; //  @ 0
    invokespecial #1; // Method java/lang/Object."<init>":()V @ 1
    aload_0; //  @ 4
    getstatic     #2; // Field idCounter:I @ 5
    putfield      #3; // Field id:I @ 8
    aload_0; //  @ 11
    iload_2; //  @ 12
    putfield      #4; // Field minStockwerk:I @ 13
    aload_0; //  @ 16
    iload_1; //  @ 17
    putfield      #5; // Field maxStockwerk:I @ 18
    aload_0; //  @ 21
    iload_3; //  @ 22
    putfield      #6; // Field maxPersonen:I @ 23
    aload_0; //  @ 26
    iload_2; //  @ 27
    putfield      #7; // Field aktuellesStockwerk:I @ 28
    getstatic     #2; // Field idCounter:I @ 31
    iconst_1; //  @ 34
    iadd; //  @ 35
    putstatic     #2; // Field idCounter:I @ 36
    return;
    return; //  @ 39
    return;
  }

  public Aufzug(int, int); (){
    aload_0; //  @ 0
    iload_1; //  @ 1
    iconst_0; //  @ 2
    iload_2; //  @ 3
    invokespecial #8; // Method "<init>":(III)V @ 4
    return;
    return; //  @ 7
    return;
  }

  public boolean fahren (int p_int_0){
    aload_0; //  @ 0
    getfield      #9; // Field tuerAuf:Z @ 1
    ifne          69; //  @ 4
    aload_0; //  @ 7
    getfield      #7; // Field aktuellesStockwerk:I @ 8
    istore_2; //  @ 11
    iload_1; //  @ 12
    aload_0; //  @ 13
    getfield      #5; // Field maxStockwerk:I @ 14
    if_icmple     31; //  @ 17
    aload_0; //  @ 20
    aload_0; //  @ 21
    getfield      #5; // Field maxStockwerk:I @ 22
    putfield      #7; // Field aktuellesStockwerk:I @ 25
    goto          55; //  @ 28
    iload_1; //  @ 31
    aload_0; //  @ 32
    getfield      #4; // Field minStockwerk:I @ 33
    if_icmpge     50; //  @ 36
    aload_0; //  @ 39
    aload_0; //  @ 40
    getfield      #4; // Field minStockwerk:I @ 41
    putfield      #7; // Field aktuellesStockwerk:I @ 44
    goto          55; //  @ 47
    aload_0; //  @ 50
    iload_1; //  @ 51
    putfield      #7; // Field aktuellesStockwerk:I @ 52
    aload_0; //  @ 55
    getfield      #7; // Field aktuellesStockwerk:I @ 56
    iload_2; //  @ 59
    if_icmpeq     67; //  @ 60
    iconst_1; //  @ 63
    goto          68; //  @ 64
    iconst_0; //  @ 67
    return;
    ireturn; //  @ 68
    iconst_0; //  @ 69
    return;
    ireturn; //  @ 70
    return;
  }

  public int einsteigen (int p_int_0){
    aload_0; //  @ 0
    getfield      #9; // Field tuerAuf:Z @ 1
    ifeq          38; //  @ 4
    aload_0; //  @ 7
    getfield      #10; // Field anzahlPersonen:I @ 8
    aload_0; //  @ 11
    getfield      #6; // Field maxPersonen:I @ 12
    if_icmpge     38; //  @ 15
    iload_1; //  @ 18
    ifle          38; //  @ 19
    aload_0; //  @ 22
    dup; //  @ 23
    getfield      #10; // Field anzahlPersonen:I @ 24
    iconst_1; //  @ 27
    iadd; //  @ 28
    putfield      #10; // Field anzahlPersonen:I @ 29
    iinc          1, -1; //  @ 32
    goto          7; //  @ 35
    iload_1; //  @ 38
    return;
    ireturn; //  @ 39
    return;
  }

  public void aussteigen (int p_int_0){
    aload_0; //  @ 0
    getfield      #9; // Field tuerAuf:Z @ 1
    ifeq          21; //  @ 4
    aload_0; //  @ 7
    iconst_0; //  @ 8
    aload_0; //  @ 9
    getfield      #10; // Field anzahlPersonen:I @ 10
    iload_1; //  @ 13
    isub; //  @ 14
    invokestatic  #11; // Method java/lang/Math.max:(II)I @ 15
    putfield      #10; // Field anzahlPersonen:I @ 18
    return;
    return; //  @ 21
    return;
  }

  public int getMaxPersonen (){
    aload_0; //  @ 0
    getfield      #6; // Field maxPersonen:I @ 1
    return;
    ireturn; //  @ 4
    return;
  }

  public void tuerOeffnen (){
    aload_0; //  @ 0
    iconst_1; //  @ 1
    putfield      #9; // Field tuerAuf:Z @ 2
    return;
    return; //  @ 5
    return;
  }

  public void tuerSchliessen (){
    aload_0; //  @ 0
    iconst_0; //  @ 1
    putfield      #9; // Field tuerAuf:Z @ 2
    return;
    return; //  @ 5
    return;
  }

  public int getMinStockwerk (){
    aload_0; //  @ 0
    getfield      #4; // Field minStockwerk:I @ 1
    return;
    ireturn; //  @ 4
    return;
  }

  public int getMaxStockwerk (){
    aload_0; //  @ 0
    getfield      #5; // Field maxStockwerk:I @ 1
    return;
    ireturn; //  @ 4
    return;
  }

  public int getAktuellesStockwerk (){
    aload_0; //  @ 0
    getfield      #7; // Field aktuellesStockwerk:I @ 1
    return;
    ireturn; //  @ 4
    return;
  }

  public int getAnzahlPersonen (){
    aload_0; //  @ 0
    getfield      #10; // Field anzahlPersonen:I @ 1
    return;
    ireturn; //  @ 4
    return;
  }

  public boolean isTuerAuf (){
    aload_0; //  @ 0
    getfield      #9; // Field tuerAuf:Z @ 1
    return;
    ireturn; //  @ 4
    return;
  }

  public int getID (){
    aload_0; //  @ 0
    getfield      #3; // Field id:I @ 1
    return;
    ireturn; //  @ 4
    return;
  }

  static {};
// Error: me.lukas81298.decompiler.DecompileException: Code: did not end with a simicolon
