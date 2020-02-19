import java.io.*;


import Global.*;
import Structures.*;
import Modele.*;


public class Sokoban{

    public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {
        Properties.Load();
        Niveau[] niveaux = new Niveau[100];
        new LecteurNiveaux().ChargerNiveaux("levels.txt", niveaux);
        GameManager.InstanceGame(niveaux);
        GameManager.StartLevel();
    }





    public static void test(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {
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

    public static void Print(String S){
        System.out.println(S);
    }
    public static void Print(int n){
        System.out.println(Integer.toString(n));
    }
}