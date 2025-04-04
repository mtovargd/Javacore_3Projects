package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Board {

    private String currentBoard;
    private final Scanner scanner;
    private final Manager manager;

    public Board(Scanner scanner, Manager manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    public void setInitialBoard() {
        System.out.println("Welcome to Tic-Tac-Toe!");

        String initialBoard = "";
        boolean validBoard = false; // Loops until user enter a valid board

        while (!validBoard) {
            System.out.print("Enter the cells: ");
            initialBoard = scanner.nextLine();
            /*
              Matches("[XO_]*"): This regex pattern checks if the string contains only the characters X, O, or _.
              The '*' allows for zero or more occurrences of those characters.
              It will loop until there is a valid string and have the specified characters
             */
            if (initialBoard.matches("[XO_]+") && initialBoard.length() == 9) {
                validBoard = true;
            } else {
                System.out.println("'" + initialBoard + "' is an invalid input! " +
                        "The string must only contain X, O, or _. Also must be 9 characters length. Try again.");
            }
        }
        printBoard(initialBoard);
        this.currentBoard = initialBoard;
        updateBoard();
    }

    public void printBoard(String newBoard) {

        int counterX = 0;
        int counterO = 0;
        /*
         * Format the board to print it visually
         * Use the loop to set the first turn, according to the number of each chars at the initial state
         */
        String board = "---------\n| ";
        for (int i = 0; i < newBoard.length(); i++) {
            if (manager.firstTurn) {            // Counts each char to set the first turn
                if (newBoard.charAt(i) == 'X') {
                    counterX++;
                } else if (newBoard.charAt(i) == 'O') {
                    counterO++;
                }
            }
            board += newBoard.charAt(i) + " ";
            if ((i+1) % 3 == 0) { // Once the line ends (3 columns each) add a break line
                board += "|\n";
                if (i + 1 != newBoard.length()) { // Add a '|' just at the start of the next line (after the break)
                    board += "| ";
                }
            }
        }
        board += "---------"; // Board closed
        if (this.manager.firstTurn) {
            if (counterX > counterO) { // Just if there's more X, O starts. Any other case, X starts
                manager.printCharTurn();
            }
            manager.firstTurn = false;
        }
        board = board.replace("_", " ");

        System.out.println(board);
        if (manager.endGame) {
            System.out.println(manager.printCharTurn() + " wins");
        }
    }

    private void updateBoard() {
        int x = 0;
        int y = 0;
        boolean isEmptyCell = false;
        String newBoard = "";
        if (currentBoard.contains("_")){ // Verify that the board contains at least one empty cell
            int cell = 0;
            while (!isEmptyCell) {
                System.out.print("Enter the coordinates ");
                // TODO (optional) read the 'X Y' coords format to just 2 chars
                String inputX = ""; // Read next string and tries to parse to int
                String inputY = "";
                try{
                    inputX = scanner.next();
                    x = Integer.parseInt(inputX);
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!"); // Catches anything that is not a number
                    continue;
                }
                try{
                    inputY = scanner.next();
                    y = Integer.parseInt(inputY);
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!"); // Catches anything that is not a number
                    continue;
                }
                if (x >= 1 && x <= 3 && y >= 1 && y <= 3) { // Board boundaries
                    /**
                     * Coords 1,1 means 1*1 =1. 1-1=0, so it's index 0 at the array
                     * Coords 3,3 means 3*3=9, 9-1=8, so it's index 8
                     * Then verifies if the selected coords contains an empty cell
                     */
                    cell = ((x -1) * 3) + (y - 1);
                    if (currentBoard.charAt(cell) == '_') {
                        isEmptyCell = true;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
            /* Creates a new string with the updated cells */
            for (int i = 0; i < currentBoard.length(); i++) {
                if (i == cell){
                    newBoard += manager.printCharTurn();
                } else{
                    newBoard += currentBoard.charAt(i);
                }
                if (checkWinner(newBoard, i)){
                    /* If there's a winner, the loop breaks, so the string of the new board will not be complete,
                     * so it is completed with the remained string that wasn't checked at that point
                     * */
                    manager.endGame = true;
                    manager.printCharTurn();
                    newBoard += currentBoard.substring(i + 1);
                    break;
                }
            }
            printBoard(newBoard);
            if (newBoard.contains("_")){
                System.out.println("Game not finished");
            }
            currentBoard = newBoard;

            while (!manager.endGame) { // Loops the update until the game is finished
                updateBoard();
            }
        } else {
            System.out.println("Draw");
            manager.endGame = true;
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
    public static boolean checkWinner(String board, int cell) {
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
