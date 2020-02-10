package TP6;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import javax.imageio.ImageIO;

import Global.Configuration;
import TP1.Niveau;

import static java.lang.Integer.min;
import static java.lang.Integer.max;


class NiveauGraphique extends JComponent {

    Image but;
    Image caisse;
    Image caissesurbut;
    Image mur;
    Image sol;
    Image pousseur;
    
    Niveau niveau;

    public NiveauGraphique(Niveau n){

		try {
            but = ImageIO.read( Configuration.charge("Images/But.png"));
            caisse = ImageIO.read( Configuration.charge("Images/Caisse.png"));
			caissesurbut = ImageIO.read( Configuration.charge("Images/Caisse_sur_but.png"));
			mur = ImageIO.read( Configuration.charge("Images/Mur.png"));
			sol = ImageIO.read( Configuration.charge("Images/Sol.png"));
			pousseur = ImageIO.read( Configuration.charge("Images/Pousseur.png"));

		} catch (Exception e) {
			Configuration.instance().logger().severe("Impossible de charger l'image");
			System.exit(1);
        }
        
        niveau = n;
    }

    public void paintComponent(Graphics g) {
		System.out.println("Entree dans paintComponent : map ("+niveau.lignes+"x"+niveau.colonnes+")");

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
        int imgsize = min(width / niveau.lignes ,height / niveau.colonnes);

		// On affiche
        int[][]map = niveau.mapGet();
        for(int i = 0; i< niveau.lignes;i++)
            for(int j = 0; j< niveau.colonnes;j++){
                /*Image a gauche*/ //drawable.drawImage(GetImage(map[j][i]), 1+i * imgsize,  1+j * imgsize, imgsize, imgsize, null);
                /*Image au centre*/ drawable.drawImage(GetImage(map[j][i]), ((center.x - niveau.lignes * (imgsize/2)) + i * imgsize), ((center.y - niveau.colonnes * (imgsize/2)) + j * imgsize), imgsize, imgsize, null);
            }


	}


    Image GetImage(int a){
        switch(a){
            case 35: return mur;
            case 32: return sol;
            case 46: return but;
            case 36 : return caisse;
            case 64 : return pousseur;
            case 0: return mur;
            default :
                Configuration.instance().logger().warning("Objet inconnue dans le niveau");
                return pousseur;
        }
    }

}