package TP5;

import java.util.Arrays;

public class SequenceTableau<T> implements Sequence<T> {
    public int MAXLEN = 1000;

    T tab[] = (T[])new Object[MAXLEN];
    int first = 0;
    int len = 0;

    public void insereTete(T element){
        if(len==MAXLEN) doubleTabSize();
        first = moinsUn(first);
        tab[first]=element;
        len++;
    }

    public void insereQueue(T element){
        if(len==MAXLEN) doubleTabSize();
        tab[last()]=element;
        len++;
    }

    public T extraitTete(){
        T v = tab[first];
        first = plusUn(first);
        len--;
        return v;
    }

    public boolean estVide(){
        return len == 0;
    }


    public Iterateur<T> iterateur(){
        return new IterateurTableau<T>(this);
    }
    public int lenght(){
        return len;
    }
    public int startingAt(){
        return first;
    }
    public T getValue(int n){
        return tab[n];
    }
    public void setValue(T val, int n){
        tab[n]=val;
    }
 
    
    private void doubleTabSize(){
        MAXLEN = MAXLEN * 2;
        tab = Arrays.copyOf(tab, MAXLEN);
    }
    private int moinsUn(int n){if(n==0)return MAXLEN-1; else return n-1; }
    private int plusUn(int n){if(n==MAXLEN-1)return 0; else return n+1; }
    private int last(){if(first+len> MAXLEN-1)return first+len-MAXLEN; else return first+len; }
    
}