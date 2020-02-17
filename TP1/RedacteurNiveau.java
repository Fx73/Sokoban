package TP1;

import Global.Configuration;

import java.io.*;

public class RedacteurNiveau{
        OutputStream stream;
    public RedacteurNiveau(String filepath) {
        File out = new File(filepath);
        try {
            out.createNewFile();
            stream = new FileOutputStream(out);
        }
        catch(Exception e){
            Configuration.logger().severe("Erreur de creation d'un fichier de sortie " + filepath);
        }

    }

    public void ecrisNiveau(Niveau N) throws IOException{
    int [][] tab = N.mapGet();
    for(int i=0;i<N.lignes;i++){
        for(int j=0;j<N.colonnes;j++)
            if(tab[i][j]!=0)stream.write((byte)tab[i][j]);
        stream.write('\n');
    }
    stream.write(';');
    stream.write( N.nom.getBytes());
    stream.write('\n');
    stream.write('\n');
    stream.flush();
    stream.close();
 }




}