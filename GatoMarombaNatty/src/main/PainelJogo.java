package main;

import entidades.Player;
import entidades.Suco;
import fundo.FundoControler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PainelJogo extends JPanel implements Runnable{

    //Screen Settings
    private final Toolkit tela=Toolkit.getDefaultToolkit();
    public Dimension screenSize = tela.getScreenSize();
    private int screeenheigth=(screenSize.height-75);
    private int level=0;
    private int line = 9+level;
    double ratio = (16.0/9.0)*line;
    private int coluns =(int) ratio;
    private int sizeOfTittle=screeenheigth/line;
    private final int FPS =60;
    public int gameHei = sizeOfTittle * line;
    public int gameWid = sizeOfTittle * coluns;


    //set player position

    private int playerX = 0*sizeOfTittle;
    private int playerY=0*sizeOfTittle;
    private int playerSpeed = (2*(sizeOfTittle/FPS))+1;

    //set game clear conditions

    private int creatines=0;
    public int creaCount=0;
    public int life=3;

    //system
    Main main=new Main();
    private Thread rodarJogo;
    private int seconds;
    private KeyHandler controle= new KeyHandler();
    private FundoControler controlefundo=new FundoControler(this);
    private Player jogador = new Player(this,controle);
    public ColisionChecker colisoes = new ColisionChecker(this,controlefundo);
    private Sound musica = new Sound();
    private final Sound SE = new Sound();
    private UI ui=new UI(this);
    private Scenes cenas = new Scenes(this);


    //monster
    List <Suco> inimigos =new ArrayList<>();

    public PainelJogo(){
        //start.play();
        this.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(controle);
        this.setFocusable(true);
    }


    public void startGame(){
        inimigos.removeAll(inimigos);
        for (int i = 0; i <= level; i++) {
            inimigos.add(new Suco(this,controlefundo));
        }
        rodarJogo=new Thread(this);
        rodarJogo.start();
    }
    private void newGame(){
        //timer update
        main.timer=seconds;

        //game break
        rodarJogo=null;

        //system restart
        level++;
        line = 9+level;
        ratio = (16.0/9.0)*line;
        coluns =(int) ratio;
        sizeOfTittle=screeenheigth/line;
        gameHei = sizeOfTittle * line;
        gameWid = sizeOfTittle * coluns;

        //player restart
        jogador.x=0;
        jogador.y=0;
        playerSpeed = (2*sizeOfTittle/FPS)+1;
        jogador.setDfautValues();

        //background restart
        controlefundo.getImagemFundo();
        jogador.getPlayerImage();

        //juice restart
        for (Suco inimigo:inimigos) {
            inimigo.getImage();
            inimigo.setDefautValues();
        }

        //game start
        controlefundo.makemap();
        cenas.newtransition();
        startGame();
    }
    private void gameOver(){
        rodarJogo=null;
    }

    @Override
    public void run() {
        //timer draw
        long initialTime= (System.currentTimeMillis()/1000);
        //life draw
        int lifeOld=life;
        //fps counter
        double repaintInterval = (double) 1000000000 /FPS;
        double delta=0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer=0;
        int drawcount=0;
        while (rodarJogo!=null){
            currentTime = System.nanoTime();
            delta+=(currentTime-lastTime)/repaintInterval;
            timer+=(currentTime-lastTime);
            lastTime=currentTime;
            if(delta>=1){
                long timeNow= (System.currentTimeMillis())/1000;
                seconds=(int)((timeNow-initialTime)+main.timer);
                if(life!=lifeOld){
                    playES(2,0);
                    lifeOld=life;
                }
                update();
                repaint();
                delta--;
                drawcount++;
            }
            if(timer>=1000000000){
//                System.out.println("fps: "+drawcount+" "+seconds);
//                System.out.println(creatines);
//                System.out.println(life);
                drawcount=0;
                timer=0;
            }
        }
    }
    public void update(){
        jogador.update();
        for (Suco inimigo : inimigos) {
            inimigo.update();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //fundo
        controlefundo.draw(g2);

        //inimigo
        for (Suco inimigo : inimigos) {
            inimigo.draw(g2);
        }

        //jogador
        jogador.draw(g2);

        //ui
        ui.draw(g2);

        cenas.draw(g2);

        g2.dispose();

        if(creatines==0){
            newGame();
        }
        if(life==0) {
            playES(2,0);
            gameOver();
        }
    }
    public void playMusica(int i,int volume){
        musica.setFile(i,volume);
        musica.play();
        musica.loop();
    }
    public void stopMusica(){
        musica.stop();
    }
    public void playES(int i,int volume){
        SE.setFile(i,volume);
        SE.play();
    }
    public int getSeconds() {
        return seconds;
    }
    public int getCreatines() {
        return creatines;
    }
    public void setCreatines(int creatines) {
        this.creatines = creatines;
    }
    public int getLevel() {
        return level;
    }
    public int getColuns() {
        return coluns;
    }
    public int getLine() {
        return line;
    }
    public int getSizeOfTittle() {
        return sizeOfTittle;
    }
    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }
    public int getPlayerSpeed() {
        return playerSpeed;
    }
    public FundoControler getControlefundo() {
        return controlefundo;
    }
    public ColisionChecker getColisoes() {
        return colisoes;
    }

}
