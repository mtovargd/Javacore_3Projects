package tictactoe;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Manager {
    Scanner scanner = new Scanner(System.in);
    protected boolean endGame = false; // Flag to check if the game ends at the current turn
    protected boolean turnX = true; // Determines whose turn it is
    private final Board board; // Board instance
    private final Player player1; // Player 1 plays as X
    private final Player player2; // Player 2 plays as O

    public Manager(String player1, String player2) {
        this.player1 = new Player("X", player1);
        this.player2 = new Player("O", player2);
        this.board = new Board(scanner, this);
    }

    public void setInitialState() {
        this.board.setInitialBoard();
    }

    protected String printCharTurn() {
        if (turnX) {
            turnX = false; // Changes the turn when it's called
            return this.player1.getTurn();
        } else {
            turnX = true;
            return this.player2.getTurn();
        }
    }

    protected boolean isUser() {
        if (turnX){
            return Objects.equals(this.player1.getPlayerType(), "user");
        } else {
            return Objects.equals(this.player2.getPlayerType(), "user");
        }
    }

    /**
     The logic should check from end to start, in order to use just one iteration when the board is updating
     instead of update and loop again through the string array
     0 1 2   1- must check 2 backwards (horizontal)
     3 4 5   2- must check 5 backwards (horizontal)
     6 7 8   3- must check 6 (diagonal from 6 to 2) it shouldn't check 2 to 6 because 6 might change after the check
     4- also check 6 vertical (6-0) same logic as diagonal
     5- must check 7 vertical (7 to 1)
     6- last check 8 backwards (horizontal),
     7- also check 8 vertical (8 to 2, same case at the diagonal 2-6)
     8- and diagonal 8 to 0
     */
    protected static boolean checkWinner(String board, int cell) {
        /**
         * There are 3 different checks (horizontal, vertical, diagonal)
         * Horizontal: check i-1 and i-2
         * Vertical: check i-3 and i-6
         * Diagonal (6-2): check i-2 and i-4
         * Diagonal (8-0): check i-4 and i-8
         */
        int[] hCells = new int[]{2, 5, 8}; // The cells that need to be checked horizontally
        int[] vCells = new int[]{6, 7, 8}; // The cells that need to be checked vertically
        int dLRCells = 6; // The cells that need to be checked diagonal left to right (6-2)
        int dRLCells = 8; // The cells that need to be checked diagonal right to left (8-0)

        if (board.charAt(cell) != '_'){
            if (Arrays.stream(hCells).anyMatch(num -> num == cell)) {
                if (board.charAt(cell) == board.charAt(cell - 1) &&
                        board.charAt(cell) == board.charAt(cell - 2)) {
                    return true;
                }
            }
            if (Arrays.stream(vCells).anyMatch(num -> num == cell)) {
                if (board.charAt(cell) == board.charAt(cell - 3) &&
                        board.charAt(cell) == board.charAt(cell - 6)) {
                    return true;
                }
            }
            if (cell == dLRCells) {
                if (board.charAt(cell) == board.charAt(4) &&
                        board.charAt(cell) == board.charAt(2)) {
                    return true;
                }
            }
            if (cell == dRLCells) {
                if (board.charAt(cell) == board.charAt(4) &&
                        board.charAt(cell) == board.charAt(0)) {
                    return true;
                }
            }
        }
        return false;
    }

}
