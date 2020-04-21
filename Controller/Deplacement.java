package Controller;

import Modele.GameManager;

import java.awt.*;

import static Controller.Coup.Dir.*;
import static Controller.Coup.PointToDir;
import static Global.Tools.*;
import static Modele.GameManager.niveau;

public class Deplacement {
    public static Coup Dijkstra(Point depart, Point arr, int[][] map){
        if(depart.equals(arr)) return null;
        boolean [][] parcours = new boolean[GameManager.niveau().lignes][GameManager.niveau().colonnes];
        for (int i = 0; i < niveau().lignes; i++)
            for (int j = 0; j < niveau().colonnes; j++)
                if(map[i][j] == MUR || map[i][j] == CAISSE)
                    parcours[i][j] = true;

        return Dijk_File(depart,arr,parcours);
    }


    static Coup Dijk_File(Point p, Point arrivee, boolean [][] parcours){
        Chaine deb = new Chaine(p,null);
        Chaine fin = deb;
        parcours[p.y][p.x] = true;
        while(deb != null && !deb.p.equals(arrivee)){
            if(!parcours[deb.p.y-1][deb.p.x]) {
                fin.next = new Chaine(new Point(deb.p.x, deb.p.y - 1), deb);
                parcours[deb.p.y-1][deb.p.x] = true;
                fin = fin.next;
            }
            if(!parcours[deb.p.y+1][deb.p.x]) {
                fin.next = new Chaine(new Point(deb.p.x, deb.p.y + 1), deb);
                parcours[deb.p.y+1][deb.p.x] = true;
                fin = fin.next;
            }
            if(!parcours[deb.p.y][deb.p.x - 1]) {
                fin.next = new Chaine(new Point(deb.p.x - 1, deb.p.y), deb);
                parcours[deb.p.y][deb.p.x - 1] = true;
                fin = fin.next;
            }
            if(!parcours[deb.p.y][deb.p.x + 1]) {
                fin.next = new Chaine(new Point(deb.p.x + 1, deb.p.y), deb);
                parcours[deb.p.y][deb.p.x + 1] = true;
                fin = fin.next;
            }
            deb = deb.next;
        }
        if(deb==null)return null;

        Coup c = new Coup(PointToDir(new Point(deb.p.x - deb.parent.p.x,deb.p.y - deb.parent.p.y)),false,null);
        deb = deb.parent;
        while(deb.parent != null){
            c = new Coup(PointToDir(new Point(deb.p.x - deb.parent.p.x,deb.p.y - deb.parent.p.y)),false,c);
            deb = deb.parent;
        }

        return c;
    }

    public static Point[] Dijkstra(Point depart, int[][] map){
        boolean [][] parcours = new boolean[GameManager.niveau().lignes][GameManager.niveau().colonnes];
        for (int i = 0; i < niveau().lignes; i++)
            for (int j = 0; j < niveau().colonnes; j++)
                if(map[i][j] == MUR || map[i][j] == CAISSE)
                    parcours[i][j] = true;

        return DikjFileAccessible(depart,parcours);
    }


    static Point[] DikjFileAccessible(Point p, boolean [][] parcours){
        Chaine deb = new Chaine(p,null);
        Chaine fin = deb;
        Chaine save = deb;
        int n = 0;
        parcours[p.y][p.x] = true;
        while(deb != null){
            if(!parcours[deb.p.y-1][deb.p.x]) {
                fin.next = new Chaine(new Point(deb.p.x, deb.p.y - 1), deb);
                parcours[deb.p.y-1][deb.p.x] = true;
                fin = fin.next;
            }
            if(!parcours[deb.p.y+1][deb.p.x]) {
                fin.next = new Chaine(new Point(deb.p.x, deb.p.y + 1), deb);
                parcours[deb.p.y+1][deb.p.x] = true;
                fin = fin.next;
            }
            if(!parcours[deb.p.y][deb.p.x - 1]) {
                fin.next = new Chaine(new Point(deb.p.x - 1, deb.p.y), deb);
                parcours[deb.p.y][deb.p.x - 1] = true;
                fin = fin.next;
            }
            if(!parcours[deb.p.y][deb.p.x + 1]) {
                fin.next = new Chaine(new Point(deb.p.x + 1, deb.p.y), deb);
                parcours[deb.p.y][deb.p.x + 1] = true;
                fin = fin.next;
            }
            deb = deb.next;
            n++;
        }

        Point[] accessible = new Point[n];
        n=0;
        while(save != null){
            accessible[n] = save.p;
            save = save.next;
            n++;
        }

        return accessible;
    }


    static boolean [][] BoolArrCopy(boolean [][] arr){
        boolean [][] newarr = new boolean[GameManager.niveau().lignes][GameManager.niveau().colonnes];
        for (int i = 0; i < arr.length; i++) {
            newarr[i] = arr[i].clone();
        }
        return newarr;
    }

    static class Chaine{
        Point p;
        Chaine parent;
        Chaine next;
        Chaine(Point _p, Chaine _parent){
            p = new Point(_p.x,_p.y);
            parent = _parent;
        }
    }
}

