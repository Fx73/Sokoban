package Vue;

import Controller.Coup;
import Global.*;
import Modele.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationPousseur extends Animation{
    AnimationPousseur(ImageBankPousseur _imagebank) {
        super(_imagebank);
    }

    public Image GetAnimation(){
        int ref = Coup.DirToInt(GameManager.playercontroller.lastdir);
        //if(GameManager.playercontroller.ismoving)
            return imagebank.GetImage(ref + 4 * animstep + 4);
        //else
        //    return imagebank.GetImage(ref);
    }


}
