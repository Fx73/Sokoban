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

	public void run() {
		// Creation d'une fenetre
		frame = new JFrame("Sokoban : Niveau " + lvlno.toString() + " : " + niveaux[lvlno].nom() );

		// Ajout de notre composant de dessin dans la fenetre
		if((Boolean) Configuration.Lis("Animations"))
			frame.add(new NiveauGraphiqueAnime());
		else
			frame.add(new NiveauGraphique());



		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(GameKeyListener);
		frame.addKeyListener(playercontroller.playerlistener);
		frame.addMouseListener(playercontroller.playermlistener);
        frame.addMouseListener(new HistoriqueMouseListener());
		// On fixe la taille et on demarre
		frame.setSize(600, 600);
		if((Boolean) Configuration.Lis("Maximized")) {
            frame.setUndecorated(true);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
        }
        frame.setVisible(true);

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
        frame.dispose();
        if((Boolean) Configuration.Lis("Maximized"))
            Configuration.Ecris("Maximized",false);
		 else
            Configuration.Ecris("Maximized",true);
		run();
	}

	public void toggleAnimation(){
        frame.dispose();
        if((Boolean) Configuration.Lis("Animations"))
            Configuration.Ecris("Animations",false);
		 else
			Configuration.Ecris("Animations",true);
		run();
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
