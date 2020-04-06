package Vue;

import Global.Configuration;
import Modele.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;

import static Global.Tools.*;

public class ImageBankCaisse extends ImageBank {
    Image caisse;
    Image caissesurbut;

    ImageBankCaisse(){
        Load();
    }
    void Load(){
        try {
            caisse = ImageIO.read( Configuration.charge("Images/Caisse.png"));
            caissesurbut = ImageIO.read( Configuration.charge("Images/Caisse_sur_but.png"));
        } catch (Exception e) {
            Configuration.instance().logger().severe("Impossible de charger l'image caisse");
            System.exit(1);
        }
    }

    public Image GetImage(int a){
        switch(a){
            case CAISSE : return caisse;
            case CAISSEONBUT : return caissesurbut;
            default :
                Configuration.logger().warning("Objet inconnue dans le niveau");
                return null;
        }
    }
}
