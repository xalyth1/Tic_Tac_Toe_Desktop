package tictactoe;

import javax.swing.*;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Optional;

public class TicTacToe extends JFrame {

    JButton[][] buttonGrid = new JButton[3][3];
    GameStatus gameStatus = GameStatus.GAME_IS_NOT_STARTED;
    JLabel statusBar;
    String currentPlayer = "X";


    PlayerType player1Type = PlayerType.HUMAN;
    PlayerType player2Type = PlayerType.HUMAN;

    JButton player1Button;
    JButton player2Button;

    JButton startResetButton = new JButton("Start");


    public TicTacToe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setSize(450, 450);

        createAndInitializeGUI();

        setVisible(true);

    }
    private void createAndInitializeGUI() {
        JPanel northPanel = new JPanel();

        northPanel.setLayout(new GridLayout(1,3 ));

        player1Button = new JButton("Human");
        player1Button.setName("ButtonPlayer1");
        player1Button.addActionListener(e -> {

            if (player1Type == PlayerType.HUMAN) {
                player1Type = PlayerType.COMPUTER;
                player1Button.setText("Robot");
            } else if (player1Type == PlayerType.COMPUTER) {
                player1Type = PlayerType.HUMAN;
                player1Button.setText("Human");
            }

        });


        //startResetButton = new JButton("Start");
        startResetButton.setName("ButtonStartReset");

        startResetButton.addActionListener(e -> {

            if (player1Type == PlayerType.COMPUTER) {
                makeRandXMove();
                currentPlayer = "O";
                updateGameState();
            }


            if (startResetButton.getText().equals("Reset")) {

                for (int i = 0; i < buttonGrid.length; i++) {
                    for (int j = 0; j < buttonGrid[0].length; j++) {
                        buttonGrid[i][j].setText(" ");
                    }
                }
                currentPlayer = "X";

                player1Button.setEnabled(true);
                player2Button.setEnabled(true);

                startResetButton.setText("Start");

                //updateGameState();
                gameStatus = GameStatus.GAME_IS_NOT_STARTED;
                statusBar.setText(gameStatus.description);




                enableAllButtons();
            } else if (startResetButton.getText().equals("Start")) {

                startResetButton.setText("Reset");

                //updateGameState();
                gameStatus = GameStatus.GAME_IN_PROGRESS;
                statusBar.setText(gameStatus.description);




                enableAllButtons();
                player1Button.setEnabled(false);
                player2Button.setEnabled(false);
            }
            //updateGameState();


        });
        player2Button = new JButton("Human");
        player2Button.setName("ButtonPlayer2");
        player2Button.addActionListener(e -> {
            if (player2Type == PlayerType.HUMAN) {
                player2Type = PlayerType.COMPUTER;
                player2Button.setText("Robot");
            } else if (player2Type == PlayerType.COMPUTER) {
                player2Type = PlayerType.HUMAN;
                player2Button.setText("Human");
            }
        });


        northPanel.add(player1Button);
        northPanel.add(startResetButton);
        northPanel.add(player2Button);



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
                button.setEnabled(false);

                button.addActionListener(e -> {
                    String text = button.getText();
                    if (text.equals(" ") && (gameStatus == GameStatus.GAME_IN_PROGRESS || gameStatus == GameStatus.GAME_IS_NOT_STARTED)) {
                        button.setText(currentPlayer);
                        currentPlayer = currentPlayer == "X" ? "O" : "X";
                        //updateGameState();

                        if (gameStatus == GameStatus.GAME_IN_PROGRESS && player2Type == PlayerType.COMPUTER && currentPlayer.equals("O")) {



                        }

                    } else if (text.equals("X") || text.equals("O")) {

                    }
                    //currentPlayer = currentPlayer == "X" ? "O" : "X";

                    updateGameState();
                    try {
                        Thread.sleep(100);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });

                button.addActionListener( e -> {

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


        downPanel.add(statusBar);



        setLayout(new BorderLayout());

        add(northPanel, BorderLayout.NORTH);

        add(boardPanel, BorderLayout.CENTER);

        add(downPanel, BorderLayout.SOUTH);

        updateGameState();
    }


    int counter = 1;
    private void updateGameState() {
        System.out.println("Update game state called" + counter++);
        // if all buttons not clicked -> game not started
        if (!isGameStarted()) {
            gameStatus = GameStatus.GAME_IS_NOT_STARTED;
            disableAllButtons();
            player1Button.setEnabled(true);
            player2Button.setEnabled(true);



        }
        else if (wins("X")) {
            gameStatus = GameStatus.X_WINS;
            disableAllButtons();
            //player1Button.setEnabled(true);
            //player2Button.setEnabled(true);
            //startResetButton.setText("Start");
        }
        else if (wins("O")) {
            gameStatus = GameStatus.O_WINS;
            disableAllButtons();
            //player1Button.setEnabled(true);
            //player2Button.setEnabled(true);
            //startResetButton.setText("Start");

        } else if (!hasFreeMove()) {
            //System.out.println(" nie ma ruchów");
            gameStatus = GameStatus.DRAW;
            disableAllButtons();
            //player1Button.setEnabled(true);
           // player2Button.setEnabled(true);
            //|startResetButton.setText("Start");
        } else {
            gameStatus = GameStatus.GAME_IN_PROGRESS;
            statusBar.setText(gameStatus.description);
            //startResetButton.setText("S");
            player1Button.setEnabled(false);
            player2Button.setEnabled(false);





            if (player1Type == PlayerType.COMPUTER && currentPlayer.equals("X")) {
                int delay = 700;
                Timer timer = new Timer( delay, new ActionListener(){
                    @Override
                    public void actionPerformed( ActionEvent e ){
                        findFreeButton().get().setText("X");

                        currentPlayer = "O";
                        updateGameState();
                    }
                } );
                timer.setRepeats( false );
                timer.start();
            }

            if (player2Type == PlayerType.COMPUTER && currentPlayer.equals("O")) {
                int delay = 700;
                Timer timer = new Timer( delay, new ActionListener(){
                    @Override
                    public void actionPerformed( ActionEvent e ){
                        findFreeButton().get().setText("O");

                        currentPlayer = "X";
                        updateGameState();
                    }
                } );
                timer.setRepeats( false );
                timer.start();
            }
            //updateGameState();




        }

        statusBar.setText(gameStatus.description);



    }

    private Optional<JButton> findFreeButton() {
        return Arrays.stream(buttonGrid)
                .flatMap(button -> Arrays.stream(button))
                .filter(button -> button.getText().equals(" ")).findAny();
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


    private void makeRandXMove() {
        findFreeButton().get().setText("X");
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

    private void enableAllButtons() {
        setAllButtonsEnabled(true);
    }
    private void disableAllButtons() {
        setAllButtonsEnabled(false);
    }
    private void setAllButtonsEnabled(boolean value) {
        Arrays.stream(buttonGrid)
                .flatMap(button -> Arrays.stream(button))
                .forEach(jButton -> jButton.setEnabled(value));

        player1Button.setEnabled(value);
        player2Button.setEnabled(value);
    }



}