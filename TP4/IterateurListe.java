package TP4;

import TP2.*;


public class IterateurListe implements Iterateur {
    SequenceListe list;
    SequenceListe clone;
    int taille;
    int pos;
    boolean precedant;

    public IterateurListe(SequenceListe l) {
        list = l;
        clone = (SequenceListe) l.clone();
        pos = 0;
        taille = 0;
        while(!clone.estVide()){
            taille++;
            clone.extraitTete();
        }
        clone = (SequenceListe) l.clone();
        precedant = false;
    }

    public boolean aProchain(){
        return pos + 1 < taille;
    }

    public int prochain(){
        int a = clone.extraitTete();
        clone.insereQueue(a);
        pos++;
        precedant = true;
        return a;
    }

    public void supprime(){
        if(!precedant)
            throw new IllegalStateException("Suppression d'un element inexistant");
        else{
            for(int i = pos;i<taille;i++){
                int a = clone.extraitTete();
                clone.insereQueue(a);
            }
            list = new SequenceListe();
            for(int i = 0;i<taille;i++){
                if(i != pos - 1){
                    int a = clone.extraitTete();
                    list.insereQueue(a);
                    clone.insereQueue(a);
                }
                else
                    clone.extraitTete();
            }
            precedant = false;
        }
    }


}