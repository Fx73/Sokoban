package Controller;

import Global.Configuration;
import Global.Tools;
import Modele.GameManager;

import java.awt.Point;
import static Global.Tools.*;

public class IA {

public Point accessible[];
public Point successeur[][];

private int posac = 0;
private int possu = 0;

public IA(){
    accessible = new Point[GameManager.niveau().colonnes * GameManager.niveau().colonnes];
    successeur = new Point[GameManager.niveau().colonnes * GameManager.niveau().colonnes * 4][2];
}

public void MarquerTout(Point PlayerPosition){
    accessible = new Point[GameManager.niveau().colonnes * GameManager.niveau().colonnes];
    successeur = new Point[GameManager.niveau().colonnes * GameManager.niveau().colonnes * 4][2];
    posac = 0;
    possu = 0;
    CalculeAccessible(PlayerPosition);
    CalculSuccesseur();
    MarquerCases();
}

public void CalculeAccessible(Point d){
    if(GameManager.niveau().mapGet()[d.y][d.x] == SOL || GameManager.niveau().mapGet()[d.y][d.x] == BUT || GameManager.niveau().mapGet()[d.y][d.x] == POUSSEUR || GameManager.niveau().mapGet()[d.y][d.x] == POUSSEURONBUT){
        for (int i = 0;i<posac;i++) {
            if(accessible[i].equals(d))
                return;
        }
        accessible[posac]=d;
        posac ++;

        CalculeAccessible(new Point(d.x - 1,d.y));
        CalculeAccessible(new Point(d.x + 1,d.y));
        CalculeAccessible(new Point(d.x,d.y - 1));
        CalculeAccessible(new Point(d.x,d.y + 1));
    }
}

public void CalculSuccesseur(){
    for (int i = 0;i<posac;i++) {
        AddSuccesseur(accessible[i].x,accessible[i].y,-1,0);
        AddSuccesseur(accessible[i].x,accessible[i].y,1,0);
        AddSuccesseur(accessible[i].x,accessible[i].y,0,-1);
        AddSuccesseur(accessible[i].x,accessible[i].y,0,1);
    }
}
public void AddSuccesseur(int x,int y, int a, int b){
    if (EstSuccesseur(GameManager.niveau().mapGet()[y+b][x+a],GameManager.niveau().mapGet()[y+2*b][x+2*a])){
        successeur[possu][0] = new Point(x+a,y+b);
        successeur[possu][1] = new Point(x+2*a,y+2*b);
        possu++;
    }
}

private boolean EstSuccesseur(int cas, int suiv){
    return  ((cas == CAISSE || cas== CAISSEONBUT)  && (suiv == SOL || suiv == BUT || suiv == POUSSEUR || suiv == POUSSEURONBUT));

}

public void MarquerCases(){
    for (int i = 0;i<posac;i++) {
        GameManager.interfacegraphique.Marquer(accessible[i].x,accessible[i].y,1);
    }
    for (int i = 0;i<possu;i++) {
        GameManager.interfacegraphique.Marquer(successeur[i][1].x,successeur[i][1].y,2);
    }
}

}
