package TP1;

import java.io.IOException;
import java.io.OutputStream;

public class RedacteurNiveau{

    public void ecrisNiveau(OutputStream stream, Niveau N) throws IOException{

    int [][] tab = N.mapGet();
    for(int i=0;i<N.lignes;i++){
        for(int j=0;j<N.colonnes;j++)
            if(tab[i][j]!=0)stream.write((byte)tab[i][j]);
        stream.write('\n');
    }
    stream.write(';');
    stream.write( N.nom.getBytes());
 }




}