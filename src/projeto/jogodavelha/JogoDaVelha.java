package projeto.jogodavelha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaVelha extends JFrame implements ActionListener {
    JButton[] botoes = new JButton[9];
    boolean jogadorX = true;

    public JogoDaVelha() {
        setTitle("Jogo da Velha");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            botoes[i] = new JButton();
            botoes[i].setFont(new Font("Arial", Font.BOLD, 40));
            botoes[i].addActionListener(this);
            add(botoes[i]);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton botao = (JButton) e.getSource();
        if (!botao.getText().equals("")) return;

        botao.setText(jogadorX ? "X" : "O");
        jogadorX = !jogadorX;
        verificarVencedor();
    }

    void verificarVencedor() {
        int[][] v = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };

        for (int[] p : v) {
            String a = botoes[p[0]].getText(), b = botoes[p[1]].getText(), c = botoes[p[2]].getText();
            if (a.equals(b) && a.equals(c) && !a.equals("")) {
                JOptionPane.showMessageDialog(this, "Jogador " + a + " venceu!");
                resetarJogo();
                return;
            }
        }

        for (JButton b : botoes) if (b.getText().equals("")) return;
        JOptionPane.showMessageDialog(this, "Empate!");
        resetarJogo();
    }

    void resetarJogo() {
        for (JButton b : botoes) b.setText("");
        jogadorX = true;
    }

    public static void main(String[] args) {
        new JogoDaVelha();
    }
}