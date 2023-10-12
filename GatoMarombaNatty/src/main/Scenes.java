package main;

import java.awt.*;

public class Scenes {
    PainelJogo tela;
    Graphics2D g2;
    Color ngayyyyy;
    int alpha=124;
    public Scenes(PainelJogo tela) {
        this.tela = tela;
    }
    public void draw(Graphics2D g2){
        this.g2=g2;
        transition();

    }
    public void transition(){
        if(alpha>4){
            g2.setColor(new Color(0, 0, 0, alpha));
            g2.fillRect(0,0,tela.gameWid,tela.gameHei);
            alpha-=5;
        }
    }
    public void newtransition(){
        alpha=124;
    }
}
