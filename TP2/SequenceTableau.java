package TP2;

import java.util.Arrays;

import TP4.IterateurTableau;
import TP4.Iterateur;

public class SequenceTableau implements Sequence{
    public int MAXLEN = 1000;

    int tab[] = new int[MAXLEN];
    int first = 0;
    int len = 0;

    private void doubleTabSize(){
        MAXLEN = MAXLEN * 2;
        tab = Arrays.copyOf(tab, MAXLEN);
    }
    private int moinsUn(int n){if(n==0)return MAXLEN-1; else return n-1; }
    private int plusUn(int n){if(n==MAXLEN-1)return 0; else return n+1; }
    private int last(){if(first+len> MAXLEN-1)return first+len-MAXLEN; else return first+len; }


    public void insereTete(int element){
        if(len==MAXLEN) doubleTabSize();
        first = moinsUn(first);
        tab[first]=element;
        len++;
    }

    public void insereQueue(int element){
        if(len==MAXLEN) doubleTabSize();
        tab[last()]=element;
        len++;
    }

    public int extraitTete(){
        int v = tab[first];
        first = plusUn(first);
        len--;
        return v;
    }

    public boolean estVide(){
        return len == 0;
    }


    public Iterateur iterateur(){
        return new IterateurTableau(this);
    }
    public int lenght(){
        return len;
    }
    public int startingAt(){
        return first;
    }
    public int getValue(int n){
        return tab[n];
    }
    public void setValue(int val, int n){
        tab[n]=val;
    }
 
}