package tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {
    public TicTacToe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setSize(450, 450);

        createAndInitializeGUI();

        setVisible(true);

    }
    private void createAndInitializeGUI() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3,3 ));

        for (int i = 3; i > 0; i--) {
            for (char j = 'A'; j < 'D'; j++) {
                JButton button = new JButton(""+ j + i);
                button.setName("Button" + j + i);
                System.out.println(button.getName());
                boardPanel.add(button);
            }
        }
        add(boardPanel);
    }


}