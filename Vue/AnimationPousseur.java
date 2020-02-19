package Vue;

import Controller.Coup;
import Global.*;
import Modele.GameManager;

import java.awt.*;

public class AnimationPousseur extends Animation{
    AnimationPousseur(ImageBank _imagebank) {
        super(_imagebank);
    }

    public Image GetImage(){
        return imagebank.pousseuridle[Coup.DirToInt(GameManager.playercontroller.lastdir)];
    }

}
