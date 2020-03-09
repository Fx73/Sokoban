package Controller;

import Modele.GameManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static Controller.Coup.Dir.HAUT;

public class IA {
    public boolean enabled = false;
    private Timer timer;
    public IA(){
        timer = new Timer(1000, new ActionListener() { @Override public void actionPerformed(ActionEvent actionEvent) { tictac();}});
    }

    void tictac(){
        DeplacementAlea();
    }

    public void EnableDisable(){
        if (enabled)
            timer.stop();
        else
            timer.start();
        enabled = !enabled;
    }


    void DeplacementAlea(){
        Controller.Coup.Dir direction = Controller.Coup.IntToDir(new Random().nextInt(4));
        Coup c = GameManager.playercontroller.CreateCoup(direction);
        GameManager.historique.Faire(c);
    }
}
