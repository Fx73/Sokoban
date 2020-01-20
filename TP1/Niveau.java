package TP1;

public class Niveau{

    String nom;
    int [][] map = new int[42][42];
    public int lignes = 42;
    public int colonnes = 42;

    public void fixeNom(String s){nom = s;}

    void videCase(int i, int j){}

    void AjouteMur(int i ,int j){}
    void AjoutePousseur(int i ,int j){}
    void AjouteCaisse(int i ,int j){}
    void AjouteBut(int i ,int j){}

    public int lignes(){return lignes;}
    public int colonnes(){return colonnes;}
    public String nom(){return nom;}

    Boolean estVide(int l, int c){return true;}

    Boolean aMur(int l, int c){return true;}
    Boolean APousseur(int l, int c){return true;}
    Boolean aBut(int l, int c){return true;}
    Boolean aCaisse(int l, int c){return true;}

    public void mapSet(int[][] input){map = input;}
    public int[][] mapGet(){return map;}
}