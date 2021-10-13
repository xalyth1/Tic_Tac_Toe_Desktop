package tictactoe;

public enum GameStatus {
    GAME_IS_NOT_STARTED ("Game is not started"),
    GAME_IN_PROGRESS ("Game in progress"),
    X_WINS ("X wins"),
    O_WINS ("O wins"),
    DRAW ("Draw");

    String description;
    GameStatus(String description) {
        this.description = description;
    }

}
