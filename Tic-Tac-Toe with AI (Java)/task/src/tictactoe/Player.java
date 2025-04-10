package tictactoe;

public class Player {
    private String name = "";
    private String playerType = "";

    public Player(String symbol, String type) {
        setName(symbol);
        setPlayerType(type);
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setPlayerType(String type) {
        this.playerType = type;
    }

    protected String getTurn() {
        return this.name;
    }
    protected String getPlayerType() { return this.playerType; }

    public int getCoord(String s) {
        return -1;
    }
}
