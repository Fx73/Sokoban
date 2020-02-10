package TP6;

import javax.swing.*;
import TP1.Niveau;


// L'interface runnable déclare une méthode run
public class InterfaceGraphique implements Runnable {

	Niveau[] niveaux;
	Integer lvlno = 0;

	public InterfaceGraphique(Niveau[] n){
		niveaux = n;
	}

	public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame("Sokoban : Niveau " + lvlno.toString() + " : " + niveaux[lvlno].nom() );

		// Ajout de notre composant de dessin dans la fenetre
		frame.add(new NiveauGraphique(niveaux[lvlno]));

		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on demarre
		frame.setSize(500, 300);
		frame.setVisible(true);
	}
}
