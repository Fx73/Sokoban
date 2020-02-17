package Global;

import TP7.GameManager;

import java.awt.*;
import java.util.Arrays;

import static Global.Tools.CAISSE;
import static Global.Tools.MUR;
import static TP7.GameManager.niveau;

public class Deplacement {
    static Point arrivee;
    static boolean [][] parcours;
    public static int[] Djikstra(Point depart, Point arr){
        arrivee = arr;
        parcours = new boolean[GameManager.niveau().lignes][GameManager.niveau().colonnes];
        int[][] map = GameManager.niveau().mapGet();
        for (int i = 0; i < niveau().lignes; i++)
            for (int j = 0; j < niveau().colonnes; j++)
                if(map[i][j] == MUR || map[i][j] == CAISSE)
                    parcours[i][j] = true;

        return Djik_rec(depart);
    }

    static int[] Djik_rec(Point p){
        if(p.equals(arrivee))
            return new int[0];

        parcours[p.y][p.x] = true;

        int[][] dir = new int [4][];
        if(!parcours[p.y-1][p.x])
            dir[0]  = Djik_rec(new Point(p.x,p.y-1));
        if(!parcours[p.y+1][p.x])
            dir[1] = Djik_rec(new Point(p.x,p.y+1));
        if(!parcours[p.y][p.x -1])
            dir[2] = Djik_rec(new Point(p.x-1,p.y));
        if(!parcours[p.y][p.x +1])
            dir[3] = Djik_rec(new Point(p.x+1,p.y));

        int min = 4;
        for (int i = 0; i < 4; i++) {
            if(dir[i] != null && (min == 4 || dir[i].length < dir[min].length))
                min = i;
        }

        if(min == 4)
            return null;

        int [] newdir = Arrays.copyOf(dir[min],dir[min].length + 1);
        newdir[newdir.length-1] = min;
        return newdir;
    }

}
