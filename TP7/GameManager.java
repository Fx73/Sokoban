package TP7;

import Global.Configuration;
import Global.Properties;
import TP1.Niveau;
import TP6.InterfaceGraphique;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static Global.Tools.*;

public class GameManager {
    public static 	Niveau[] niveaux;
    public static Integer lvlno = 0;
    public static InterfaceGraphique interfacegraphique;

    public static KeyListener GameKeyListener=new KeyListener(){
        @Override
        public void keyTyped(KeyEvent keyEvent) { }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_F12 || keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE)
                interfacegraphique.toggleFullScreen();
            if (keyEvent.getKeyCode() == KeyEvent.VK_R) {
                ResetLevel();
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_Q || keyEvent.getKeyCode() == KeyEvent.VK_A) {
                Exit();
            }

        }
        @Override
        public void keyReleased(KeyEvent keyEvent) { }
    };


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

    static public void ResetLevel(){

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
