package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickHandler implements MouseListener {
    TelaInicial telinha;

    public ClickHandler(TelaInicial telinha) {
        this.telinha = telinha;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        telinha.play();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
