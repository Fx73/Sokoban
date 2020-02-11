package TP1;

import java.io.*;

public class LecteurNiveaux{
    public String nom = "Default";
    public int lignes = 0;
    public int colonnes = 0;


public void ChargerNiveaux(String lvlpath, Niveau[] niveaux)throws IOException{
    String rsclvlpath = "Levels/";
    InputStream in_stream = ClassLoader.getSystemClassLoader().getResourceAsStream(rsclvlpath + lvlpath);
    int[][] niv = this.lisProchainNiveau(in_stream);
    int i =0;

    while(niv != null){
        niveaux[i] = new Niveau();
        niveaux[i].mapSet(niv);
        niveaux[i].lignes = this.lignes;
        niveaux[i].colonnes = this.colonnes;
        niveaux[i].fixeNom(this.nom);
        niv = this.lisProchainNiveau(in_stream);
        i++;
    }

}


public int[][] lisProchainNiveau(InputStream stream) throws IOException {
    int [][] map = new int[42][42];
    boolean fin = false;
    int i=0,j=0;
    byte data[] = new byte[1];
    stream.read(data);

    if(data[0]==0)return null;

    while(data[0] != 0){
        if(data[0] == '\n'){
            fin = true;
            i++;
            colonnes = max(j,colonnes);
            j=0;
        }
        else if (data[0] == ';'){
            nom = ReadLine(stream);
            if(j!=0){colonnes = max(j,colonnes);i++;j=0;}
            fin = true;
        }
        else{
            fin = false;
            if (data[0] != '\r')
                map[i][j]=data[0];
            j++;
        }
        stream.read(data);

        //Niveau suivant
        if((data[0] == '\n' || data[0] == '\r') && fin)
            break;
        //Taille depassee
        if(j>=41 || i>=41){
            System.out.println("Taille de la map trop grande");
            return null;
        }
    }
    lignes = i;
    return map;
}


    String ReadLine(InputStream stream) throws IOException {
        String S = "";
        byte[] data = new byte [1];
        stream.read(data);
        while(data[0] != '\n' && data[0] != 0){
            S += (char)data[0];
            stream.read(data);
        }
        return S;
    }
    private int max(int a, int b) {
        return a>b?a:b;
    }

    public void printNiveau(Niveau N){
        System.out.println(N.nom);

        int [][] tab = N.mapGet();
        for(int i=0;i<N.lignes;i++){
            for(int j=0;j<N.colonnes;j++)
                System.out.print((char)tab[i][j]);
            System.out.print('\n');
        }
    }

}