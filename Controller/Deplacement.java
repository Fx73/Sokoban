package Controller;

import Modele.GameManager;

import java.awt.*;

import static Global.Tools.CAISSE;
import static Global.Tools.MUR;
import static Modele.GameManager.niveau;

public class Deplacement {
    static Point arrivee;
    public static deplist Djikstra(Point depart, Point arr){
        boolean [][] parcours;
        arrivee = arr;
        parcours = new boolean[GameManager.niveau().lignes][GameManager.niveau().colonnes];
        int[][] map = GameManager.niveau().mapGet();
        for (int i = 0; i < niveau().lignes; i++)
            for (int j = 0; j < niveau().colonnes; j++)
                if(map[i][j] == MUR || map[i][j] == CAISSE)
                    parcours[i][j] = true;

        return Djik_rec(depart,parcours);
    }

    static deplist Djik_rec(Point p, boolean [][] parcours){
        if(p.equals(arrivee))
            return new deplist(-1,0,null);

        parcours[p.y][p.x] = true;

        deplist[] dir = new deplist [4];
        if(!parcours[p.y-1][p.x])
            dir[0]  = Djik_rec(new Point(p.x,p.y-1),BoolArrCopy(parcours));
        if(!parcours[p.y+1][p.x])
            dir[1] = Djik_rec(new Point(p.x,p.y+1),BoolArrCopy(parcours));
        if(!parcours[p.y][p.x -1])
            dir[2] = Djik_rec(new Point(p.x-1,p.y),BoolArrCopy(parcours));
        if(!parcours[p.y][p.x +1])
            dir[3] = Djik_rec(new Point(p.x+1,p.y),BoolArrCopy(parcours));

        int min = 4;
        for (int i = 0; i < 4; i++) {
            if(dir[i] != null && (min == 4 || dir[i].poid < dir[min].poid))
                min = i;
        }

        if(min == 4)
            return null;

        return new deplist(min,dir[min].poid+1,dir[min]);
    }

    public static class deplist{
        public deplist(int d, int p, deplist n){
            dep = d;
            poid = p;
            next = n;
        }
        public int dep;
        int poid;
        public deplist next;

    }

    static boolean [][] BoolArrCopy(boolean [][] arr){
        boolean [][] newarr = new boolean[GameManager.niveau().lignes][GameManager.niveau().colonnes];
        for (int i = 0; i < arr.length; i++) {
            newarr[i] = arr[i].clone();
        }
        return newarr;
    }
}

