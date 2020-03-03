package Vue;

import Global.Configuration;
import Modele.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;

import static Global.Tools.*;

public class ImageBankAll extends ImageBank {
    Image but;
    Image caisse;
    Image caissesurbut;
    Image sol;

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

    ImageBankAll(){
        Load();
    }
    void Load(){
        try {
            but = ImageIO.read( Configuration.charge("Images/But.png"));
            caisse = ImageIO.read( Configuration.charge("Images/Caisse.png"));
            caissesurbut = ImageIO.read( Configuration.charge("Images/Caisse_sur_but.png"));
            sol = ImageIO.read( Configuration.charge("Images/Sol.png"));



        } catch (Exception e) {
            Configuration.instance().logger().severe("Impossible de charger l'image");
            System.exit(1);
        }
    }

    public Image GetImage(int a){
        switch(a){
            case SOL: case POUSSEUR : return sol;
            case BUT: case POUSSEURONBUT : return but;
            case CAISSE : return caisse;
            case CAISSEONBUT : return caissesurbut;
            default :
                Configuration.logger().warning("Objet inconnue dans le niveau");
                return null;
        }
    }
}
