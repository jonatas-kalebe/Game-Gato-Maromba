package entidades;

import main.KeyHandler;
import main.PainelJogo;
import main.Viadagens;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    PainelJogo tela;
    KeyHandler controle;

    public Player(PainelJogo tela, KeyHandler controle) {
        this.tela = tela;
        this.controle = controle;
        setDfautValues();
        getPlayerImage();
    }

    public void setDfautValues(){
        x=tela.getPlayerX();
        y= tela.getPlayerY();
        speed= tela.getPlayerSpeed();

        rectx=tela.getSizeOfTittle()/3;
        recty=tela.getSizeOfTittle()/3;
        rectw=tela.getSizeOfTittle()/5;
        recth=tela.getSizeOfTittle()/2;

        direction="down";

        solidArea=new Rectangle(rectx,recty,rectw,recth);
    }
    private void colisi(){
        rectx=x+(tela.getSizeOfTittle()/3);
        recty=y+(tela.getSizeOfTittle()/3);
        rectw=(tela.getSizeOfTittle()/5);
        recth=(tela.getSizeOfTittle()/2);
    }

    public void getPlayerImage(){
        Viadagens utilitario=new Viadagens();

        try {
            frente1= ImageIO.read(getClass().getResource("/player/frente1.png"));
            frente1=utilitario.redimensionarImagem(frente1,tela.getSizeOfTittle(), tela.getSizeOfTittle());
            frente2= ImageIO.read(getClass().getResource("/player/frente2.png"));
            frente2=utilitario.redimensionarImagem(frente2,tela.getSizeOfTittle(), tela.getSizeOfTittle());
            tras1= ImageIO.read(getClass().getResource("/player/tras1.png"));
            tras1=utilitario.redimensionarImagem(tras1,tela.getSizeOfTittle(), tela.getSizeOfTittle());
            tras2= ImageIO.read(getClass().getResource("/player/tras2.png"));
            tras2=utilitario.redimensionarImagem(tras2,tela.getSizeOfTittle(), tela.getSizeOfTittle());
            esquerda1= ImageIO.read(getClass().getResource("/player/esquerda1.png"));
            esquerda1=utilitario.redimensionarImagem(esquerda1,tela.getSizeOfTittle(), tela.getSizeOfTittle());
            esquerda2= ImageIO.read(getClass().getResource("/player/esquerda2.png"));
            esquerda2=utilitario.redimensionarImagem(esquerda2,tela.getSizeOfTittle(), tela.getSizeOfTittle());
            direita1= ImageIO.read(getClass().getResource("/player/direita1.png"));
            direita1=utilitario.redimensionarImagem(direita1,tela.getSizeOfTittle(), tela.getSizeOfTittle());
            direita2= ImageIO.read(getClass().getResource("/player/direita2.png"));
            direita2=utilitario.redimensionarImagem(direita2,tela.getSizeOfTittle(), tela.getSizeOfTittle());
        } catch (IOException e) {
           System.out.println(e.getMessage());
        }

    }

    public void update(){

        colisaoup=false;colisaodown=false;colisaorigth=false;colisaoleft=false;
        direction=controle.direction;
        tela.getColisoes().checarfundo(this,true);
        tela.getColisoes().checarInimigo(this);

        if(controle.cima&&!colisaoup&&y-speed>0){
            direction="up";
            y-=speed;
            contadorDeSprite++;
        }
        if(controle.baixo&&!colisaodown&&(y+tela.getSizeOfTittle())+speed<tela.gameHei){
            direction="down";
            y+=speed;
            contadorDeSprite++;
        }
        if(controle.direita&&!colisaorigth&&(x+tela.getSizeOfTittle())+speed< tela.gameWid){
            direction="right";
            x+=speed;
            contadorDeSprite++;
        }
        if(controle.esquerda&&!colisaoleft&&x-speed>0){
            direction="left";
            x-=speed;
            contadorDeSprite++;
        }

        if(contadorDeSprite>10){
            if(numeroDoSprite==1)numeroDoSprite=2;
            else numeroDoSprite=1;
            contadorDeSprite=0;
        }
        colisi();
    }
    public void draw(Graphics2D g2){


        BufferedImage image = null;
        switch (direction){
            case "up"-> {
                if(numeroDoSprite ==1){
                    image=frente1;
                }
                if(numeroDoSprite ==2){
                    image=frente2;
                }

            }
            case "down"-> {
                if(numeroDoSprite ==1){
                    image=tras1;
                }
                if(numeroDoSprite ==2){
                    image=tras2;
                }
            }
            case "right"->{
                if(numeroDoSprite ==1){
                    image=direita1;
                }
                if(numeroDoSprite ==2){
                    image=direita2;
                }
            }
            case "left"-> {
                if(numeroDoSprite ==1){
                    image=esquerda1;
                }
                if(numeroDoSprite ==2){
                    image=esquerda2;
                }
            }
        }
        g2.drawImage(image,x,y,null);
        //g2.fillRect(rectx,recty,rectw, recth);

    }




}
