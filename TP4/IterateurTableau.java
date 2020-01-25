package TP4;

import TP2.*;

public class IterateurTableau implements Iterateur {

    SequenceTableau tab;
    int first;
    int taille;
    int courant;
    int precedant = -1;

    public IterateurTableau(SequenceTableau t){
        tab = t;
        courant = 0;
        taille = t.lenght();
        first = t.startingAt();
    }

    public boolean aProchain(){
        return courant + 1 < taille;
    }

    public int prochain(){
        courant ++;
        int a = first + courant;
        if(a>tab.MAXLEN)
            a-=tab.MAXLEN;
        return tab.getValue(a-1);
    }

    public void supprime(){
        if(precedant == -1)
            throw new IllegalStateException("Suppression d'un element inexistant");
        else{
            for(int i = precedant;i<taille-2;i++){
                tab.setValue(tab.getValue(i+1), i);
            }
            precedant= -1;
        }
    }




}