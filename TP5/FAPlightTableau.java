package TP5;

import java.util.Arrays;

public class FAPlightTableau <T extends Comparable<T>> implements FAPlight<T> {

    public int MAXLEN = 1000;
    T tab[] = (T[])new Comparable[MAXLEN];
    int taille = 0;

    public void Enfiler(T element) {
        if(taille>=MAXLEN)doubleTabSize();

        int i;
        for(i = 0;i<taille;i++){
            if(tab[i].compareTo(element)< 0 )
                break;
        }

        for(int j = taille-1;j>=i;j--){
            tab[j+1]= tab[j];
        }
        tab[i] = element;

        taille ++;
    }

    public T Defiler() {
        taille --;
        return tab[taille];
    }

    public boolean estVide() {
        return taille == 0;
    }

    private void doubleTabSize(){
        MAXLEN = MAXLEN * 2;
        tab = Arrays.copyOf(tab, MAXLEN);
    }
    
}

