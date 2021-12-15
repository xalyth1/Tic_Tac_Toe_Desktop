package tictactoe;

public class Game implements Runnable {
    TicTacToe ticTacToe;
    GameStatus gameStatus = GameStatus.GAME_IS_NOT_STARTED;

    Player player1;
    Player player2;
    Player currentPlayer;
    HumanPlayerWorker worker;

    public Game(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
        player1 = new Player("X", PlayerType.HUMAN);
        player2 = new Player("O", PlayerType.HUMAN);
        player1.setPlayerAdversary(player2);
        player2.setPlayerAdversary(player1);
        currentPlayer = player1;
    }


    public void run() {
        System.out.println("player1 Type: " + player1.getPlayerType());
        System.out.println("player2 Type: " + player2.getPlayerType());
        startGame();
    }

    public void startGame() {
        gameStatus = GameStatus.GAME_IN_PROGRESS;
        ticTacToe.statusBar.setText("The turn of " + currentPlayer.getPlayerType().name + " Player(" + currentPlayer.getPlayerSymbol() + ")");

        while (gameStatus == GameStatus.GAME_IN_PROGRESS) {
            makeMove();
            //currentPlayer = currentPlayer.getAdversary();
        }

        ticTacToe.setAllButtonsEnabled(false);

    }



    public void reset() {

    }

    public GameStatus makeMove() {
        if (currentPlayer.getPlayerType().equals(PlayerType.ROBOT)) {
            sleep(300);
            ticTacToe.findFreeButton().get().setText(currentPlayer.getPlayerSymbol());
        } else {
            worker = new HumanPlayerWorker();
            worker.start();
            try {
                worker.join();
            } catch (InterruptedException e) {
                System.out.println("!Player move thread error...");
                e.printStackTrace();
            }





        }

        currentPlayer = currentPlayer.getAdversary();

        String msg = null;

        if (ticTacToe.wins(player1.getPlayerSymbol())) {
            System.out.println("p1 wins");
            gameStatus = GameStatus.X_WINS;
            msg = "The " + player1.getPlayerType().name + " Player (" + player1.getPlayerSymbol() + ") wins";
        } else if (ticTacToe.wins(player2.getPlayerSymbol())) {
            System.out.println("p2 wins");
            gameStatus = GameStatus.O_WINS;
            msg = "The " + player2.getPlayerType().name + " Player (" + player2.getPlayerSymbol() + ") wins";
        } else if (!ticTacToe.hasFreeMove()) {
            System.out.println("draw");
            gameStatus = GameStatus.DRAW;
            msg = gameStatus.description;
        } else {
            gameStatus = GameStatus.GAME_IN_PROGRESS;
            msg = "The turn of " + currentPlayer.getPlayerType().name + " Player(" + currentPlayer.getPlayerSymbol() + ")";
        }


        ticTacToe.statusBar.setText(msg);



        return gameStatus;
    }

    public void sleep(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
