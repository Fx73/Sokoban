package Controller;

public class HistoriquePile {
    Commande passe;
    Commande futur;

    public void Faire(Commande c){
        c.Execute();
        c.next = passe;
        passe = c;
        futur = null;
    }

    public void Annuler(){
        if(passe == null)return;
        Commande c = passe.next;
        passe.next = null;
        futur = passe;
        passe = c;
        futur.Dexecute();
    }

    public void Refaire(){
        if(futur == null)return;
        Commande c = futur.next;
        futur.next = null;
        passe = futur;
        futur = c;
        passe.Execute();
    }
}
