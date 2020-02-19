package Vue;

import Global.Configuration;
import Modele.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;

import static Global.Tools.*;

public class ImageBank {
    Image but;
    Image caisse;
    Image caissesurbut;
    Image sol;
    Image[] pousseuridle;
    Image[][]pousseurmoving;

    Image mur;
    Image murseulgauche;
    Image murseuldroit;
    Image murseulhaut;
    Image murseulbas;
    Image murgauche;
    Image murdroit;
    Image murhaut;
    Image murbas;
    Image murhautgauche;
    Image murhautdroit;
    Image murbasgauche;
    Image murbasdroit;
    Image murgauchedroit;
    Image murhautbas;
    Image murplein;

    ImageBank(){
        Load();
    }
    void Load(){
        pousseuridle = new Image[4];
        pousseurmoving = new Image[4][2];
        try {
            but = ImageIO.read( Configuration.charge("Images/But.png"));
            caisse = ImageIO.read( Configuration.charge("Images/Caisse.png"));
            caissesurbut = ImageIO.read( Configuration.charge("Images/Caisse_sur_but.png"));
            sol = ImageIO.read( Configuration.charge("Images/Sol.png"));

            pousseuridle[0] = ImageIO.read( Configuration.charge("Images/Player/player_01.png"));
            pousseuridle[1] = ImageIO.read( Configuration.charge("Images/Player/player_00.png"));
            pousseuridle[2] = ImageIO.read( Configuration.charge("Images/Player/player_02.png"));
            pousseuridle[3] = ImageIO.read( Configuration.charge("Images/Player/player_03.png"));


            mur = ImageIO.read( Configuration.charge("Images/Murs/Mur.png"));
            murseulgauche = ImageIO.read( Configuration.charge("Images/Murs/Murseulgauche.png"));
            murseuldroit = ImageIO.read( Configuration.charge("Images/Murs/Murseuldroit.png"));
            murseulhaut = ImageIO.read( Configuration.charge("Images/Murs/Murseulhaut.png"));
            murseulbas = ImageIO.read( Configuration.charge("Images/Murs/Murseulbas.png"));
            murgauche = ImageIO.read( Configuration.charge("Images/Murs/Murgauche.png"));
            murdroit = ImageIO.read( Configuration.charge("Images/Murs/Murdroit.png"));
            murhaut = ImageIO.read( Configuration.charge("Images/Murs/Murhaut.png"));
            murbas = ImageIO.read( Configuration.charge("Images/Murs/Murbas.png"));
            murhautgauche = ImageIO.read( Configuration.charge("Images/Murs/Murhautgauche.png"));
            murhautdroit = ImageIO.read( Configuration.charge("Images/Murs/Murhautdroit.png"));
            murbasgauche = ImageIO.read( Configuration.charge("Images/Murs/Murbasgauche.png"));
            murbasdroit = ImageIO.read( Configuration.charge("Images/Murs/Murbasdroit.png"));
            murplein = ImageIO.read( Configuration.charge("Images/Murs/Murplein.png"));
            murhautbas = ImageIO.read( Configuration.charge("Images/Murs/Murhautbas.png"));
            murgauchedroit = ImageIO.read( Configuration.charge("Images/Murs/Murgauchedroit.png"));


        } catch (Exception e) {
            Configuration.instance().logger().severe("Impossible de charger l'image");
            System.exit(1);
        }
    }

    public Image GetImage(int a, int x, int y){
        switch(a){
            case SOL: case POUSSEUR : return sol;
            case BUT: case POUSSEURONBUT : return but;
            case CAISSE : return caisse;
            case CAISSEONBUT : return caissesurbut;
            case MUR: case 0:
                int hash = 0;
                if(IsMur(x-1,y))
                    hash +=1;
                if(IsMur(x,y-1))
                    hash +=10;
                if(IsMur(x,y+1))
                    hash +=100;
                if(IsMur(x+1,y))
                    hash +=1000;
                switch (hash){
                    case 0 : default : return mur;
                    case 1 : return murseulbas;
                    case 10 : return murseuldroit;
                    case 100 : return murseulgauche;
                    case 1000 : return murseulhaut;
                    case 11 : return murbasdroit;
                    case 101 : return murbasgauche;
                    case 1010 : return murhautdroit;
                    case 1100 : return murhautgauche;
                    case 111 : return murbas;
                    case 1101 : return murgauche;
                    case 1110 : return murhaut;
                    case 1011 : return murdroit;
                    case 1001 : return murhautbas;
                    case 110 : return murgauchedroit;
                    case 1111: return murplein;
                }
            default :
                Configuration.logger().warning("Objet inconnue dans le niveau");
                return null;
        }
    }
    Boolean IsMur(int x, int y){
        if(x == -1 || y == -1 )
            return true;
        if(x > GameManager.niveau().colonnes || y>GameManager.niveau().lignes)
            return true;
        return GameManager.niveau().mapGet()[x][y] == 35 || GameManager.niveau().mapGet()[x][y] == 0;
    }
}
