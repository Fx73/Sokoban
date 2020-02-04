package TP6;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;

public class background extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g){
        g.drawImage(getClass().getResource("/Images/sokoban_tilesheet.png").getImage());

    }


}