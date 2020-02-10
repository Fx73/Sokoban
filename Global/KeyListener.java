package Global;

import TP6.InterfaceGraphique;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyChar()=='f'){
            InterfaceGraphique.instance.toggleFullScreen();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
