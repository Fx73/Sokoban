import java.io.*;
import TP1.*;
import TP2.*;


public class MainClass{

    public static void main(String [] args){
        SequenceListe l = new SequenceListe(); 
        l.insereTete(2);
        l.insereTete(1);
        l.insereQueue(3);
        while(!l.estVide()){
            Print(Integer.toString(l.extraitTete()));
        }
         
        SequenceTableau t = new SequenceTableau(); 
        t.insereTete(2);
        t.insereTete(1);
        t.insereQueue(3);
        while(!t.estVide()){
            Print(Integer.toString(t.extraitTete()));
        }
    }





    public static void mai1(String [] args) throws FileNotFoundException,IOException {
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
}