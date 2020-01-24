package TP4;

import TP2.*;

public interface Iterateur{
    Sequence list = null;
    boolean aProchain();
    int prochain();
    void supprime();
}


public class IterateurListe implements Iterateur {
    SequenceListe list; 
    Object courant;
    Object precedant;
    Object precedant2;

    public IterateurListe(SequenceListe l){
        list = l;
        courant = l.tete;
    }

    public boolean aProchain(){
        return courant.next != null;
    }

    public int prochain(){
        int a = courant.val;
        precedant2 = precedant;
        precedant = courant;
        courant = courant.next;
        return a;
    }

    public void supprime(){
        if(precedant == null)
            throw new IllegalStateException("Suppression d'un element inexistant");
        if(precedant2 == null){
            list.tete = courant;
            precedant = null;
        }
        else{
            precedant2.next = courant;
            precedant= null;
        }
    }


}