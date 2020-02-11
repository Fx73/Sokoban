package TP1;

import static Global.Tools.*;

public class Niveau{

    String nom;
    int [][] map = new int[42][42];
    public int lignes = 42;
    public int colonnes = 42;

    public void fixeNom(String s){nom = s;}

    public void videCase(int i, int j){
        map[j][i]=SOL;
    }

    public void AjouteMur(int i ,int j){
        map[j][i]=MUR;
    }
    public void AjoutePousseur(int i ,int j){
        map[j][i]=POUSSEUR;
    }
    public void AjouteCaisse(int i ,int j){
        map[j][i]=CAISSE;
    }
    public void AjouteBut(int i ,int j){
        map[j][i]=BUT;
    }
    public void AjouteCaisseonbut(int i ,int j){
        map[j][i]=CAISSEONBUT;
    }
    public void AjoutePousseuronbut(int i ,int j){
        map[j][i]=POUSSEURONBUT;
    }
    public String nom(){return nom;}

    Boolean estVide(int l, int c){return true;}

    Boolean aMur(int l, int c){return true;}
    Boolean APousseur(int l, int c){return true;}
    Boolean aBut(int l, int c){return true;}
    Boolean aCaisse(int l, int c){return true;}

    public void mapSet(int[][] input){map = input;}
    public int[][] mapGet(){return map;}
}