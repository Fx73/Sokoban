import java.io.*;
import java.lang.ProcessBuilder.Redirect.Type;

import TP1.*;
import TP3.Fabrique;
import TP5.*;


public class MainClass{

    public static void main(String[] args) {
        Fabrique<String> fabrique = new Fabrique<String>();
        Print("Sequence");
        Sequence<String> l = fabrique.sequence("liste");
        l.insereQueue("2");
        l.insereTete("1");
        l.insereQueue("3");
        l.insereQueue("4");
        Iterateur<String> i = l.iterateur();
        while(i.aProchain())
            Print(i.prochain());
        Print(i.prochain());

        Print("FAP");
        FAP<String> f = fabrique.fap("liste");
        f.Enfiler("First", 10);
        f.Enfiler("Third", 6);
        f.Enfiler("Second", 7);
        f.Enfiler("Last", 0);
        while(!f.estVide()){
            Print(f.Defiler());
        }
        Print("Fin"); 
    }



    public static void main4(String [] args){
        TP2.Sequence l = new TP2.SequenceTableau(); 
        l.insereQueue(2);
        l.insereTete(1);
        l.insereQueue(3);
        l.insereQueue(4);
        TP4.Iterateur i = l.iterateur();
        while(i.aProchain())
            Print(i.prochain());
        Print(i.prochain());
        Print("Fin"); 
    }



    
    public static void main2(String [] args){
        TP2.SequenceListe l = new TP2.SequenceListe(); 
        l.insereTete(2);
        l.insereTete(1);
        l.insereQueue(3);
        while(!l.estVide()){
            Print(l.extraitTete());
        }
         
        TP2.SequenceTableau t = new TP2.SequenceTableau(); 
        t.insereTete(2);
        t.insereTete(1);
        t.insereQueue(3);
        while(!t.estVide()){
            Print(t.extraitTete());
        }
    }





    public static void main1(String [] args) throws FileNotFoundException,IOException {
        Niveau[] niveaux = new Niveau[100];
        File out = new File("lvlout.txt");
        out.createNewFile();
        InputStream in_stream = LecteurNiveaux.class.getResourceAsStream("/levels.txt");
        OutputStream out_stream = new FileOutputStream(out);
        LecteurNiveaux lecteur = new LecteurNiveaux();
        RedacteurNiveau redacteur = new RedacteurNiveau();
        int i =0;

        int[][] niv = lecteur.lisProchainNiveau(in_stream);

        while(niv != null){
            niveaux[i] = new Niveau();
            niveaux[i].mapSet(niv);
            niveaux[i].lignes = lecteur.lignes;
            niveaux[i].colonnes = lecteur.colonnes;
            niveaux[i].fixeNom(lecteur.nom);
            niv = lecteur.lisProchainNiveau(in_stream);
            i++;
        }
        int max = i;
        //Affichage
        for (i=0;i<max;i++){
            System.out.println("Niveau "+i);
            lecteur.printNiveau( niveaux[i]);
        }

        redacteur.ecrisNiveau(out_stream, niveaux[0]);
        out_stream.flush();
    }



    public static void Print(String S){
        System.out.println(S);
    }
    public static void Print(int n){
        System.out.println(Integer.toString(n));
    }
}