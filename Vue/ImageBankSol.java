package Vue;

import Global.Configuration;
import Modele.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;

import static Global.Tools.BUT;

public class ImageBankSol extends ImageBank {
    Image but;
    Image but1;
    Image but2;
    Image sol;
    Image sol1;
    Image sol2;

    @Override
    void Load() {
        try {
            but = ImageIO.read( Configuration.charge("Images/But.png"));
            but1 = ImageIO.read( Configuration.charge("Images/But1.png"));
            but2 = ImageIO.read( Configuration.charge("Images/But2.png"));
            sol = ImageIO.read( Configuration.charge("Images/Sol.png"));
            sol1 = ImageIO.read( Configuration.charge("Images/Sol1.png"));
            sol2 = ImageIO.read( Configuration.charge("Images/Sol2.png"));
        } catch (Exception e) {
            Configuration.instance().logger().severe("Impossible de charger l'image sol");
            System.exit(1);
        }
    }

    @Override
    public Image GetImage(int a) {
        if(GameManager.niveau().mapGet()[coord.x][coord.y] == BUT) {
            if (a == 1)
                return but1;
            if (a == 2)
                return but2;
            return but;
        }
        if (a == 1)
            return sol1;
        if (a == 2)
            return sol2;
        return sol;
    }
}
