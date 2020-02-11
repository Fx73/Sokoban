package Global;

public class Tools{

    public static final int MUR = 35;
    public static final int SOL = 32;
    public static final int BUT = 46;
    public static final int CAISSE = 36;
    public static final int POUSSEUR = 64;
    public static final int CAISSEONBUT = 42;
    public static final int POUSSEURONBUT = 43;

    public static void Print(String S){
        System.out.println(S);
    }
    public static void Print(int n){
        System.out.println(Integer.toString(n));
    }
    public static void Print(Exception e){
        System.out.println(e.toString());
    }
    public static void Print(Object o){
        System.out.println(o.toString());
    }
}