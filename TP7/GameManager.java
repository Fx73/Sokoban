package TP7;

import Global.Configuration;
import Global.Properties;
import TP1.Niveau;
import TP6.InterfaceGraphique;

import javax.swing.*;

import java.io.IOException;

import static Global.Tools.*;

public class GameManager {
    public static 	Niveau[] niveaux;
    public static Integer lvlno = 0;
    public static InterfaceGraphique interfacegraphique;

    static void EndTurn(){
        RefreshScreen();
        for (int i = 0; i < niveaux[lvlno].lignes; i++)
            for (int j = 0; j < niveaux[lvlno].colonnes; j++)
                if(niveaux[lvlno].mapGet()[i][j] == CAISSE || niveaux[lvlno].mapGet()[i][j] == BUT)
                    return;

        lvlno ++;
        interfacegraphique.RemoveFrame();
        StartLevel();
    }

    static public void StartLevel(){
        SwingUtilities.invokeLater(interfacegraphique);
    }

    static public void RefreshScreen(){
        interfacegraphique.Refresh();
    }

    static  public Niveau niveau(){
        return niveaux[lvlno];
    }

    public static void Exit() {
        try {
            Properties.Store();
        } catch (IOException e) {
            Configuration.logger().severe("Erreur d'ecriture des Properties");
            System.exit(1);
        }
        System.exit(0);
    }
}
