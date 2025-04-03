package tictactoe;

import java.util.Arrays;

public class Manager {
    /* firstTurn starts in true because it only stays active at the first turn */
    protected boolean firstTurn = true;
    /* boolean to check if the game ends at that turn or not */
    protected boolean endGame = false;
    private boolean turnX = false;

    private Board board = new Board(); // Once there is an initial state, it will be updated by every turn
    private Player playerX = new Player();
    private Player playerO = new Player();


    public void setInitialState(String initialBoard) {
        this.board.setInitialBoard(initialBoard);
    }

    public String printCharTurn() {
        turnX = !turnX;
        if (turnX) {
            return this.playerX.useTurn();
        }
        return this.playerO.useTurn();
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
    public static boolean checkWinner(String currentBoard, int cell) {
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

        if (Arrays.stream(hCells).anyMatch(num -> num == cell)) {
            if (currentBoard.charAt(cell) == currentBoard.charAt(cell - 1) &&
                    currentBoard.charAt(cell) == currentBoard.charAt(cell - 2)){
                return true;
            }
        }
        if (Arrays.stream(vCells).anyMatch(num -> num == cell)) {
            if (currentBoard.charAt(cell) == currentBoard.charAt(cell - 3) &&
                    currentBoard.charAt(cell) == currentBoard.charAt(cell - 6)){
                return true;
            }
        }
        if (cell == dLRCells) {
            if (currentBoard.charAt(cell) == currentBoard.charAt(4) &&
                    currentBoard.charAt(cell) == currentBoard.charAt(2)){
                return true;
            }
        }
        if (cell == dRLCells) {
            if (currentBoard.charAt(cell) == currentBoard.charAt(4) &&
                    currentBoard.charAt(cell) == currentBoard.charAt(0)){
                return true;
            }
        }
        // TODO: Here will be the vertical and diagonal check
        return false;
    }
}
