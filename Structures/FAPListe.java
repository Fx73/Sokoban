package Structures;

public class FAPListe<T> implements FAP<T>{

    maillon tete;

    public void Enfiler(T element, int priorite) {
        maillon nouveau = new maillon(element, priorite);
        maillon temp = tete;

        if(tete == null){
            tete = nouveau;
            return;
        }

        //Recherche de la valeur de priorité égale ou precedante et insertion
        if(nouveau.prio>tete.prio){
                nouveau.next = tete;
                tete = nouveau;
            }
        else{
            while (temp.next != null && nouveau.prio<=temp.next.prio)
                temp = temp.next;

            if(temp.next != null )
                nouveau.next = temp.next.next;
            temp.next = nouveau;
        }
       
    }

    public T Defiler() {
        T valeur = tete.val;
        tete = tete.next;
        return valeur;
    }

    public boolean estVide() {
        return tete == null;
    }

    class maillon {
        T val;
        int prio;
        maillon next;
    
        public maillon(T v, int p){
            val = v;
            prio = p;
        }
    }

}

