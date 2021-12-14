package tictactoe;

public class Player {
    private PlayerType playerType;
    private String playerSymbol;

    public Player(String playerSymbol, PlayerType playerType) {
        this.playerSymbol = playerSymbol;
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }
}
