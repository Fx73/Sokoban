package TP2;

import TP4.IterateurListe;
import TP4.Iterateur;

public class SequenceListe implements Sequence{
    maillon tete;


    public void insereTete(int element){
        maillon nouveau = new maillon();
        nouveau.val = element;
        nouveau.next = tete;

        tete = nouveau;
    }

    public void insereQueue(int element){
        maillon nouveau = new maillon();
        nouveau.val = element;
        nouveau.next = null;

        if(tete == null)
            tete = nouveau;
        else{
            maillon last = tete;
            while (last.next != null){
                last = last.next;
            }
            last.next = nouveau;
        }

    }

    public int extraitTete(){
        if(tete == null) throw new RuntimeException("Sequence Vide");
        int v = tete.val;
        tete = tete.next;
        return v;
    }

    public boolean estVide(){
        return tete == null;
    }

    public Iterateur iterateur() {
        return new IterateurListe(this);
    }

    @Override
	public SequenceListe clone() {
        SequenceListe l = new SequenceListe();
        maillon t = tete;
        while(t!=null){
            l.insereQueue(t.val);
            t=t.next;
        }
        return l;
    }

}
class maillon{
    int val;
    maillon next;
}