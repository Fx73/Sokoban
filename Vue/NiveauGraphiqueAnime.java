package Vue;

import Modele.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Global.Tools.*;
import static java.lang.Integer.min;


public class NiveauGraphiqueAnime extends JComponent {
    ImageBankCaisse ibankcaisse;
    ImageBankMur ibankmur;
    ImageBankArrow ibankarrow;
    ImageBankSol ibanksol;
    Animation animationpousseur;
    private int marqueur[][];

    public NiveauGraphiqueAnime(){
        ibankcaisse = new ImageBankCaisse();
        ibankmur = new ImageBankMur();
        ibankarrow = new ImageBankArrow();
        ibanksol = new ImageBankSol();
        animationpousseur = new AnimationPousseur(new ImageBankPousseur());
        new Timer(160, new ActionListener() { @Override public void actionPerformed(ActionEvent actionEvent) { tictac();}}).start();
        marqueur = new int[GameManager.niveau().lignes][GameManager.niveau().colonnes];
    }

    void tictac () {
        animationpousseur.AvancerAnimation();

        GameManager.RefreshScreen();
    }

    public void Marquer(int x, int y, int valeur){
        marqueur[y][x] = valeur;
    }
    public void Demarquer(){
        marqueur = new int[GameManager.niveau().lignes][GameManager.niveau().colonnes];
    }

    public void paintComponent(Graphics g) {
		// Graphics 2D est le vrai type de l'objet passé en paramètre
		// Le cast permet d'avoir acces a un peu plus de primitives de dessin
		Graphics2D drawable = (Graphics2D) g;

		// On reccupere quelques infos provenant de la partie JComponent
		int width = getSize().width;
		int height = getSize().height;

		// On calcule le centre de la zone et un rayon
		Point center = new Point(width/2, height/2);

		// On efface tout
		drawable.setPaint(Color.black);
		drawable.fillRect(0, 0, width, height);
		drawable.setPaint(Color.black);

		//Calcul de la taille d'un objet
        int imgsize = min(width / GameManager.niveau().colonnes ,height / GameManager.niveau().lignes);

        // On affiche le jeu
        int[][]map = GameManager.niveau().mapGet();
        for(int i = 0; i< GameManager.niveau().colonnes;i++)
            for(int j = 0; j< GameManager.niveau().lignes;j++){
                int xplace = ((center.x - GameManager.niveau().colonnes * (imgsize/2)) + i * imgsize);
                int yplace = ((center.y - GameManager.niveau().lignes * (imgsize/2)) + j * imgsize);
                ibanksol.SetCoord(j,i);
                drawable.drawImage(ibanksol.GetImage(marqueur[j][i]), xplace, yplace, imgsize, imgsize, null);

                if(map[j][i] == POUSSEURONBUT || map[j][i] == POUSSEUR)
                    drawable.drawImage(animationpousseur.GetAnimation(), xplace,yplace, imgsize, imgsize, null);
                else if(map[j][i] == MUR || map[j][i] == 0){
                    ibankmur.SetCoord(j,i);
                    drawable.drawImage(ibankmur.GetImage(map[j][i]), xplace, yplace, imgsize, imgsize, null);
                }
                else if(map[j][i] == CAISSE || map[j][i] == CAISSEONBUT)
                    drawable.drawImage(ibankcaisse.GetImage(map[j][i]), xplace, yplace, imgsize, imgsize, null);
            }

        //On affiche les fleches
        drawable.drawImage(ibankarrow.GetImage(0),0,0,imgsize*2,imgsize*2,null);
        drawable.drawImage(ibankarrow.GetImage(1),imgsize*2,0,imgsize*2,imgsize*2,null);

    }








}