package TP7;

import Global.Deplacement;
import Global.Tools;
import TP1.Niveau;
import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import TP6.NiveauGraphique;

import static Global.Tools.*;
import static TP7.GameManager.*;
import static java.lang.Math.abs;

public class PlayerControler {
    int [][]map;
    public Point coord = new Point ();

    int x,y;
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
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Deplacement.deplist depl;
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Point arrivee  = GetPointInGrid(mouseEvent.getPoint());
            System.out.println(arrivee);

            if((abs(arrivee.x -x) == 1 && arrivee.y - y == 0 ) || (abs(arrivee.y -y) == 1 && arrivee.x - x == 0 )) {
                if (arrivee.x - x == 0)
                    if (arrivee.y - y > 0)
                        MoveDown();
                    else
                        MoveUp();
                else if (arrivee.x - x > 0)
                        MoveRight();
                    else
                        MoveLeft();
                Move();
                RefreshScreen();
                GameManager.EndTurn();
                return;
            }

            depl = Deplacement.Djikstra(new Point(x,y),arrivee);
            if (depl == null) return;

            if(!executorService.isShutdown())executorService.shutdownNow();
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(this::AutoMove, 0, 100, TimeUnit.MILLISECONDS);
        }
        private void AutoMove() {
            switch (depl.dep){
                case 0: MoveUp();break;
                case 1: MoveDown();break;
                case 2: MoveLeft();break;
                case 3: MoveRight();break;
                case -1:executorService.shutdownNow();return;
            }
            Move();
            RefreshScreen();
            depl = depl.next;
        }
        @Override
        public void mousePressed(MouseEvent mouseEvent) { }
        @Override
        public void mouseReleased(MouseEvent mouseEvent) { }
        @Override
        public void mouseEntered(MouseEvent mouseEvent) { }
        @Override
        public void mouseExited(MouseEvent mouseEvent) { }
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

    public static Point GetPointInGrid(Point coord){
        int imgsize = interfacegraphique.ImgSize();
        Point zero = new Point(interfacegraphique.GetSize().width/2 - niveau().colonnes * (imgsize/2) +(imgsize/2) , interfacegraphique.GetSize().height/2 - niveau().lignes * (imgsize/2) + imgsize);
        int xc = (coord.x - zero.x)/imgsize;
        int yc = (coord.y - zero.y)/imgsize;
        return new Point(xc,yc);
    }
}
