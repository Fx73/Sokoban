package TP6;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;

import Global.Configuration;
import TP1.Niveau;

import static Global.Tools.*;
import static java.lang.Integer.min;


class NiveauGraphique extends JComponent {

    Image but;
    Image caisse;
    Image caissesurbut;
    Image sol;
    Image pousseur;


    Image mur;
    Image murseulgauche;
    Image murseuldroit;
    Image murseulhaut;
    Image murseulbas;
    Image murgauche;
    Image murdroit;
    Image murhaut;
    Image murbas;
    Image murhautgauche;
    Image murhautdroit;
    Image murbasgauche;
    Image murbasdroit;
    Image murgauchedroit;
    Image murhautbas;
    Image murplein;

    
    Niveau niveau;

    public NiveauGraphique(Niveau n){

		try {
            but = ImageIO.read( Configuration.charge("Images/But.png"));
            caisse = ImageIO.read( Configuration.charge("Images/Caisse.png"));
			caissesurbut = ImageIO.read( Configuration.charge("Images/Caisse_sur_but.png"));
            sol = ImageIO.read( Configuration.charge("Images/Sol.png"));
			pousseur = ImageIO.read( Configuration.charge("Images/Pousseur.png"));
            mur = ImageIO.read( Configuration.charge("Images/Murs/Mur.png"));


            murseulgauche = ImageIO.read( Configuration.charge("Images/Murs/Murseulgauche.png"));
            murseuldroit = ImageIO.read( Configuration.charge("Images/Murs/Murseuldroit.png"));
            murseulhaut = ImageIO.read( Configuration.charge("Images/Murs/Murseulhaut.png"));
            murseulbas = ImageIO.read( Configuration.charge("Images/Murs/Murseulbas.png"));
            murgauche = ImageIO.read( Configuration.charge("Images/Murs/Murgauche.png"));
            murdroit = ImageIO.read( Configuration.charge("Images/Murs/Murdroit.png"));
            murhaut = ImageIO.read( Configuration.charge("Images/Murs/Murhaut.png"));
            murbas = ImageIO.read( Configuration.charge("Images/Murs/Murbas.png"));
            murhautgauche = ImageIO.read( Configuration.charge("Images/Murs/Murhautgauche.png"));
            murhautdroit = ImageIO.read( Configuration.charge("Images/Murs/Murhautdroit.png"));
            murbasgauche = ImageIO.read( Configuration.charge("Images/Murs/Murbasgauche.png"));
            murbasdroit = ImageIO.read( Configuration.charge("Images/Murs/Murbasdroit.png"));
            murplein = ImageIO.read( Configuration.charge("Images/Murs/Murplein.png"));
            murhautbas = ImageIO.read( Configuration.charge("Images/Murs/Murhautbas.png"));
            murgauchedroit = ImageIO.read( Configuration.charge("Images/Murs/Murgauchedroit.png"));


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
                drawable.drawImage(sol, ((center.x - niveau.lignes * (imgsize/2)) + i * imgsize), ((center.y - niveau.colonnes * (imgsize/2)) + j * imgsize), imgsize, imgsize, null);
                drawable.drawImage(GetImage(map[j][i],j,i), ((center.x - niveau.lignes * (imgsize/2)) + i * imgsize), ((center.y - niveau.colonnes * (imgsize/2)) + j * imgsize), imgsize, imgsize, null);
                if(map[j][i] == POUSSEURONBUT) drawable.drawImage(pousseur, ((center.x - niveau.lignes * (imgsize/2)) + i * imgsize), ((center.y - niveau.colonnes * (imgsize/2)) + j * imgsize), imgsize, imgsize, null);

            }

	}


    Image GetImage(int a, int x, int y){
        switch(a){
            case SOL: return sol;
            case BUT: case POUSSEURONBUT : return but;
            case CAISSE : return caisse;
            case CAISSEONBUT : return caissesurbut;
            case POUSSEUR : return pousseur;
            case MUR: case 0:
                return GetMur(x,y);
            default :
                Configuration.instance().logger().warning("Objet inconnue dans le niveau");
                return null;
        }
    }

    Image GetMur(int x, int y) {
        int hash = 0;
        if(IsMur(x-1,y))
            hash +=1;
        if(IsMur(x,y-1))
            hash +=10;
        if(IsMur(x,y+1))
            hash +=100;
        if(IsMur(x+1,y))
            hash +=1000;

        switch (hash){
            case 0 : default : return mur;
            case 1 : return murseulbas;
            case 10 : return murseuldroit;
            case 100 : return murseulgauche;
            case 1000 : return murseulhaut;
            case 11 : return murbasdroit;
            case 101 : return murbasgauche;
            case 1010 : return murhautdroit;
            case 1100 : return murhautgauche;
            case 111 : return murbas;
            case 1101 : return murgauche;
            case 1110 : return murhaut;
            case 1011 : return murdroit;
            case 1001 : return murhautbas;
            case 110 : return murgauchedroit;
            case 1111: return murplein;
        }
    }

    Boolean IsMur(int x, int y){
        if(x == -1 || y == -1 )
            return true;
        if(x > niveau.colonnes || y>niveau.lignes)
            return true;
        return niveau.mapGet()[x][y] == 35 || niveau.mapGet()[x][y] == 0;
    }

}