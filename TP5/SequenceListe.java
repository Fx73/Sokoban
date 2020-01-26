package TP5;

public class SequenceListe<T> implements Sequence<T>{
    maillon<T> tete;


    public void insereTete(T element){
        maillon<T> nouveau = new maillon<T>();
        nouveau.val = element;
        nouveau.next = tete;

        tete = nouveau;
    }

    public void insereQueue(T element){
        maillon<T> nouveau = new maillon<T>();
        nouveau.val = element;
        nouveau.next = null;

        if(tete == null)
            tete = nouveau;
        else{
            maillon<T> last = tete;
            while (last.next != null){
                last = last.next;
            }
            last.next = nouveau;
        }

    }

    public T extraitTete(){
        if(tete == null) throw new RuntimeException("Sequence Vide");
        T v = tete.val;
        tete = tete.next;
        return v;
    }

    public boolean estVide(){
        return tete == null;
    }

    public Iterateur<T> iterateur() {
        return new IterateurListe<T>(this);
    }

    @Override
	public SequenceListe<T> clone() {
        SequenceListe<T> l = new SequenceListe<T>();
        maillon<T> t = tete;
        while(t!=null){
            l.insereQueue(t.val);
            t=t.next;
        }
        return l;
    }

}
class maillon<T>{
    T val;
    maillon<T> next;
}