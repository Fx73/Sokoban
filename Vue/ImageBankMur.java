package Vue;

import Global.Configuration;
import Modele.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;

public class ImageBankMur extends ImageBank {
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

    private void Load(){
        try {
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

    @Override
    public Image GetImage(int a) {
        int x = coord.x;
        int y= coord.y;
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
    }

    Boolean IsMur(int x, int y){
        if(x == -1 || y == -1 )
            return true;
        if(x > GameManager.niveau().colonnes || y>GameManager.niveau().lignes)
            return true;
        return GameManager.niveau().mapGet()[x][y] == 35 || GameManager.niveau().mapGet()[x][y] == 0;
    }
}
