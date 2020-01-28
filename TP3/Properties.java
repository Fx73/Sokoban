package TP3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Global.Configuration;
import Global.Tools;


public class Properties {
    //MACROS
    final static String path = "rsc/";

    //Proprietes du jeu
    public String sequenceImplement = "Liste";



    public void Load()throws IOException{
        BufferedReader in_stream = new BufferedReader(new FileReader(path + "default.cfg"));
        String S; 
        while ((S = in_stream.readLine()) != null) {
            if(!S.startsWith("#")){
                switch (S.split("=")[0]) {
                    case "Sequence":
                        sequenceImplement = S.split("=")[1];
                        break;
                
                    default:
                        Tools.Print("Erreur dans le fichier de proprietes");
                        break;
                }
            }
        }
      in_stream.close();
    }

    public void Store()throws IOException{
        BufferedWriter out_stream = new BufferedWriter(new FileWriter(path + "default.cfg"));
        out_stream.write("# Choix de l'implémentation des sequences\n");
        out_stream.write("# Valeurs utiles : Liste ou Tableau\n");
        out_stream.write("Sequence=Tableau\n");

        out_stream.close();
    }

}
