package tictactoe;

import java.util.Scanner;

public class Manager {
    Scanner scanner = new Scanner(System.in);
    protected boolean endGame = false; // Flag to check if the game ends at the current turn
    protected boolean turnO = false; // Determines whose turn it is
    private final Board board; // Board instance
    private final Player player = new Player("X"); // User plays as X
    private final Player comp = new Player("O"); // Computer plays as O

    public Manager() {
        this.board = new Board(scanner, this);
    }

    public void setInitialState() {
        this.board.setInitialBoard();
    }

    public String printCharTurn() {
        turnO = !turnO; // Changes the turn when it's called
        if (turnO) {
            return this.player.useTurn();
        }
        return this.comp.useTurn();
    }

}
