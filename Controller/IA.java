package Controller;

import Global.Configuration;
import Global.Tools;
import Modele.GameManager;

import  java.lang.System;
import java.awt.Point;
import static Global.Tools.*;

public class IA {

public IA(){
}


public void SolveLevel(){
    Coup reponse = SolveRec(GameManager.playercontroller.x,GameManager.playercontroller.y,GameManager.niveau().mapGetCopy());
    GameManager.playercontroller.LauchAutoMove(reponse);
    Tools.Print("Fini");
}

public Coup SolveRec(int px, int py, int[][] map){
    Point[] acce = CalculeAccessible(new Point(px,py),map);
    Point[][] succ = CalculSuccesseur(map,acce);
    double[] h = CalculerHeuristique(succ,map);

    while(true){
        int min = -1;
        for (int i = 0; i < h.length; i++) {
            if(h[i]!=-1 && (min == -1 || h[min]>h[i]))
                min=i;
        }
        if(min == -1)
            return null;
        h[min]=-1;
        DelCaisse(succ[min][0],map);
        AddCaisse(succ[min][1],map);
        if(Win(map))
            return CalculeChemin(new Point(px,py),succ[min], null,map);
        Coup res = SolveRec(succ[min][0].x,succ[min][0].y,map);
        DelCaisse(succ[min][1],map);
        AddCaisse(succ[min][0],map);
        if (res != null)
            return  CalculeChemin(new Point(px,py),succ[min], res,map);

    }
}

public double[] CalculerHeuristique(Point[][] succ,int[][]map){
    double[] h = new double[succ.length];
    for (int k = 0; k < succ.length; k++) {
        double min = 2;
        Point pold = succ[k][0];
        Point pnew = succ[k][1];
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == BUT || map[i][j] == POUSSEURONBUT){
                    min = 1 + ((distance(pnew,new Point(j,i)) - distance(pold,new Point(j,i))));
                }
            }
        }
        h[k] = min;
    }
    return  h;
}

double distance(Point a, Point b){
    return java.lang.Math.sqrt(pow2(a.x - b.x) + pow2(a.y - b.y));
}
int pow2(int a){
    return a*a;
}

public boolean Win(int[][] map){
    for (int[] col:map)
        for (int c:col) {
            if(c == BUT || c == CAISSE)
                return false;
        }
    return true;
}

public Coup CalculeChemin(Point depart, Point[] succ, Coup suiv, int [][] map){
    Point arrivee = new Point(succ[0].x - (succ[1].x -succ[0].x),succ[0].y - (succ[1].y -succ[0].y));
    Coup c = Deplacement.Djikstra(depart,arrivee,map);
    Coup d = new Coup(Coup.PointToDir(new Point((succ[1].x -succ[0].x),(succ[1].y -succ[0].y))),true,suiv);

    if(c == null) return d;
    Coup cnext = c;
    while (cnext.next != null)
        cnext = cnext.next;
    cnext.next = d;
    return c;
}

void DelCaisse(Point p, int[][]map){
    if(map[p.y][p.x] == CAISSEONBUT)
        map[p.y][p.x] = BUT;
    else
        map[p.y][p.x] = SOL;
}
void AddCaisse(Point p, int[][]map){
    if(map[p.y][p.x] == BUT)
        map[p.y][p.x] = CAISSEONBUT;
    else
        map[p.y][p.x] = CAISSE;
}

public void MarquerTout(Point playerposition){
    Point[] accessible = CalculeAccessible(playerposition,GameManager.niveau().mapGet());
    Point[][] successeur = CalculSuccesseur(GameManager.niveau().mapGet(),accessible);
    AfficherMarque(accessible,successeur);
}

public Point[] CalculeAccessible(Point d, int[][] map){
    Point[] temp = new Point[GameManager.niveau().lignes * GameManager.niveau().colonnes];
    Integer len = CalculeAccessibleRec(d,map,temp,0);
    Point[] accessible = new Point[len];
    System.arraycopy(temp,0,accessible,0,len);
    return accessible;
}

public Integer CalculeAccessibleRec(Point d, int[][] map, Point[] accessible,Integer len) {
    if (map[d.y][d.x] == SOL || map[d.y][d.x] == BUT || map[d.y][d.x] == POUSSEUR || map[d.y][d.x] == POUSSEURONBUT) {
        for (int i = 0; i < len; i++) {
            if (accessible[i].equals(d))
                return len;
        }
        accessible[len] = d;
        len++;

        len = CalculeAccessibleRec(new Point(d.x - 1, d.y), map,accessible,len);
        len = CalculeAccessibleRec(new Point(d.x + 1, d.y), map,accessible,len);
        len = CalculeAccessibleRec(new Point(d.x, d.y - 1), map,accessible,len);
        len = CalculeAccessibleRec(new Point(d.x, d.y + 1), map,accessible,len);
        return len;
    }
    return len;
}

public Point[][] CalculSuccesseur(int[][] map, Point[] accessible){
    Point[][]temp = new Point[accessible.length * 3][2];
    int pos = 0;
    for (int i = 0;i<accessible.length;i++) {
        pos = AddSuccesseur(accessible[i].x,accessible[i].y,-1,0,map,temp,pos);
        pos = AddSuccesseur(accessible[i].x,accessible[i].y,1,0,map,temp,pos);
        pos = AddSuccesseur(accessible[i].x,accessible[i].y,0,-1,map,temp,pos);
        pos = AddSuccesseur(accessible[i].x,accessible[i].y,0,1,map,temp,pos);
    }
    Point[][]successeur = new Point[pos][2];
    System.arraycopy(temp,0,successeur,0,pos);
    return successeur;
}
public int AddSuccesseur(int x,int y, int a, int b,int[][] map,Point[][] successeur, int pos){
    if (y+2*b>0 && x+2*a>0 && y+2*b<map.length && x+2*a<map[0].length && EstSuccesseur(map[y+b][x+a],map[y+2*b][x+2*a])){
        successeur[pos][0] = new Point(x+a,y+b);
        successeur[pos][1] = new Point(x+2*a,y+2*b);
        pos+=1;
    }
    return pos;
}

private boolean EstSuccesseur(int cas, int suiv){
    return  ((cas == CAISSE || cas== CAISSEONBUT)  && (suiv == SOL || suiv == BUT || suiv == POUSSEUR || suiv == POUSSEURONBUT));

}

public void AfficherMarque(Point[]accessible, Point[][] successeur){
    for (int i = 0;i<accessible.length;i++) {
        GameManager.interfacegraphique.Marquer(accessible[i].x,accessible[i].y,1);
    }
    for (int i = 0;i<successeur.length;i++) {
        GameManager.interfacegraphique.Marquer(successeur[i][1].x,successeur[i][1].y,2);
    }
}

}
