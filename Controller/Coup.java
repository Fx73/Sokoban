package Controller;

import Modele.GameManager;

import java.awt.*;

public class Coup extends Commande {
    public static enum Dir {
        HAUT,
        BAS,
        GAUCHE,
        DROITE,
        RIEN;
    }

    Dir dir;
    boolean pousse = false;
    Coup next;

    public Coup(Dir _dir,boolean _pousse) {
        dir = _dir;
        pousse = _pousse;
        next = null;
    }
    public Coup(Dir _dir, boolean _pousse, Coup _next) {
        dir = _dir;
        pousse = _pousse;
        next = _next;
    }
    public void Execute() {
        Point direction = DirToPoint(dir);
        if(pousse) GameManager.playercontroller.MoveCaisse(direction);
        GameManager.playercontroller.Move(direction);
        GameManager.RefreshScreen();
        GameManager.EndTurn();
    }

    public void Dexecute(){
        Point direction = DirToPoint(dir);
        GameManager.playercontroller.Demove(direction);
        if(pousse) GameManager.playercontroller.DemoveCaisse(direction);
        GameManager.RefreshScreen();
        GameManager.EndTurn();
    }

    public void SetNext(Coup _next){
        next = _next;
    }
    public static int DirToInt(Dir d){
        switch (d){
            case HAUT:return 0;
            case BAS:return 1;
            case GAUCHE:return 2;
            case DROITE:return 3;
        }
        return 4;
    }
    public static Dir IntToDir(int n){
        switch (n){
            case 0:return Dir.HAUT;
            case 1:return Dir.BAS;
            case 2:return Dir.GAUCHE;
            case 3:return Dir.DROITE;
        }
        return Dir.RIEN;
    }

    public static Point DirToPoint(Dir d){
        int x = 0,y = 0;
        switch (d){
            case HAUT:y--;break;
            case BAS: y++;break;
            case GAUCHE: x --;break;
            case DROITE: x ++;break;
        }
        return new Point(x,y);
    }

}
