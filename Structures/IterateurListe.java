package TP5;

public class IterateurListe<T> implements Iterateur<T> {
    SequenceListe<T> list;
    SequenceListe<T> clone;
    int taille;
    int pos;
    boolean precedant;

    public IterateurListe(SequenceListe<T> l) {
        list = l;
        clone = (SequenceListe<T>) l.clone();
        pos = 0;
        taille = 0;
        while(!clone.estVide()){
            taille++;
            clone.extraitTete();
        }
        clone = (SequenceListe<T>) l.clone();
        precedant = false;
    }

    public boolean aProchain(){
        return pos + 1 < taille;
    }

    public T prochain(){
        T a = clone.extraitTete();
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
                T a = clone.extraitTete();
                clone.insereQueue(a);
            }
            list = new SequenceListe<T>();
            for(int i = 0;i<taille;i++){
                if(i != pos - 1){
                    T a = clone.extraitTete();
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