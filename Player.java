package tictactoe;

public class Player{
    private PlayerType playerType;
    private String playerSymbol;

    private Player adversary;

    public Player(String playerSymbol, PlayerType playerType) {
        this.playerSymbol = playerSymbol;
        this.playerType = playerType;
    }

    public void run() {

    }

    public void setPlayerAdversary(Player p) {
        this.adversary = p;
    }

    public Player getAdversary() {
        return adversary;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }
}
