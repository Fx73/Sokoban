package Controller;

import Global.Tools;
import Modele.GameManager;

import  java.lang.System;
import java.awt.Point;
import java.lang.Math;
import java.util.Arrays;
import java.util.Hashtable;

import static Global.Tools.*;

public class IA {

    Hashtable<Integer, Integer> ht;

    int nb;
public IA(){
    ht = new Hashtable<>();

}


public void SolveLevel(){
    Coup reponse = SolveRec(GameManager.playercontroller.x,GameManager.playercontroller.y,GameManager.niveau().mapGetCopy());
    GameManager.playercontroller.LauchAutoMove(reponse);
    Tools.Print("Fini");
}

public Coup SolveRec(int px, int py, int[][] map){
    Point[] acce = Deplacement.Dijkstra(new Point(px,py),map);
    Point[][] succ = CalculSuccesseur(map,acce);
    if(ht.get(Arrays.deepHashCode(map)*Arrays.deepHashCode(succ)) != null) return null;
    ht.put(Arrays.deepHashCode(map)*Arrays.deepHashCode(succ),nb);
    nb++;
    Tools.Print("Configuration testee : "+nb);
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
public double[] CalculerHeuristiqueChemin2(Point[][] succ,int[][]map){
    double[] h = new double[succ.length];
    for (int k = 0; k < succ.length; k++) {
        double min = 2;
        Point pold = succ[k][0];
        Point pnew = succ[k][1];
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == BUT || map[i][j] == POUSSEURONBUT){
                    //min = HeuristiqueDistance(pold,pnew,new Point(j,i));
                    min = HeuristiqueChemin(pold,pnew,new Point(j,i),map);
                }
            }
        }
        h[k] = min;
    }
    return  h;

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
                    //min = HeuristiqueDistance(pold,pnew,new Point(j,i));
                    min = HeuristiqueChemin(pold,pnew,new Point(j,i),map);
                }
            }
        }
        h[k] = min;
    }
    return  h;
}

double HeuristiqueDistance(Point pold, Point pnew, Point but){
    return 1 + (Math.sqrt(Math.pow(pnew.x - but.x,2) + Math.pow(pnew.y - but.y,2)) - Math.sqrt(Math.pow(pold.x - but.x,2) + Math.pow(pold.y - but.y,2)));
}
int HeuristiqueChemin(Point pold, Point pnew, Point but, int[][] map){
    DelCaisse(pold,map);
    Coup c1 = Deplacement.Dijkstra(pold,but,map);
    Coup c2 = Deplacement.Dijkstra(pnew,but,map);
    AddCaisse(pold,map);
    int a1 = 0,a2 = 0;
    while (c1!=null){
        c1=c1.next;
        a1++;
    }
    while (c2!=null){
        c2=c2.next;
        a2++;
    }
    return 1 + (a2 - a1);
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
    Coup c = Deplacement.Dijkstra(depart,arrivee,map);
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
void DelPousseur(Point p, int[][]map){
        if(map[p.y][p.x] == POUSSEURONBUT)
            map[p.y][p.x] = BUT;
        else
            map[p.y][p.x] = SOL;
}
void AddPousseur(Point p, int[][]map){
        if(map[p.y][p.x] == BUT)
            map[p.y][p.x] = POUSSEURONBUT;
        else
            map[p.y][p.x] = POUSSEUR;
}

public void MarquerTout(Point playerposition){
    Point[] accessible = Deplacement.Dijkstra(playerposition,GameManager.niveau().mapGet());
    Point[][] successeur = CalculSuccesseur(GameManager.niveau().mapGet(),accessible);
    AfficherMarque(accessible,successeur);
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
