public class Scope {

  public int a = 1;
  public int b = 3;
  public int c = 4;
  public static int d;

  public static void main(String s[]) {
    d = 5;
    System.out.println("A:" + d);
    System.out.println("B:" + Scope.d);
    int d = 13;
    System.out.println("C:" + d);
    System.out.println("D:" + Scope.d);
    Scope.d = 14;
    d = 15;
    System.out.println("E:" + d);
    System.out.println("F:" + Scope.d);
    Scope s1 = new Scope();
    System.out.println("G:" + s1.a);
    System.out.println("H:" + s1.b);
    System.out.println("I:" + s1.c);
    s1.aMethod(77);
    System.out.println("J:" + s1.a);
    System.out.println("K:" + s1.b);
    System.out.println("L:" + s1.c);
    Scope s2 = new Scope();
    System.out.println("M:" + s2.a);
    System.out.println("N:" + s2.b);
    System.out.println("O:" + s2.c);
    s2 = s1;
    System.out.println("P:" + s2.a);
    System.out.println("Q:" + s2.b);
    System.out.println("R:" + s2.c);
    s2.a = 19;
    s2.b = 20;
    s1.a = 33;
    s1.b = 34;
    s1.c = 35;
    s2.c = 21;
    System.out.println("S:" + s1.a);
    System.out.println("T:" + s1.b);
    System.out.println("U:" + s1.c);
    System.out.println("V:" + s2.a);
    System.out.println("W:" + s2.b);
    System.out.println("X:" + s2.c);
  }

  public void aMethod(int a) {
    int b = 4;
    System.out.println("FOO .A:" + a);
    System.out.println("FOO .B:" + b);
    System.out.println("FOO .C:" + c);
    System.out.println("FOO .D:" + d);
    c = a;
    d = d + 1;
    System.out.println("FOO .E:" + a);
    System.out.println("FOO .F:" + b);
    System.out.println("FOO .G:" + c);
    System.out.println("FOO .H:" + d);
    System.out.println("FOO .I:" + this.b);
  }
}
