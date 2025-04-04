package tictactoe;

import java.util.Scanner;

public class Manager {
    Scanner scanner = new Scanner(System.in);
    protected boolean firstTurn = true; // First turn starts as true, active only for the first move
    protected boolean endGame = false; // Flag to check if the game ends at the current turn
    private boolean turnX = false; // Determines whose turn it is
    private final Board board; // Board instance
    private final Player playerX = new Player("X");
    private final Player playerO = new Player("O");

    public Manager() {
        this.board = new Board(scanner, this);
    }

    public void setInitialState() {
        this.board.setInitialBoard();
    }

    public String printCharTurn() {
        turnX = !turnX;
        if (turnX) {
            return this.playerX.useTurn();
        }
        return this.playerO.useTurn();
    }

}
