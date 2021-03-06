import java.io.*;

import Global.Configuration;
import Global.Tools;
import Structures.Iterateur;
import Structures.Sequence;
import TP1.*;
import TP3.Fabrique;
import TP3.Properties;


public class MainClass{


    public static void main3(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {
        Properties.Load();
        Tools.Print(Configuration.Lis("Sequence"));
        if(Configuration.Lis("Sequence") == "Tableau")
            Configuration.Ecris("Sequence", "Liste");
        else
            Configuration.Ecris("Sequence", "Tableau");

        Properties.Store();
        Configuration.instance();
        Print((String)Configuration.Lis("Sequence"));

        Sequence<String> l = Configuration.instance().FabriqueSequence();
        l.insereQueue("2");
        l.insereTete("1");
        Iterateur<String> i = l.iterateur();
        while(i.aProchain())
            Print(i.prochain());
        Print(i.prochain());

        Object log = Configuration.logger();

        Print("Fin"); 

    }




    public static void main(String[] args) {
        Fabrique<String> fabrique = new Fabrique<String>();
        Print("Sequence");
        TP5.Sequence<String> l = fabrique.sequence("Liste");
        l.insereQueue("2");
        l.insereTete("1");
        l.insereQueue("3");
        l.insereQueue("4");
        TP5.Iterateur<String> i = l.iterateur();
        while(i.aProchain())
            Print(i.prochain());
        Print(i.prochain());

        Print("FAP");
        TP5.FAP<String> f = fabrique.fap("Liste");
        f.Enfiler("First", 10);
        f.Enfiler("Third", 6);
        f.Enfiler("Second", 7);
        f.Enfiler("Last", 0);
        while(!f.estVide()){
            Print(f.Defiler());
        }

        Print("FAPlight");
        TP5.FAPlight<String> f2 = fabrique.faplight("Tableau");
        f2.Enfiler("A");
        f2.Enfiler("D");
        f2.Enfiler("B");
        f2.Enfiler("C");
        while(!f2.estVide()){
            Print(f2.Defiler());
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
        InputStream in_stream = LecteurNiveaux.class.getResourceAsStream("/levels.txt");
        LecteurNiveaux lecteur = new LecteurNiveaux();
        RedacteurNiveau redacteur = new RedacteurNiveau("lvlout.txt");
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

        redacteur.ecrisNiveau(niveaux[0]);
    }



    public static void Print(String S){
        System.out.println(S);
    }
    public static void Print(int n){
        System.out.println(Integer.toString(n));
    }
}