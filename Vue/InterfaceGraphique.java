package Vue;

import Global.*;
import Modele.GameManager;

import javax.swing.*;
import java.awt.*;

import static Modele.GameManager.*;
import static java.lang.Integer.min;

// L'interface runnable déclare une méthode run
public class InterfaceGraphique implements Runnable {
	JFrame frame;
	private NiveauGraphique niveaugraphique;
	private NiveauGraphiqueAnime niveaugraphiqueanime;

	public void run() {
		// Creation d'une fenetre
		frame = new JFrame("Sokoban : Niveau " + lvlno.toString() + " : " + niveaux[lvlno].nom() );

		// Ajout de notre composant de dessin dans la fenetre
		if((Boolean) Configuration.Lis("Animations")) {
			niveaugraphiqueanime = new NiveauGraphiqueAnime();
			frame.add(niveaugraphiqueanime);
		}else{
			niveaugraphique = new NiveauGraphique();
			frame.add(niveaugraphique);
		}


		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(GameKeyListener);
		frame.addKeyListener(playercontroller.playerlistener);
		frame.addMouseListener(playercontroller.playermlistener);

		// On fixe la taille et on demarre
		frame.setSize(600, 600);
		frame.setVisible(true);

		if((Boolean) Configuration.Lis("Maximized"))
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);

	}

	public void Refresh(){
		frame.repaint();
	}
	public void RemoveFrame(){
		frame.dispose();
	}

	public Dimension GetSize(){return frame.getContentPane().getSize();}
	public int ImgSize(){return min(frame.getWidth() / GameManager.niveau().colonnes ,frame.getHeight() / GameManager.niveau().lignes);}

	public void toggleFullScreen(){
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if((Boolean) Configuration.Lis("Maximized")){
			device.setFullScreenWindow(null);
			Configuration.Ecris("Maximized",false);
		} else {
			device.setFullScreenWindow(frame);
			Configuration.Ecris("Maximized",true);
		}
	}

	public void toggleAnimation(){
		if((Boolean) Configuration.Lis("Animations")){
			frame.remove(niveaugraphiqueanime);
			niveaugraphiqueanime = null;
			niveaugraphique = new NiveauGraphique();
			frame.add(niveaugraphique);
			frame.revalidate();
			Refresh();
			Configuration.Ecris("Animations",false);
		} else {
			frame.remove(niveaugraphique);
			niveaugraphique = null;
			niveaugraphiqueanime = new NiveauGraphiqueAnime();
			frame.add(niveaugraphiqueanime);
			frame.revalidate();
			Refresh();
			Configuration.Ecris("Animations",true);
		}
	}


	public void Marquer(int x, int y, int valeur){
		if(niveaugraphique != null)	niveaugraphique.Marquer(x,y,valeur);
		if(niveaugraphiqueanime != null) niveaugraphiqueanime.Marquer(x,y,valeur);
	}
	public void Demarquer(){
		if(niveaugraphique != null)	niveaugraphique.Demarquer();
		if(niveaugraphiqueanime != null) niveaugraphiqueanime.Demarquer();
	}

}
