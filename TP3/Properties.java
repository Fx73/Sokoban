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


    public static void Load()throws IOException{
        BufferedReader in_stream = new BufferedReader(new FileReader(path + "default.cfg"));
        String S; 
        while ((S = in_stream.readLine()) != null) {
            if(!S.startsWith("#")){
                String[] prop = S.split("=");
                if(!Configuration.Ecris(prop[0], prop[1]))
                    Tools.Print("Erreur dans le fichier de proprietes : champ inconnu " + prop[0]);
            }
        }
      in_stream.close();
    }

    public static void Store()throws IOException{
        BufferedWriter out_stream = new BufferedWriter(new FileWriter(path + "default.cfg"));
        out_stream.write("# Choix de l'implémentation des sequences\n");
        out_stream.write("# Valeurs utiles : Liste ou Tableau\n");
        out_stream.write("Sequence="+ Configuration.Lis("Sequence").toString() +"\n");
        out_stream.write("LogLevel="+ Configuration.Lis("LogLevel").toString() +"\n");

        out_stream.close();
    }

}
