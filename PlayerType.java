package tictactoe;

public enum PlayerType {
    HUMAN ("Human"),
    ROBOT("Robot");

    String name;

    PlayerType (String name) {
        this.name = name;
    }

    PlayerType anotherPlayerType() {
        PlayerType type = null;
        if (this.equals(HUMAN))
            type = ROBOT;
        if (this.equals(ROBOT))
            type = HUMAN;
        return type;
    }

}
