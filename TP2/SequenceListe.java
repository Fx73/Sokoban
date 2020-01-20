package TP2;

interface List{

    void insereTete(int element);
    void insereQueue(int element);
    int extraitTete();
    boolean estVide();

}


public class SequenceList implements List{
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

        maillon last = tete;
        while (last.next != null){
            last = last.next;
        }

        last.next = nouveau;
    }

    public int extraitTete(){
        int v = tete.val;
        tete = tete.next;
        return v;
    }

    public boolean estVide(){
        return tete == null;
    }

}
class maillon{
    int val;
    maillon next;
}