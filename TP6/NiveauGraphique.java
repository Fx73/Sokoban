package TP6;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import javax.imageio.ImageIO;

import Global.Configuration;
import TP1.Niveau;
 

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
			sol = ImageIO.read( Configuration.charge("Images/Pousseur.png"));
			pousseur = ImageIO.read( Configuration.charge("Images/Sol.png"));

		} catch (Exception e) {
			Configuration.instance().logger().severe("Impossible de charger l'image");
			System.exit(1);
        }
        
        niveau = n;
    }

    public void paintComponent(Graphics g) {
		System.out.println("Entree dans paintComponent : ");

		// Graphics 2D est le vrai type de l'objet passé en paramètre
		// Le cast permet d'avoir acces a un peu plus de primitives de dessin
		Graphics2D drawable = (Graphics2D) g;

		// On reccupere quelques infos provenant de la partie JComponent
		int width = getSize().width;
		int height = getSize().height;

		// On calcule le centre de la zone et un rayon
		Point center = new Point(width/2, height/2);

		// On efface tout
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, width, height);
		drawable.setPaint(Color.black);

		// On affiche
        drawable.drawImage(pousseur, center.x-20, center.y-20, 40, 40, null);
        int[][]map = niveau.mapGet();
        for(int i = 0; i< niveau.lignes;i++)
            for(int j = 0; i< niveau.colonnes;j++){
                drawable.drawImage(GetImage(map[i][j]), ((center.x - niveau.colonnes * 5) + i * 5), ((center.y - niveau.lignes * 5) + j * 5), 10, 10, null);
            }


	}


    Image GetImage(int a){
        switch(a){

            default : return pousseur;
        }
    }

}