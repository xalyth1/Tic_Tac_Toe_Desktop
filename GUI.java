package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GUI {
    Game game;// = new Game();

    private static final GUI gui = new GUI();
    private JMenuItem hvhMenuItem;
    private JMenuItem hvrMenuItem;
    private JMenuItem rvhMenuItem;
    private JMenuItem rvrMenuItem;
    private JMenuItem exitMenuItem;

    private final String startStr = "Start";
    private final String resetStr = "Reset";

    public void setGame(Game game) {
        this.game = game;
    }

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

        hvhMenuItem = new JMenuItem("Human vs Human");
        hvrMenuItem = new JMenuItem("Human vs Robot");
        rvhMenuItem = new JMenuItem("Robot vs Human");
        rvrMenuItem = new JMenuItem("Robot vs Robot");
        exitMenuItem = new JMenuItem("Exit");



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
            game.player1 = new Player("X", PlayerType.HUMAN);
            game.player2 = new Player("O", PlayerType.HUMAN);
        });

        hvrMenuItem.addActionListener(al -> {
            game.player1 = new Player("X", PlayerType.HUMAN);
            game.player2 = new Player("O", PlayerType.ROBOT);
        });

        rvhMenuItem.addActionListener(al -> {
            game.player1 = new Player("X", PlayerType.ROBOT);
            game.player2 = new Player("O", PlayerType.HUMAN);
        });

        rvrMenuItem.addActionListener(al -> {
            game.player1 = new Player("X", PlayerType.ROBOT);
            game.player2 = new Player("O", PlayerType.ROBOT);
        });

        return menuBar;
    }

    public JButton[][] createBoardJButtonList() {
        JButton[][] buttonGrid = new JButton[3][3];
        for (int i = 3; i > 0; i--) {
            for (char j = 'A'; j < 'D'; j++) {
                JButton button = new JButton(" "/*""+ j + i*/);
                //JButton button = new JButton(""+ j + i);
                button.setName("Button" + j + i);
                button.setFocusPainted(false);
                Font font = button.getFont();
                button.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 16));
                buttonGrid[3 - i][j - 65] = button;
                button.setEnabled(false);

                System.out.println(button.getName());
            }
        }
        return buttonGrid;
    }

    public void createActionListeners() {
        game.ticTacToe.player1Button.addActionListener(e -> {
            game.player1.setPlayerType(game.player1.getPlayerType().anotherPlayerType());
            game.ticTacToe.player1Button.setText(game.player1.getPlayerType().name);
        });

        game.ticTacToe.player2Button.addActionListener(e -> {
            game.player2.setPlayerType(game.player2.getPlayerType().anotherPlayerType());
            game.ticTacToe.player2Button.setText(game.player2.getPlayerType().name);
        });

        game.ticTacToe.startResetButton.addActionListener(e -> {
            JButton button = game.ticTacToe.startResetButton;

            if (button.getText().equals(resetStr)) {
                game.reset();
                game.ticTacToe.clearAllBoardButtons();
                game.ticTacToe.player1Button.setEnabled(true);
                game.ticTacToe.player2Button.setEnabled(true);

                button.setText(startStr);
            } else if (button.getText().equals(startStr)) {
                game.ticTacToe.clearAllBoardButtons();

                System.out.println("Enable all buttons");
                game.ticTacToe.setAllButtonsEnabled(true);
                game.ticTacToe.player1Button.setEnabled(false);
                game.ticTacToe.player2Button.setEnabled(false);

                new Thread(game).start();



                button.setText(resetStr);
            }
        });

        JButton[][] buttonGrid = game.ticTacToe.buttonGrid;
        for (JButton[] tab : buttonGrid) {
            for (JButton button : tab) {
                button.addActionListener(al -> {


                    if (button.getText().equals(" ")) {
                        game.worker.moveMade = true;
                        button.setText(game.currentPlayer.getPlayerSymbol());
                    }
                }
                );
            }
        }

        createMenuListeners();
    }

    private void createMenuListeners() {
        hvhMenuItem.addActionListener(al -> {
            game.player1.setPlayerType(PlayerType.HUMAN);
            game.player2.setPlayerType(PlayerType.HUMAN);
            game.ticTacToe.player1Button.setText("Human");
            game.ticTacToe.player2Button.setText("Human");

            prepareBoard();

            new Thread(game).start();

            JButton button = game.ticTacToe.startResetButton;
            button.setText(resetStr);

        });

        hvrMenuItem.addActionListener(al -> {
            game.player1.setPlayerType(PlayerType.HUMAN);
            game.player2.setPlayerType(PlayerType.ROBOT);
            game.ticTacToe.player1Button.setText("Human");
            game.ticTacToe.player2Button.setText("Robot");

            prepareBoard();

            new Thread(game).start();

            JButton button = game.ticTacToe.startResetButton;
            button.setText(resetStr);
        });

        rvhMenuItem.addActionListener(al -> {
            game.player1.setPlayerType(PlayerType.ROBOT);
            game.player2.setPlayerType(PlayerType.HUMAN);
            game.ticTacToe.player1Button.setText("Robot");
            game.ticTacToe.player2Button.setText("Human");

            prepareBoard();

            new Thread(game).start();

            JButton button = game.ticTacToe.startResetButton;
            button.setText(resetStr);
        });

        rvrMenuItem.addActionListener(al -> {
            game.player1.setPlayerType(PlayerType.ROBOT);
            game.player2.setPlayerType(PlayerType.ROBOT);
            game.ticTacToe.player1Button.setText("Robot");
            game.ticTacToe.player2Button.setText("Robot");

            prepareBoard();

            new Thread(game).start();

            JButton button = game.ticTacToe.startResetButton;
            button.setText(resetStr);
        });

        exitMenuItem.addActionListener(al -> {
            game.ticTacToe.dispatchEvent(new WindowEvent(game.ticTacToe, WindowEvent.WINDOW_CLOSING));
        });


    }

    private void prepareBoard() {
        game.ticTacToe.clearAllBoardButtons();
        game.ticTacToe.setAllButtonsEnabled(true);
        game.ticTacToe.player1Button.setEnabled(false);
        game.ticTacToe.player2Button.setEnabled(false);
    }




}
