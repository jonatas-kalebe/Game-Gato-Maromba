package main;

import fundo.Fundo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    PainelJogo tela;
    BufferedImage elementos[]=new BufferedImage[10];
    Font arial_40;
    Color back;

    public UI(PainelJogo tela) {
        this.tela = tela;
        arial_40=new Font("Arial",Font.PLAIN,25);
        back=new Color(103,106,104,20);
        setHearts();
    }
    private void setHearts(){
        setup(0,"/UI/heart.png");
        setup(1,"/UI/lost.png");
        setup(2,"/UI/temp.png");
        setup(3,"/Tiles/nerf.png");

    }
    private void setup(int index,String path){
        Viadagens utilidades=new Viadagens();

        try {
            elementos[index]= ImageIO.read(getClass().getResource(path));
            elementos[index]=utilidades.redimensionarImagem(elementos[index],35,35);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2){
        int x=1240,y=0,minutos=tela.getSeconds()/60,time=tela.getSeconds()%60;

        //draw space
        g2.setColor(back);
        g2.setFont(arial_40);
        g2.fillRect(x,y,tela.screenSize.width, tela.gameHei);

        //draw life
        y=50;
        for (int i = 0; i < 3; i++) {
            if(i<tela.life){
                g2.drawImage(elementos[0],x+(40*i),y,null);
            }
            else g2.drawImage(elementos[1],x+(40*i),y,null);
        }

        //draw time
        y=160;
        g2.setColor(Color.WHITE);
        g2.drawImage(elementos[2],x-3,y,null);
        g2.drawString(String.format("%02d:%02d",minutos,time),x+42,y+27);

        //draw creatines
        y=220;
        g2.drawImage(elementos[3],x,y,null);
        g2.drawString(String.format("%02d",tela.creaCount),x+48,y+30);

        //draw level
        y=280;
        g2.drawString(String.format("Level: %d",tela.getLevel()),x,y+30);




    }
}
