package main;

import entidades.*;
import fundo.FundoControler;

public class ColisionChecker {
    PainelJogo tela;
    FundoControler fundo;

    public ColisionChecker(PainelJogo tela, FundoControler fundo) {
        this.tela = tela;
        this.fundo=fundo;
    }
    public void checarfundo(Entity entidade,boolean player){
        int colisaoEsquerdaX=entidade.x+entidade.solidArea.x;
        int colisaoDireitax=entidade.x+entidade.solidArea.x+entidade.solidArea.width;
        int colisaoTopoY=entidade.y+entidade.solidArea.y;
        int colisaoInferiorY=entidade.y+entidade.solidArea.y+entidade.solidArea.height;

        int colunaEsquerda=colisaoEsquerdaX/tela.getSizeOfTittle();
        int colunaDireita=colisaoDireitax/tela.getSizeOfTittle();
        int linhaTop=colisaoTopoY/tela.getSizeOfTittle();
        int linhaInferior=colisaoInferiorY/tela.getSizeOfTittle();

        int fundo1,fundo2;

        int linhaTopx = (colisaoTopoY - entidade.speed) / tela.getSizeOfTittle();
        fundo1=tela.getControlefundo().codigoFundo[colunaEsquerda][linhaTopx];
        fundo2=tela.getControlefundo().codigoFundo[colunaDireita][linhaTopx];
        if(tela.getControlefundo().fundo[fundo1].collision || tela.getControlefundo().fundo[fundo2].collision){
            entidade.colisaoup=true;
        }
        if(player){
            if (tela.getControlefundo().fundo[fundo1].creatine ) {
                checarObjeto(colunaEsquerda,linhaTopx);
            }else if( tela.getControlefundo().fundo[fundo2].creatine) {
                checarObjeto(colunaDireita,linhaTopx);
            }
        }
        int linhaInferiorx = (colisaoInferiorY + entidade.speed) / tela.getSizeOfTittle();
        fundo1=tela.getControlefundo().codigoFundo[colunaEsquerda][linhaInferiorx];
        fundo2=tela.getControlefundo().codigoFundo[colunaDireita][linhaInferiorx];
        if(tela.getControlefundo().fundo[fundo1].collision || tela.getControlefundo().fundo[fundo2].collision){
            entidade.colisaodown=true;
        }
        if(player){
            if (tela.getControlefundo().fundo[fundo1].creatine ) {
                checarObjeto(colunaEsquerda,linhaInferiorx);
            } else if( tela.getControlefundo().fundo[fundo2].creatine) {
                checarObjeto(colunaDireita,linhaInferiorx);
            }
        }
        int colunaDireitax = (colisaoDireitax + entidade.speed) / tela.getSizeOfTittle();
        fundo1=tela.getControlefundo().codigoFundo[colunaDireitax][linhaTop];
        fundo2=tela.getControlefundo().codigoFundo[colunaDireitax][linhaInferior];
        if(tela.getControlefundo().fundo[fundo1].collision || tela.getControlefundo().fundo[fundo2].collision){
            entidade.colisaorigth=true;
        }
        if(player){
            if (tela.getControlefundo().fundo[fundo1].creatine ) {
                checarObjeto(colunaDireitax,linhaTop);
            } else if( tela.getControlefundo().fundo[fundo2].creatine) {
                checarObjeto(colunaDireitax,linhaInferior);
            }
        }

        int colunaEsquerdax = (colisaoEsquerdaX - entidade.speed) / tela.getSizeOfTittle();
        fundo1=tela.getControlefundo().codigoFundo[colunaEsquerdax][linhaTop];
        fundo2=tela.getControlefundo().codigoFundo[colunaEsquerdax][linhaInferior];
        if(tela.getControlefundo().fundo[fundo1].collision || tela.getControlefundo().fundo[fundo2].collision){
            entidade.colisaoleft=true;
        }
        if(player){
            if (tela.getControlefundo().fundo[fundo1].creatine ) {
                checarObjeto(colunaEsquerdax,linhaTop);
            } else if( tela.getControlefundo().fundo[fundo2].creatine) {
                checarObjeto(colunaEsquerdax,linhaInferior);
            }
        }
    }
    public void checarObjeto(int coluna,int linha){
        fundo.codigoFundo[coluna][linha]=0;
        tela.setCreatines(tela.getCreatines()-1);
        tela.creaCount++;
        System.out.println("gayy");
    }
    public void checarInimigo(Entity entidade){
        for (Suco inimigo: tela.inimigos) {
            if(interseccaoRet(entidade.rectx,entidade.recty,entidade.rectw,entidade.recth,
                    inimigo.rectx,inimigo.recty,inimigo.rectw, inimigo.recth)){
                inimigo.removeThis();
                tela.life--;
                break;
            }
        }
    }
    boolean interseccaoRet(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        return range(x1, x1+w1, x2, x2+w2) && range(y1, y1+h1, y2, y2+h2);
    }
    boolean range(int ax1, int ax2, int bx1, int bx2) {
        return Math.max(ax1, bx1) <= Math.min(ax2, bx2);
    }

}
