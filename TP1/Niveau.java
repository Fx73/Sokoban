package TP1;

public class Niveau{

    String nom;
    int [][] map = new int[42][42];
    int lignes = 42;
    int colonnes = 42;

    void fixeNom(String s){nom = s;}

    void videCase(int i, int j){}

    void AjouteMur(int i ,int j){}
    void AjoutePousseur(int i ,int j){}
    void AjouteCaisse(int i ,int j){}
    void AjouteBut(int i ,int j){}

    int lignes(){return lignes;}
    int colonnes(){return colonnes;}
    String nom(){return nom;}

    Boolean estVide(int l, int c){return true;}

    Boolean aMur(int l, int c){return true;}
    Boolean APousseur(int l, int c){return true;}
    Boolean aBut(int l, int c){return true;}
    Boolean aCaisse(int l, int c){return true;}

    void mapSet(int[][] input){map = input;}
    int[][] mapGet(){return map;}
}