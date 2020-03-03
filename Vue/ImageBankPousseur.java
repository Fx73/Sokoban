package Vue;

import Global.Configuration;

import javax.imageio.ImageIO;
import java.awt.*;

public class ImageBankPousseur extends ImageBank{
    Image[] pousseuridle;
    Image[]pousseurmoving;

    public void Load() {
        pousseuridle = new Image[4];
        pousseurmoving = new Image[8];
        try {
            pousseuridle[0] = ImageIO.read( Configuration.charge("Images/Player/player_00.png"));
            pousseuridle[1] = ImageIO.read( Configuration.charge("Images/Player/player_01.png"));
            pousseuridle[2] = ImageIO.read( Configuration.charge("Images/Player/player_02.png"));
            pousseuridle[3] = ImageIO.read( Configuration.charge("Images/Player/player_03.png"));
            pousseurmoving[0] = ImageIO.read( Configuration.charge("Images/Player/player_04.png"));
            pousseurmoving[4] = ImageIO.read( Configuration.charge("Images/Player/player_05.png"));
            pousseurmoving[1] = ImageIO.read( Configuration.charge("Images/Player/player_06.png"));
            pousseurmoving[5] = ImageIO.read( Configuration.charge("Images/Player/player_07.png"));
            pousseurmoving[2] = ImageIO.read( Configuration.charge("Images/Player/player_08.png"));
            pousseurmoving[6] = ImageIO.read( Configuration.charge("Images/Player/player_09.png"));
            pousseurmoving[3] = ImageIO.read( Configuration.charge("Images/Player/player_10.png"));
            pousseurmoving[7] = ImageIO.read( Configuration.charge("Images/Player/player_11.png"));

        } catch (Exception e) {
            Configuration.instance().logger().severe("Impossible de charger l'image");
            System.exit(1);
        }
    }

    @Override
    public Image GetImage(int a) {
        if(a<4)
            return pousseuridle[a];
        else
            return pousseurmoving[a-4];
    }
}
