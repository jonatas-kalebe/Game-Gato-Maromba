package main;

import javax.swing.*;

public class TelaInicial extends JPanel {
    public boolean start=false;
    PainelJogo tela;
    JFrame window;
    ClickHandler mouse=new ClickHandler(this);

    public TelaInicial(PainelJogo tela,JFrame window) {
        this.tela = tela;
        this.window=window;
        JButton botao = new JButton();
        botao.addMouseListener(mouse);
        add(botao);
        setVisible(true);

    }

    public void play() {
        window.remove(this);
        window.add(tela);
        window.validate();
        tela.startGame();
    }
}
