package tictactoe;

public enum PlayerType {
    HUMAN ("Human"),
    COMPUTER ("Computer");

    String name;

    PlayerType (String name) {
        this.name = name;
    }

}
