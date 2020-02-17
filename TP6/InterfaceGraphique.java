package TP6;

import javax.swing.*;
import java.awt.*;
import Global.Configuration;
import TP7.GameManager;

import static TP7.GameManager.*;
import static java.lang.Integer.min;

// L'interface runnable déclare une méthode run
public class InterfaceGraphique implements Runnable {
	JFrame frame;

	public void run() {
		// Creation d'une fenetre
		frame = new JFrame("Sokoban : Niveau " + lvlno.toString() + " : " + niveaux[lvlno].nom() );

		// Ajout de notre composant de dessin dans la fenetre
		frame.add(new NiveauGraphique());

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
}
