package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean cima,baixo,direita,esquerda;
    public String direction="down";
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 38,87->{
                cima=true;
                direction="up";

            }
            case 40,83-> {
                baixo=true;
                direction = "down";
            }
            case 37,65->{
                esquerda=true;
                direction="left";
            }
            case 39,68-> {
                direita=true;
                direction="right";
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case 38,87->{
                cima=false;
            }
            case 40,83-> {
                baixo=false;
            }
            case 37,65->{
                esquerda=false;
            }
            case 39,68-> {
                direita=false;
            }
        }
    }
}
