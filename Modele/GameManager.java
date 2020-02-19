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

    private static Niveau copieniveau;

    public static void EndTurn(){
        RefreshScreen();
        for (int i = 0; i < niveaux[lvlno].lignes; i++)
            for (int j = 0; j < niveaux[lvlno].colonnes; j++)
                if(niveaux[lvlno].mapGet()[i][j] == CAISSE || niveaux[lvlno].mapGet()[i][j] == BUT)
                    return;

        lvlno ++;
        if (lvlno >= niveaux.length)
            Exit();
        interfacegraphique.RemoveFrame();
        StartLevel();
    }

    static public void StartLevel(){
        try {
            new RedacteurNiveau("resources/level.save").ecrisNiveau(niveaux[lvlno]);
        }
        catch (Exception e){
            Configuration.logger().severe("Erreur d'ecriture dans le ficher de sauvegarde");
        }
        SwingUtilities.invokeLater(interfacegraphique);
    }

    static public void RefreshScreen(){
        interfacegraphique.Refresh();
    }

    static  public Niveau niveau(){
        return niveaux[lvlno];
    }

    static public void ResetLevel(){
        InputStream in_stream = ClassLoader.getSystemClassLoader().getResourceAsStream("level.save");
        try {
            int [][] map = new LecteurNiveaux().lisProchainNiveau(in_stream);
            LecteurNiveaux.printNiveau(niveaux[lvlno]);
            niveaux[lvlno].mapSet(map);
            LecteurNiveaux.printNiveau(niveaux[lvlno]);
        }
        catch (Exception e){
            Configuration.logger().severe("Erreur de lecture du fichier de sauvegarde");
        }
        playercontroller.ResetController();
        RefreshScreen();
    }

    public static void InstanceGame(Niveau[] entreeniveaux){
        niveaux = entreeniveaux;
        lvlno = (int)Configuration.Lis("NiveauCourant");
        interfacegraphique = new InterfaceGraphique();
        playercontroller = new PlayerControler();
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
