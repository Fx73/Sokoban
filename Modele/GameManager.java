package Modele;

import Global.*;
import Vue.*;
import Controller.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;

import static Global.Tools.BUT;
import static Global.Tools.CAISSE;

public class GameManager {
    public static Niveau[] niveaux;
    public static Integer lvlno = 0;
    public static InterfaceGraphique interfacegraphique;
    public static PlayerControler playercontroller;
    public static HistoriquePile historique;
    public static IA ia;

    public static KeyListener GameKeyListener=new KeyListener(){
        @Override
        public void keyTyped(KeyEvent keyEvent) { }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_Z)
                historique.Annuler();
            if (keyEvent.getKeyCode() == KeyEvent.VK_Y)
                historique.Refaire();

            if (keyEvent.getKeyCode() == KeyEvent.VK_F12 || keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE)
                interfacegraphique.toggleFullScreen();
            if (keyEvent.getKeyCode() == KeyEvent.VK_F11)
                interfacegraphique.toggleAnimation();

            if (keyEvent.getKeyCode() == KeyEvent.VK_R)
                ResetLevel();

            if (keyEvent.getKeyCode() == KeyEvent.VK_I)
                ia.MarquerTout(playercontroller.GetPlayerPosition());

            if (keyEvent.getKeyCode() == KeyEvent.VK_S)
                ia.SolveLevel();

            if (keyEvent.getKeyCode() == KeyEvent.VK_N)
                NextLevel();

            if (keyEvent.getKeyCode() == KeyEvent.VK_Q || keyEvent.getKeyCode() == KeyEvent.VK_A) {
                Exit();
            }

        }
        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_I)
                interfacegraphique.Demarquer();
        }
    };

    private static Niveau copieniveau;

    public static void EndTurn(){
        RefreshScreen();
        for (int i = 0; i < niveaux[lvlno].lignes; i++)
            for (int j = 0; j < niveaux[lvlno].colonnes; j++)
                if(niveaux[lvlno].mapGet()[i][j] == CAISSE || niveaux[lvlno].mapGet()[i][j] == BUT)
                    return;

        NextLevel();
    }

    static private void NextLevel(){
        lvlno ++;
        if (lvlno >= niveaux.length)
            Exit();
        interfacegraphique.RemoveFrame();
        StartLevel();
    }

    static private int[][] sauvegarde;
    static public void StartLevel(){
        sauvegarde = niveau().mapGetCopy();
        playercontroller.ResetController();
        SwingUtilities.invokeLater(interfacegraphique);
        historique = new HistoriquePile();
    }

    static public void RefreshScreen(){
        interfacegraphique.Refresh();
    }

    static  public Niveau niveau(){
        return niveaux[lvlno];
    }

    static public void ResetLevel(){
        niveau().mapSet(sauvegarde);
        sauvegarde = niveau().mapGetCopy();
        historique = new HistoriquePile();
        playercontroller.ResetController();
        RefreshScreen();
    }

    public static void InstanceGame(Niveau[] entreeniveaux){
        niveaux = entreeniveaux;
        lvlno = (int)Configuration.Lis("NiveauCourant");
        interfacegraphique = new InterfaceGraphique();
        playercontroller = new PlayerControler();
        ia = new IA();
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
