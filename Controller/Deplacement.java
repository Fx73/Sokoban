package Controller;

import Modele.GameManager;

import java.awt.*;

import static Controller.Coup.Dir.*;
import static Global.Tools.*;
import static Modele.GameManager.niveau;

public class Deplacement {
    static Point arrivee;
    public static Coup Djikstra(Point depart, Point arr, int[][] map){
        boolean [][] parcours;
        arrivee = arr;
        parcours = new boolean[GameManager.niveau().lignes][GameManager.niveau().colonnes];
        for (int i = 0; i < niveau().lignes; i++)
            for (int j = 0; j < niveau().colonnes; j++)
                if(map[i][j] == MUR || map[i][j] == CAISSE)
                    parcours[i][j] = true;


        return Clean(Djik_rec(depart,parcours));
    }

    static Coup Djik_rec(Point p, boolean [][] parcours){
        if(p.equals(arrivee))
            return new Coup(RIEN,false,null); //on est à l'arrivée

        parcours[p.y][p.x] = true;

        Coup[] dir = new Coup [4];
        if(!parcours[p.y-1][p.x])
            dir[0]  = Djik_rec(new Point(p.x,p.y-1),BoolArrCopy(parcours));
        if(!parcours[p.y+1][p.x])
            dir[1] = Djik_rec(new Point(p.x,p.y+1),BoolArrCopy(parcours));
        if(!parcours[p.y][p.x -1])
            dir[2] = Djik_rec(new Point(p.x-1,p.y),BoolArrCopy(parcours));
        if(!parcours[p.y][p.x +1])
            dir[3] = Djik_rec(new Point(p.x+1,p.y),BoolArrCopy(parcours));

        Coup.Dir min = RIEN;
        for (int i = 0; i < 4; i++) {
            if(dir[i] != null && (min == RIEN || Poid(dir[i]) < Poid(dir[Coup.DirToInt(min)])))
                min = Coup.IntToDir(i);
        }

        if(min == RIEN)
            return null;

        return new Coup(min,false,dir[Coup.DirToInt(min)]);
    }

    static private int Poid(Coup c){
        if(c.next == null)
            return 1;
        Coup cc = c.next;
        int count = 1;
        while (cc != null){
            cc=cc.next;
            count++;
        }
        return count;
    }

    static Coup Clean(Coup c){
        while(c !=null && c.dir == RIEN)
            c=c.next;
        if(c == null)
            return null;
        Coup cc = c;
        while(cc.next !=null){
            if(cc.next.dir ==RIEN)
                cc.next = cc.next.next;
            else
                cc=cc.next;
        }
        return c;
    }

    static boolean [][] BoolArrCopy(boolean [][] arr){
        boolean [][] newarr = new boolean[GameManager.niveau().lignes][GameManager.niveau().colonnes];
        for (int i = 0; i < arr.length; i++) {
            newarr[i] = arr[i].clone();
        }
        return newarr;
    }
}

