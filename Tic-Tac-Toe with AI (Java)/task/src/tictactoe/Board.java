package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Board {
    // TODO: Just put a random number on the empty cells when its computer's turn
    private String currentBoard;
    private final Scanner scanner;
    private final Manager manager;

    public Board(Scanner scanner, Manager manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    public void setInitialBoard() {

        String initialBoard = "_________";

        printBoard(initialBoard);
        this.currentBoard = initialBoard;
        updateBoard();
    }

    public void printBoard(String newBoard) {
        /*
         * Format the board to print it visually
         * Use the loop to set the first turn, according to the number of each chars at the initial state
         */
        String board = "---------\n| ";
        for (int i = 0; i < newBoard.length(); i++) {
            board += newBoard.charAt(i) + " ";
            if ((i+1) % 3 == 0) { // Once the line ends (3 columns each) add a break line
                board += "|\n";
                if (i + 1 != newBoard.length()) { // Add a '|' just at the start of the next line (after the break)
                    board += "| ";
                }
            }
        }
        board += "---------"; // Board closed

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
            if (!manager.turnO){
                while (!isEmptyCell) {
                    System.out.print("Enter the coordinates: ");
                    String inputX = ""; // Read next string and tries to parse to int
                    String inputY = "";
                    try {
                        inputX = scanner.next();
                        x = Integer.parseInt(inputX);
                    } catch (NumberFormatException e) {
                        System.out.println("You should enter numbers!"); // Catches anything that is not a number
                        continue;
                    }
                    try {
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
                        cell = ((x - 1) * 3) + (y - 1);
                        if (currentBoard.charAt(cell) == '_') {
                            isEmptyCell = true;
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                }
            } else {
                System.out.println("Making move level \"easy\"");
                cell = compTurn();
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

    /* Returns a random index from the available cells of the board string */
    private int compTurn(){
        Random random = new Random();
        ArrayList <Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < currentBoard.length(); i++) {
            if (currentBoard.charAt(i) == '_') {
                emptyCells.add(i);
            }
        }
        int randomIndex = random.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }
}
