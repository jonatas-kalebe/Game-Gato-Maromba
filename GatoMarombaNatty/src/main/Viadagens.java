package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Viadagens {

    public BufferedImage redimensionarImagem(BufferedImage original,int wid,int hei){
        BufferedImage imagegemRed = new BufferedImage(wid,hei,original.getType());

        Graphics2D g2= imagegemRed.createGraphics();
        g2.drawImage(original,0,0,wid,hei,null);
        g2.dispose();

        return imagegemRed;
    }
}
