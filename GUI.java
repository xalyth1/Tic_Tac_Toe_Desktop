package tictactoe;

import javax.swing.*;

public class GUI {
    Game game = new Game();
    private static final GUI gui = new GUI();

    private GUI() {

    }

    public static GUI getInstance(){
        return gui;
    }

    public JButton createPlayer1Button() {
        JButton player1Button;
        player1Button = new JButton("Human");
        player1Button.setName("ButtonPlayer1");
        return player1Button;
    }

    public JButton createStartResetButton() {
        JButton startResetButton = new JButton("Start");
        startResetButton.setName("ButtonStartReset");
        return startResetButton;
    }

    public JButton createPlayer2Button() {
        JButton player2Button;
        player2Button = new JButton("Human");
        player2Button.setName("ButtonPlayer2");
        return player2Button;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);

        JMenuItem hvhMenuItem = new JMenuItem("Human vs Human");
        JMenuItem hvrMenuItem = new JMenuItem("Human vs Robot");
        JMenuItem rvhMenuItem = new JMenuItem("Robot vs Human");
        JMenuItem rvrMenuItem = new JMenuItem("Robot vs Robot");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        gameMenu.add(hvhMenuItem);
        gameMenu.add(hvrMenuItem);
        gameMenu.add(rvhMenuItem);
        gameMenu.add(rvrMenuItem);

        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);

        gameMenu.setName("MenuGame");
        hvhMenuItem.setName("MenuHumanHuman");
        hvrMenuItem.setName("MenuHumanRobot");
        rvhMenuItem.setName("MenuRobotHuman");
        rvrMenuItem.setName("MenuRobotRobot");
        exitMenuItem.setName("MenuExit");


        hvhMenuItem.addActionListener(al -> {
            player1Type = PlayerType.HUMAN;
            player1Button.setText("Human");

            player2Type = PlayerType.HUMAN;
            player2Button.setText("Human");
        });

        hvrMenuItem.addActionListener(al -> {
            player1Type = PlayerType.HUMAN;
            player1Button.setText("Human");

            player2Type = PlayerType.COMPUTER;
            player2Button.setText("Robot");
        });

        rvhMenuItem.addActionListener(al -> {
            player1Type = PlayerType.COMPUTER;
            player1Button.setText("Robot");

            player2Type = PlayerType.HUMAN;
            player2Button.setText("Human");
        });

        rvrMenuItem.addActionListener(al -> {
            player1Type = PlayerType.COMPUTER;
            player1Button.setText("Robot");

            player2Type = PlayerType.COMPUTER;
            player2Button.setText("Robot");
        });


        return menuBar;
    }

    public void createActionListeners() {
        game.ticTacToe.player1Button.addActionListener(e -> {
            if (player1Type == PlayerType.HUMAN) {
                player1Type = PlayerType.COMPUTER;
                player1Button.setText("Robot");
            } else if (player1Type == PlayerType.COMPUTER) {
                player1Type = PlayerType.HUMAN;
                player1Button.setText("Human");
            }

        });

        game.ticTacToe.startResetButton.addActionListener(e -> {

            if (player1Type == PlayerType.COMPUTER) {
                updateGameState();
                makeRandXMove();
                currentPlayer = "O";

            }

            if (startResetButton.getText().equals("Reset")) {

                for (JButton[] jButtons : buttonGrid) {
                    for (int j = 0; j < buttonGrid[0].length; j++) {
                        jButtons[j].setText(" ");
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

        game.ticTacToe.player2Button.addActionListener(e -> {
            if (player2Type == PlayerType.HUMAN) {
                player2Type = PlayerType.COMPUTER;
                player2Button.setText("Robot");
            } else if (player2Type == PlayerType.COMPUTER) {
                player2Type = PlayerType.HUMAN;
                player2Button.setText("Human");
            }
        });


    }




}
