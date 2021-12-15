package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class TicTacToe extends JFrame {
    Game game = new Game(this);
    JButton[][] buttonGrid = new JButton[3][3];
    JLabel statusBar;

    JButton player1Button;
    JButton player2Button;

    JButton startResetButton;

    public TicTacToe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setSize(450, 450);
        createAndInitializeGUI();
        setVisible(true);
    }

    private void createAndInitializeGUI() {
        GUI gui = GUI.getInstance();
        gui.setGame(game);
        player1Button = gui.createPlayer1Button();
        startResetButton = gui.createStartResetButton();
        player2Button = gui.createPlayer2Button();

        JMenuBar menuBar = gui.createMenuBar(); // with listeners
        setJMenuBar(menuBar);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 3));
        northPanel.add(player1Button);
        northPanel.add(startResetButton);
        northPanel.add(player2Button);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        buttonGrid = gui.createBoardJButtonList();
        for (JButton[] tab : buttonGrid) {
            for (JButton button : tab) {
                boardPanel.add(button);
            }
        }

        JPanel downPanel = new JPanel();
        downPanel.setLayout(new FlowLayout());
        statusBar = new JLabel(game.gameStatus.description);
        statusBar.setName("LabelStatus");
        downPanel.add(statusBar);

        setLayout(new BorderLayout());

        add(northPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(downPanel, BorderLayout.SOUTH);


        gui.createActionListeners();
        //updateGameState();
    }


    int counter = 1;

//    private void updateGameState() {
//        System.out.println("Update game state called" + counter++);
//        // if all buttons not clicked -> game not started
//        if (!isGameStarted()) {
//            gameStatus = GameStatus.GAME_IS_NOT_STARTED;
//            disableAllButtons();
//            player1Button.setEnabled(true);
//            player2Button.setEnabled(true);
//
//
//        } else if (wins("X")) {
//            gameStatus = GameStatus.X_WINS;
//            disableAllButtons();
//            //player1Button.setEnabled(true);
//            //player2Button.setEnabled(true);
//            //startResetButton.setText("Start");
//        } else if (wins("O")) {
//            gameStatus = GameStatus.O_WINS;
//            disableAllButtons();
//            //player1Button.setEnabled(true);
//            //player2Button.setEnabled(true);
//            //startResetButton.setText("Start");
//
//        } else if (!hasFreeMove()) {
//            //System.out.println(" nie ma ruchów");
//            gameStatus = GameStatus.DRAW;
//            disableAllButtons();
//            //player1Button.setEnabled(true);
//            // player2Button.setEnabled(true);
//            //|startResetButton.setText("Start");
//        } else {
//            gameStatus = GameStatus.GAME_IN_PROGRESS;
//            statusBar.setText(gameStatus.description);
//            //startResetButton.setText("S");
//            player1Button.setEnabled(false);
//            player2Button.setEnabled(false);
//
//
//            if (player1Type == PlayerType.COMPUTER && currentPlayer.equals("X")) {
//                int delay = 700;
//                Timer timer = new Timer(delay, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        findFreeButton().get().setText("X");
//
//                        currentPlayer = "O";
//                        updateGameState();
//                    }
//                });
//                timer.setRepeats(false);
//                timer.start();
//            }
//
//            if (player2Type == PlayerType.COMPUTER && currentPlayer.equals("O")) {
//                int delay = 700;
//                Timer timer = new Timer(delay, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        findFreeButton().get().setText("O");
//
//                        currentPlayer = "X";
//                        updateGameState();
//                    }
//                });
//                timer.setRepeats(false);
//                timer.start();
//            }
//            //updateGameState();
//
//
//        }
//
//        statusBar.setText(gameStatus.description);
//
//
//    }

    public Optional<JButton> findFreeButton() {
        return Arrays.stream(buttonGrid)
                .flatMap(button -> Arrays.stream(button))
                .filter(button -> button.getText().equals(" ")).findAny();
    }

    public boolean hasFreeMove() {

        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[0].length; j++) {
                if (buttonGrid[i][j].getText().equals(" "))
                    return true;
            }
        }
        return false;
    }

    public boolean wins(String player) {
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


    private void makeRandXMove() {
        Optional<JButton> freeButton = findFreeButton();
        freeButton.ifPresent(jButton -> jButton.setText("X"));

        //findFreeButton().orElseGet()
    }

    private boolean isGameStarted() {
        for (JButton[] jButtons : buttonGrid) {
            for (int j = 0; j < buttonGrid[0].length; j++) {
                if (!jButtons[j].getText().equals(" "))
                    return true;
            }
        }
        return false;
    }

    public void clearAllBoardButtons() {
        Arrays.stream(buttonGrid)
                .flatMap(Arrays::stream)
                .forEach(jButton -> jButton.setText(" "));
    }

    private void enableAllButtons() {
        setAllButtonsEnabled(true);
    }

    private void disableAllButtons() {
        setAllButtonsEnabled(false);
    }

    public void setAllButtonsEnabled(boolean value) {
        Arrays.stream(buttonGrid)
                .flatMap(Arrays::stream)
                .forEach(jButton -> jButton.setEnabled(value));

        player1Button.setEnabled(value);
        player2Button.setEnabled(value);
    }


}