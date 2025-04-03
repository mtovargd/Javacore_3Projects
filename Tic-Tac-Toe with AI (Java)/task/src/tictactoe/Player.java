package tictactoe;

public class Player {
    private String name;

    public Player(String symbol) {
        this.name = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String useTurn() {
        return this.name;
    }
}
