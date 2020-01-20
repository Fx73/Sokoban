package TP2;

//Interface Sequence externe

public class SequenceTableau implements Sequence{
    private final static int MAXLEN = 1000;

    int tab[] = new int[MAXLEN];
    int len = 0;

    public void insereTete(int element){
        if(len==MAXLEN) throw new RuntimeException("Tableau Plein");
        for (int i = len;i>0;i--){
            tab[i]= tab[i-1];
        }
        tab[0]=element;
        len++;
    }

    public void insereQueue(int element){
        if(len==MAXLEN) throw new RuntimeException("Tableau Plein");
        tab[len]=element;
        len++;
    }

    public int extraitTete(){
        int v = tab[0];
        for (int i = 1;i<len;i++){
            tab[i-1]= tab[i];
        }
        len--;
        return v;
    }

    public boolean estVide(){
        return len == 0;
    }





}