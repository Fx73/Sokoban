package TP4;

import TP2.*;

public class IterateurTableau implements Iterateur {

    SequenceTableau tab; 
    int courant;
    int precedant = -1;

    public IterateurListe(SequenceListe l){
        list = l;
        courant = 0;
    }

    public boolean aProchain(){
        return courant + 1 < tab.lenght();
    }

    public int prochain(){
        courant ++;
        return tab.value(courant-1);
    }

    public void supprime(){
        if(precedant == -1)
            throw new IllegalStateException("Suppression d'un element inexistant");
        else{
            for(int i = precedant;i<tab.lenght()-2;i++){
                tab.setValue(tab.getValue(n+1), n);
            }
            precedant= -1;
        }
    }




}