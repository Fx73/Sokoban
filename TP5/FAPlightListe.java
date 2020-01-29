package TP5;

public class FAPlightListe<T extends Comparable<T>>  implements FAPlight<T>{

    maillon tete;

    public void Enfiler(T element) {
        maillon nouveau = new maillon(element);

        if(tete == null){
            tete = nouveau;
            return;
        }

        //Recherche de la valeur de priorité égale ou precedante et insertion
        if(element.compareTo(tete.val) < 0){
                nouveau.next = tete;
                tete = nouveau;
            }
        else{
            maillon temp = tete;
            while (temp.next != null && element.compareTo(temp.next.val) > 0)
                temp = temp.next;

            if(temp.next != null )
                nouveau.next = temp.next;
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
        maillon next;
    
        public maillon(T v){
            val = v;
        }
    }

}

