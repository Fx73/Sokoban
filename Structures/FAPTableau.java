package TP5;

import java.util.Arrays;

public class FAPTableau<T> implements FAP<T> {

    public int MAXLEN = 1000;
    //maillon tab[] = (maillon[])new Object[MAXLEN];
    T tab[] = (T[])new Object[MAXLEN];
    int prio[] = new int[MAXLEN];
    int taille = 0;

    public void Enfiler(T element, int priorite) {
        if(taille>=MAXLEN)doubleTabSize();

        int i;
        for(i = 0;i<taille;i++){
            if(prio[i]>priorite)
                break;
        }

        for(int j = taille-1;j>=i;j--){
            tab[j+1]= tab[j];
            prio[j+1] = prio[j];
        }
        tab[i] = element;
        prio[i] = priorite;

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
    
    /*class maillon {
        T val;
        int prio;
        public maillon(T v , int p){
            val = v;
            prio = p;
        }
    }*/
}

