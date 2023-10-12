package fundo;

import main.PainelJogo;
import main.Viadagens;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class FundoControler {
    PainelJogo tela;
    public Fundo[] fundo;
    public int codigoFundo[][];


    public FundoControler(PainelJogo tela){
        this.tela=tela;
        fundo=new Fundo[10];

        getImagemFundo();
        makemap();

    }

    public void getImagemFundo() {

        setup(0,"grama.jpg",false,false,false);
        setup(1,"bolo.jpg",true,false,false);
        setup(2,"suco.jpg",false,false,true);
        setup(3,"nerf.png",false,true,false);

    }
    private void setup(int index,String path,boolean colision, boolean creatine, boolean enemie){
        Viadagens utilidades=new Viadagens();

        try {
            fundo[index] =new Fundo();
            fundo[index].image=ImageIO.read(getClass().getResource("/Tiles/"+path));
            fundo[index].image=utilidades.redimensionarImagem(fundo[index].image,tela.getSizeOfTittle(),tela.getSizeOfTittle());
            fundo[index].collision=colision;fundo[index].creatine=creatine;fundo[index].enemie=enemie;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void makemap(){
        codigoFundo = new int[tela.getColuns()][tela.getLine()];
        for (int i = 0; i < tela.getLine(); i++) {
            for (int j = 0; j < tela.getColuns(); j++) {
            codigoFundo[j][i]=0;
            }
        }
        for (int i = 0; i < (tela.getLine()*tela.getColuns())/9; i++) {
            int c =(int) (Math.random()*tela.getColuns());
            int l =(int) (Math.random()*tela.getLine());

            if(codigoFundo[c][l]==1){
                i--;
            } else if (c==0 && l==1) {
                i--;
            } else if (c==1 && l==0) {
                i--;
            }else if (c==0 && l==0) {
                i--;
            }else codigoFundo[c][l]=1;
        }
        for (int i = 0; i < (tela.getLevel()/5)+1; i++) {
            int c =(int) (Math.random()*(tela.getColuns()));
            int l =(int) (Math.random()*(tela.getLine()));

            if(codigoFundo[c][l]==1){
                i--;
            }
            else{
                codigoFundo[c][l]=3;
                tela.setCreatines(tela.getCreatines()+1);
            }
        }
    }
    public  void draw(Graphics2D g2){
        int col=0;
        int lin =0;
        int x=0;
        int y=0;
        while (col<tela.getColuns() && lin< tela.getLine()){
            int codigoAtual=codigoFundo[col][lin];
            if(codigoAtual==4){
                fundo[4].rectx=col*tela.getSizeOfTittle();
                fundo[4].recty=lin*tela.getSizeOfTittle();
                fundo[4].rectw=tela.getSizeOfTittle();
                fundo[4].recth=tela.getSizeOfTittle();
            }
            g2.drawImage(fundo[codigoAtual].image,x,y,null);
            col++;
            x+=tela.getSizeOfTittle();
            if(col== tela.getColuns()){
                col=0;
                x=0;
                lin++;
                y+=tela.getSizeOfTittle();
            }
        }

    }
}
