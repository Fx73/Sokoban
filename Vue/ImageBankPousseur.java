package Vue;

import Global.Configuration;

import javax.imageio.ImageIO;
import java.awt.*;

public class ImageBankPousseur extends ImageBank{
    Image[] pousseuridle;
    Image[][]pousseurmoving;

    public void Load() {
        pousseuridle = new Image[4];
        pousseurmoving = new Image[4][2];
        try {

        pousseuridle[0] = ImageIO.read( Configuration.charge("Images/Player/player_01.png"));
        pousseuridle[1] = ImageIO.read( Configuration.charge("Images/Player/player_00.png"));
        pousseuridle[2] = ImageIO.read( Configuration.charge("Images/Player/player_02.png"));
        pousseuridle[3] = ImageIO.read( Configuration.charge("Images/Player/player_03.png"));
        } catch (Exception e) {
            Configuration.instance().logger().severe("Impossible de charger l'image");
            System.exit(1);
        }
    }

    @Override
    public Image GetImage(int a) {
        return pousseuridle[a];
    }
}
