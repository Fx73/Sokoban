package Vue;

import Modele.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Global.Tools.*;
import static java.lang.Integer.min;


public class NiveauGraphique extends JComponent {
    ImageBankAll ibankall;
    ImageBankMur ibankmur;
    Animation animationpousseur;
    public NiveauGraphique(){
        ibankall = new ImageBankAll();
        ibankmur = new ImageBankMur();
        animationpousseur = new AnimationPousseur(new ImageBankPousseur());
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

        // On affiche
        int[][]map = GameManager.niveau().mapGet();
        for(int i = 0; i< GameManager.niveau().colonnes;i++)
            for(int j = 0; j< GameManager.niveau().lignes;j++){
                int xplace = ((center.x - GameManager.niveau().colonnes * (imgsize/2)) + i * imgsize);
                int yplace = ((center.y - GameManager.niveau().lignes * (imgsize/2)) + j * imgsize);
                drawable.drawImage(ibankall.sol, xplace, yplace, imgsize, imgsize, null);

                if(map[j][i] == POUSSEURONBUT || map[j][i] == POUSSEUR)
                    drawable.drawImage(animationpousseur.GetAnimation(), xplace,yplace, imgsize, imgsize, null);
                else if(map[j][i] == MUR || map[j][i] == 0){
                    ibankmur.SetCoord(j,i);
                    drawable.drawImage(ibankmur.GetImage(map[j][i]), xplace, yplace, imgsize, imgsize, null);
                }
                else
                    drawable.drawImage(ibankall.GetImage(map[j][i]), xplace, yplace, imgsize, imgsize, null);
            }
	}








}