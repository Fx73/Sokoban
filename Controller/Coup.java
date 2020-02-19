package Controller;

import Modele.GameManager;

public class Coup {
    public static enum Dir {
        HAUT,
        BAS,
        GAUCHE,
        DROITE,
        RIEN;
    }

    Dir dir;
    Coup next;

    public Coup(Dir _dir) {
        dir = _dir;
        next = null;
    }

    public Coup(Dir _dir, Coup _next) {
        dir = _dir;
        next = _next;
    }

    public void Execute() {
        switch (dir) {
            case HAUT:
                GameManager.playercontroller.MoveUp();
                break;
            case BAS:
                GameManager.playercontroller.MoveDown();
                break;
            case GAUCHE:
                GameManager.playercontroller.MoveLeft();
                break;
            case DROITE:
                GameManager.playercontroller.MoveRight();
                break;
        }
        GameManager.playercontroller.Move();
        GameManager.RefreshScreen();
        GameManager.EndTurn();
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
}
