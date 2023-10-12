package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    int timer=0;
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setResizable(false);
        window.setTitle("Gato Maromba Natty");
        window.setBackground(Color.BLACK);

        PainelJogo painelJogo = new PainelJogo();
//        TelaInicial start=new TelaInicial(painelJogo,window);

        painelJogo.playMusica(0,-15);
        painelJogo.startGame();
        window.add(painelJogo);
        //window.add(start);


        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //painelJogo.startGame();
    }

}
