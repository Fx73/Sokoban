package TP6;

import javax.swing.*;
import TP1.Niveau;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// L'interface runnable déclare une méthode run
public class InterfaceGraphique implements Runnable {
	public static InterfaceGraphique instance;
	Niveau[] niveaux;
	Integer lvlno = 0;
	Boolean maximized = false;
	JFrame frame;

	public InterfaceGraphique(Niveau[] n){
		niveaux = n;
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent keyEvent) { }

			@Override
			public void keyPressed(KeyEvent keyEvent) {
				if (keyEvent.getKeyChar() == 'f')
					toggleFullScreen();
			}
			@Override
			public void keyReleased(KeyEvent keyEvent) { }
		});
	}

	public void run() {
		// Creation d'une fenetre
		frame = new JFrame("Sokoban : Niveau " + lvlno.toString() + " : " + niveaux[lvlno].nom() );

		// Ajout de notre composant de dessin dans la fenetre
		frame.add(new NiveauGraphique(niveaux[lvlno]));

		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on demarre
		frame.setSize(600, 600);
		frame.setVisible(true);
	}

	public void toggleFullScreen(){
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if(maximized){
			device.setFullScreenWindow(null);
			maximized = false;
		} else {
			device.setFullScreenWindow(frame);
			maximized = false;
		}
	}

}
