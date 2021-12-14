package tictactoe;

public class Game {
    TicTacToe ticTacToe;
    GameStatus gameStatus = GameStatus.GAME_IS_NOT_STARTED;

    Player player1;
    Player player2;
    Player currentPlayer;

    public Game(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
        player1 = new Player("X", PlayerType.HUMAN);
        player2 = new Player("O", PlayerType.HUMAN);
        currentPlayer = player1;
        start();
    }

    public void start() {

    }


}
