package Controller;

import Modele.GameManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static Controller.Coup.Dir.*;
import static Global.Tools.*;
import static Modele.GameManager.*;
import static java.lang.Math.abs;

public class PlayerControler {
    int [][]map;
    int x,y;

    public PlayerControler(){
        ResetController();
    }

    public KeyListener playerlistener = new KeyListener(){
        @Override
        public void keyTyped(KeyEvent keyEvent) { }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            Coup coup = null;
            switch (keyEvent.getKeyCode()){
                case KeyEvent.VK_UP : coup = CreateCoup(HAUT); break;
                case KeyEvent.VK_DOWN : coup = CreateCoup(BAS); break;
                case KeyEvent.VK_LEFT: coup = CreateCoup(GAUCHE); break;
                case KeyEvent.VK_RIGHT : coup = CreateCoup(DROITE); break;
            }
            if(coup != null)
                historique.Faire(coup);
        }
        @Override
        public void keyReleased(KeyEvent keyEvent) { }
    };

    public MouseListener playermlistener = new MouseListener(){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Coup coup = null;
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Point arrivee  = GetPointInGrid(mouseEvent.getPoint());

            if((abs(arrivee.x -x) == 1 && arrivee.y - y == 0 ) || (abs(arrivee.y -y) == 1 && arrivee.x - x == 0 )) {
                if (arrivee.x - x == 0)
                    if (arrivee.y - y > 0)
                        coup = CreateCoup(BAS);
                    else
                        coup = CreateCoup(HAUT);
                else if (arrivee.x - x > 0)
                    coup = CreateCoup(DROITE);
                    else
                    coup = CreateCoup(GAUCHE);
                historique.Faire(coup);
                return;
            }

            coup = Deplacement.Djikstra(new Point(x,y),arrivee);
            if (coup == null) return;

            if(!executorService.isShutdown())executorService.shutdownNow();
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(this::AutoMove, 0, 100, TimeUnit.MILLISECONDS);
        }
        private void AutoMove() {
            historique.Faire(coup);
            coup = coup.next;
            if(coup == null)
                executorService.shutdownNow();
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

    Coup CreateCoup(Coup.Dir dir){
        if(!CanMove(dir))
            return null;
        Coup c = new Coup(dir,CanPush(dir));
        return c;
    }

    void Move(Point direction){
        CleanPlayerCase();
        x+=direction.x;
        y+=direction.y;
        AjoutPlayerCase();
    }
    void Demove(Point direction){
        CleanPlayerCase();
        x-=direction.x;
        y-=direction.y;
        AjoutPlayerCase();
    }

    void MoveCaisse(Point direction){
        CleanCaisseCase(x + direction.x ,y + direction.y);
        AjoutCaisseCase(x + direction.x * 2,y + direction.y  * 2);
    }

    void DemoveCaisse(Point direction){
        CleanCaisseCase(x + direction.x * 2,y + direction.y  * 2);
        AjoutCaisseCase(x + direction.x ,y + direction.y);
    }

    boolean CanMove(Coup.Dir dir){
        Point m = Coup.DirToPoint(dir);
        return (map[y+m.y][x+m.x] != MUR) && (map[y + m.y][x + m.x] != CAISSE || CanPush(dir));
    }

    boolean CanPush(Coup.Dir dir){
        Point m = Coup.DirToPoint(dir);
        return (map[y + m.y][x + m.x] == CAISSE || map[y + m.y][x + m.x] == CAISSEONBUT) && !(map[y+m.y][x+m.x] == CAISSE && (map[y + m.y * 2][x + m.x * 2] == MUR || map[y + m.y * 2][x + m.x * 2] == CAISSE));
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
