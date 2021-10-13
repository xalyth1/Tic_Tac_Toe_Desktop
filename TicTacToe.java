package tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {

    JButton[][] buttonGrid = new JButton[3][3];
    GameStatus gameStatus = GameStatus.GAME_IS_NOT_STARTED;
    JLabel statusBar;
    String currentPlayer = "X";

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
                JButton button = new JButton(" "/*""+ j + i*/);
                button.setName("Button" + j + i);
                button.setFocusPainted(false);
                Font font = button.getFont();
                button.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 16));
                buttonGrid[i - 1][j - 65] = button;

                button.addActionListener(e -> {
                    String text = button.getText();
                    if (text.equals(" ") && (gameStatus == GameStatus.GAME_IN_PROGRESS || gameStatus == GameStatus.GAME_IS_NOT_STARTED)) {
                        button.setText(currentPlayer);
                        currentPlayer = currentPlayer == "X" ? "O" : "X";

                    } else if (text.equals("X") || text.equals("O")) {

                    }
                    //currentPlayer = currentPlayer == "X" ? "O" : "X";

                    updateGameState();
                    try {
                        Thread.sleep(500);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });



                System.out.println(button.getName());
                boardPanel.add(button);
            }

            //updateGameState();
        }


        JPanel downPanel = new JPanel();
        downPanel.setLayout(new FlowLayout());
        statusBar = new JLabel("Game is not started");
        statusBar.setName("LabelStatus");
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");


        resetButton.addActionListener(e -> {
            for (int i = 0; i < buttonGrid.length; i++) {
                for (int j = 0; j < buttonGrid[0].length; j++) {
                    buttonGrid[i][j].setText(" ");
                }
            }
            currentPlayer = "X";

            updateGameState();
        });

        downPanel.add(statusBar);
        downPanel.add(resetButton);



        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);

        add(downPanel, BorderLayout.SOUTH);

        updateGameState();
    }

    private void updateGameState() {
        // if all buttons not clicked -> game not started
        if (!isGameStarted()) {
            gameStatus = GameStatus.GAME_IS_NOT_STARTED;
        }
        else if (wins("X")) {
            gameStatus = GameStatus.X_WINS;
        }
        else if (wins("O")) {
            gameStatus = GameStatus.O_WINS;
        } else if (!hasFreeMove()) {
            //System.out.println(" nie ma ruchów");
            gameStatus = GameStatus.DRAW;
        } else {
            gameStatus = GameStatus.GAME_IN_PROGRESS;
        }

        statusBar.setText(gameStatus.description);



    }

    private boolean hasFreeMove() {

        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[0].length; j++) {
                if (buttonGrid[i][j].getText().equals(" "))
                    return true;
            }
        }
        return false;
    }




    private boolean wins(String player) {
        String s = player; // "X" or "O"
        // horizontal
        for (int i = 0; i < buttonGrid.length; i++) {
            if (buttonGrid[i][0].getText().equals(s) &&
                buttonGrid[i][1].getText().equals(s) &&
                buttonGrid[i][2].getText().equals(s))
                return true;
        }
        // vertical
        for (int j = 0; j < buttonGrid[0].length; j++) {
            if (buttonGrid[0][j].getText().equals(s) &&
                buttonGrid[1][j].getText().equals(s) &&
                buttonGrid[2][j].getText().equals(s))
                return true;
        }

        // diagonal
        if (buttonGrid[0][0].getText().equals(s) &&
                buttonGrid[1][1].getText().equals(s) &&
                buttonGrid[2][2].getText().equals(s)) {
            return true;
        }
        if (buttonGrid[0][2].getText().equals(s) &&
                buttonGrid[1][1].getText().equals(s) &&
                buttonGrid[2][0].getText().equals(s)) {
            return true;
        }

        //return
        return false;
    }


    private boolean isGameStarted() {
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[0].length; j++) {
                if (!buttonGrid[i][j].getText().equals(" "))
                    return true;
            }
        }
        return false;
    }


}