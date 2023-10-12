package entidades;

import fundo.FundoControler;
import main.PainelJogo;
import main.Viadagens;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Suco extends Entity{
    PainelJogo tela;
    FundoControler controle;
    public boolean deletable;
    boolean stop=false;

    public Suco(PainelJogo tela, FundoControler controle) {
        this.tela=tela;
        this.controle=controle;
        speed=(tela.getSizeOfTittle()/60)+1;
        solidArea=new Rectangle();
        setXY();
        setDefautValues();
        getImage();

        direction="down";
        deletable=true;
    }
    public void setDefautValues(){
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=tela.getSizeOfTittle();
        solidArea.height=tela.getSizeOfTittle();
    }
    private void colisi(){
        rectx=x;
        recty=y;
        rectw=tela.getSizeOfTittle();
        recth=tela.getSizeOfTittle();
    }
    public void getImage(){
        Viadagens utilitario=new Viadagens();
        try {
            frente1= ImageIO.read(getClass().getResource("/Tiles/suco.jpg"));
            frente1=utilitario.redimensionarImagem(frente1,tela.getSizeOfTittle(),tela.getSizeOfTittle());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeThis(){
        stop=true;

        recty=10000;
        rectx=10000;
        recth=0;
        rectw=0;

    }
    public void setAction(){
        actionCounter++;
        if(actionCounter>=24){
            int i =(int) (Math.random()*4);
            if(i==0&&y>0){
                direction="up";
            } else if (i==1 && y<tela.gameHei) {
                direction="down";
            }else if(i==2 && x<tela.gameWid){
                direction="right";
            } else if (i==3 &&i<=100 && x>0 ){
                direction="left";
            }
            actionCounter=0;
        }
    }
    public void setXY(){
        int c;int l;
        do {
            c =(int) (Math.random()*(tela.getColuns()-1));
            l =(int) (Math.random()*(tela.getLine()-1));
        }while (controle.codigoFundo[c][l]!=0);
       x=c*tela.getSizeOfTittle();
       y=(l*tela.getSizeOfTittle())-1;
    }
    public void update(){
        if(!stop){
            setAction();
            colisaoup=false;colisaodown=false;colisaorigth=false;colisaoleft=false;
            tela.getColisoes().checarfundo(this,false);

            if(direction=="up"&&!colisaoup&&y-(2*speed)>0+1){
                y-=speed;
                contadorDeSprite++;
            }
            if(direction=="down"&&!colisaodown && y+(2*speed)<(tela.gameHei-tela.getSizeOfTittle())-1){
                y+=speed;
                contadorDeSprite++;
            }
            if(direction=="right"&&!colisaorigth && x+(2*speed)<tela.gameWid-tela.getSizeOfTittle()){
                x+=speed;
                contadorDeSprite++;
            }
            if(direction=="left"&&!colisaoleft && x-(2*speed)>0+1){
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
    }
    public void draw(Graphics2D g2){
        if(!stop){
            BufferedImage image =frente1;
            g2.drawImage(image,x,y,null);
        }
       // g2.fillRect(rectx,recty,rectw, recth);
    }
}
