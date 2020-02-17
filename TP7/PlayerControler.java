package TP7;

import Global.Tools;
import TP1.Niveau;
import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import TP6.NiveauGraphique;

import static Global.Tools.*;
import static TP7.GameManager.niveau;

public class PlayerControler {
    int [][]map;
    int x,y;
    int xr,yr;
    int xmov = 0;
    int ymov = 0;

    public PlayerControler(){
        ResetController();
    }

    public KeyListener playerlistener = new KeyListener(){
        @Override
        public void keyTyped(KeyEvent keyEvent) { }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            int kc = keyEvent.getKeyCode();
            switch (kc){
                case KeyEvent.VK_UP : MoveUp(); break;
                case KeyEvent.VK_DOWN : MoveDown(); break;
                case KeyEvent.VK_LEFT: MoveLeft(); break;
                case KeyEvent.VK_RIGHT : MoveRight(); break;
            }
            if(kc == KeyEvent.VK_UP || kc == KeyEvent.VK_DOWN || kc == KeyEvent.VK_LEFT || kc == KeyEvent.VK_RIGHT ) {
                Move();
                GameManager.EndTurn();
            }
        }
        @Override
        public void keyReleased(KeyEvent keyEvent) { }
    };

    public MouseListener playermlistener = new MouseListener(){


        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            int xclic = (mouseEvent.getX() - GameManager.interfacegraphique.niveaugraphique.posun.x) /GameManager.interfacegraphique.niveaugraphique.imgsize;
            int yclic = (mouseEvent.getY() - GameManager.interfacegraphique.niveaugraphique.posun.y) /GameManager.interfacegraphique.niveaugraphique.imgsize;
            System.out.println("MouseEvent " + mouseEvent.getX() + " " + mouseEvent.getY());
            System.out.println("Posun " + GameManager.interfacegraphique.niveaugraphique.posun.x + " " + GameManager.interfacegraphique.niveaugraphique.posun.y);
            Tools.Print(xclic);
            Tools.Print(yclic);

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    };



    void Move(){
        if(map[y+ymov][x+xmov] == MUR)
            return;
        if(map[y+ymov][x+xmov] == CAISSE && (map[y + ymov * 2][x + xmov * 2] == MUR || map[y + ymov * 2][x + xmov * 2] == CAISSE))
            return;

        if(map[y + ymov][x + xmov] == CAISSE || map[y + ymov][x + xmov] == CAISSEONBUT){
            CleanCaisseCase(x + xmov ,y + ymov);
            AjoutCaisseCase(x + xmov * 2,y + ymov  * 2);
        }
        
        CleanPlayerCase();
        x+=xmov;
        y+=ymov;
        AjoutPlayerCase();
    }

    void MoveLeft(){
        xmov = -1;
        ymov = 0;
    }

    void MoveRight(){
        xmov = 1;
        ymov = 0;
    }
    void MoveUp(){
        xmov = 0;
        ymov = -1;
    }
    void MoveDown(){
        xmov = 0;
        ymov = 1;
    }

    void CleanPlayerCase(){
        if(map[y][x] == POUSSEURONBUT)
            niveau().AjouteBut(x,y);
        else
            niveau().videCase(x,y);
    }
    void AjoutPlayerCase(){
        if(map[y][x] == BUT)
            niveau().AjoutePousseuronbut(x,y);
        else
            niveau().AjoutePousseur(x,y);
    }
    void CleanCaisseCase(int x,int y){
        if(map[y][x] == CAISSEONBUT)
            niveau().AjouteBut(x,y);
        else
            niveau().videCase(x,y);
    }
    void AjoutCaisseCase(int x,int y){
        if(map[y][x] == BUT)
            niveau().AjouteCaisseonbut(x,y);
        else
            niveau().AjouteCaisse(x,y);
    }

    public void ResetController(){
        map = niveau().mapGet();
        for (int i = 0; i < niveau().lignes; i++)
            for (int j = 0; j < niveau().colonnes; j++)
                if(niveau().mapGet()[i][j] == 64){
                    y=i;
                    x=j;
                    return;
                }
    }
}
