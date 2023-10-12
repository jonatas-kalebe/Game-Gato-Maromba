package entidades;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    public int rectx,recty,rectw,recth;
    public int speed;
    public BufferedImage frente1,frente2,tras1,tras2,direita1,direita2,esquerda1,esquerda2;
    public String direction;
    public int numeroDoSprite =1;
    public int contadorDeSprite=1;
    public Rectangle solidArea;
    public boolean colisaoup=false,colisaodown=false,colisaorigth=false,colisaoleft=false;
    int actionCounter=0;

}
