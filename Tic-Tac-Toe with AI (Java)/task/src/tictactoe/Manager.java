package tictactoe;

import java.util.*;

public class Manager {
    Scanner scanner = new Scanner(System.in);
    protected boolean endGame = false; // Flag to check if the game ends at the current turn
    protected boolean turnX = true; // Determines whose turn it is
    private final Board board; // Board instance
    private final Player player1; // Player 1 plays as X
    private final Player player2; // Player 2 plays as O

    /* Default possible wins */
    private static final int[][] WIN_COMBINATIONS = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Horizontal
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Vertical
            {0, 4, 8}, {2, 4, 6}             // Diagonal
    };

    public Manager(String player1, String player2) {
        if (player1.equals("user")) {
            this.player1 = new Player("X", player1);
        } else{
            this.player1 = new Computer(this, "X", player1);
        }
        if (player2.equals("user")) {
            this.player2 = new Player("O", player2);
        } else{
            this.player2 = new Computer(this, "O", player2);
        }
        this.board = new Board(scanner, this);
    }

    public void setInitialState() {
        this.board.setInitialBoard();
    }

    protected Player getCurrentPlayer() {
        if (turnX) {
            return this.player1;
        } else {
            return this.player2;
        }
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

    /* Request a move to the computer player */
    protected int getCompTurn(String board){
        if (turnX){
            return this.player1.getCoord(board);
        } else {
            return this.player2.getCoord(board);
        }
    }

    protected int checkWinner(String board, int cell) {
        /**
         * This method is almost like the checkWinner at Manager class, but that method checks at the end of
         * every row and column.
         * This method needs to check the neighbors at every cell
         * 0 1 2    Vertical: [0 3 6], [1 4 7], [2 5 8]
         * 3 4 5    Horizontal: [0 1 2], [3 4 5], [6 7 8]
         * 6 7 8    Diagonal: [1 4 8], [2 4 6]
         */
        //System.out.println("cell: " + cell);
        int bestScore = 0;

        for (int[] line : WIN_COMBINATIONS) {
            if (line[0] == cell || line[1] == cell || line[2] == cell) {
                int selfChar = 0;
                int enemyChar = 0;
                int countEmpty = 0;

                for (int i : line) {
                    /* If there's a cell where the same char at the current turn, self add 1 */
                    if (String.valueOf(board.charAt(i)).equals(getCurrentPlayer().getTurn())) {
                        selfChar++;
                    }
                    else if (board.charAt(i) == '_') {
                        countEmpty++;
                    } else {
                        enemyChar++;
                    }
                }
                // If the current line has two of the same symbol and one empty, it's a threat or opportunity
                if (countEmpty == 1) {
                    if (enemyChar == 2 && bestScore != -2) {
                        bestScore = 2;
                    } else if (selfChar == 2 && bestScore != 3){
                        bestScore = -2;
                    }
                }
                else if ((selfChar == 3 || enemyChar == 3) && countEmpty == 0){
                    bestScore = 3;
                }
            }
        }
        //System.out.println("Best score: " + bestScore);
        return bestScore;
    }

    protected int minimax(String board, boolean isMaximizing, String aiSymbol) {
        /* If isMaximizing, current = aiSymbol. Else if ai = X then current = O and vice versa */
        String currentSymbol;

        if (isMaximizing) {
            currentSymbol = aiSymbol;
        } else {
            if (aiSymbol.equals("X")) {
                currentSymbol = "O";
            } else {
                currentSymbol = "X";
            }
        }

        // Check for terminal state
        if (checkRealWin(board, aiSymbol)) return 10;
        if (checkRealWin(board, aiSymbol.equals("X") ? "O" : "X")) return -10;
        if (!board.contains("_")) return 0;

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
        } else {
            bestScore = Integer.MAX_VALUE;
        }

        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == '_') {
                String newBoard = board.substring(0, i) + currentSymbol + board.substring(i + 1);
                int score = minimax(newBoard, !isMaximizing, aiSymbol);

                if (isMaximizing) {
                    bestScore = Math.max(score, bestScore);
                } else {
                    bestScore = Math.min(score, bestScore);
                }
            }
        }

        return bestScore;
    }
    private boolean checkRealWin(String board, String symbol) {
        for (int[] combo : WIN_COMBINATIONS) {
            if (board.charAt(combo[0]) == symbol.charAt(0) &&
                    board.charAt(combo[1]) == symbol.charAt(0) &&
                    board.charAt(combo[2]) == symbol.charAt(0)) {
                return true;
            }
        }
        return false;
    }

}