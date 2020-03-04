package Vue;


import Global.Configuration;
import Modele.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;

public class ImageBankArrow extends ImageBank {
    Image LeftArrow[];
    Image RightArrow[];

    @Override
    void Load() {
        LeftArrow = new Image[2];
        RightArrow = new Image[2];
        try {
            LeftArrow[0] = ImageIO.read(Configuration.charge("Images/LeftArrow.png"));
            LeftArrow[1] = ImageIO.read(Configuration.charge("Images/LeftArrow2.png"));
            RightArrow[0] = ImageIO.read(Configuration.charge("Images/RightArrow.png"));
            RightArrow[1] = ImageIO.read(Configuration.charge("Images/RightArrow2.png"));
        } catch (Exception e) {
            Configuration.instance().logger().severe("Impossible de charger l'image");
            System.exit(1);
        }
    }

    public Image GetImage(int a) {
        switch (a) {
            case 0:
                if(GameManager.historique.PasseEstVide())
                    return LeftArrow[1];
                else
                    return LeftArrow[0];
            case 1:
                if(GameManager.historique.FuturEstVide())
                    return RightArrow[1];
                else
                    return RightArrow[0];
        }
        Configuration.instance().logger().severe("Arrow inconnue");
        return null;
    }
}