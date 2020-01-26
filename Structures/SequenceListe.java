package Structures;

public class SequenceListe<T> implements Sequence<T>{
    maillon tete;


    public void insereTete(T element){
        maillon nouveau = new maillon(element);
        nouveau.next = tete;

        tete = nouveau;
    }

    public void insereQueue(T element){
        maillon nouveau = new maillon(element);
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
        maillon t = tete;
        while(t!=null){
            l.insereQueue(t.val);
            t=t.next;
        }
        return l;
    }



    class maillon{
        T val;
        maillon next;
    
        public maillon(T v){
            val = v;
        }
    }
}
