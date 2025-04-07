package tictactoe;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Board {
    private String currentBoard;
    private final Scanner scanner;
    private final Manager manager;

    public Board(Scanner scanner, Manager manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    protected void setInitialBoard() {

        String initialBoard = "_________";

        printBoard(initialBoard);
        this.currentBoard = initialBoard;
        updateBoard();
    }

    private void printBoard(String newBoard) {
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
            if (manager.isUser()){
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
                cell = manager.getCompTurn();
            }
            /* Creates a new string with the updated cells */
            for (int i = 0; i < currentBoard.length(); i++) {
                if (i == cell){
                    newBoard += manager.printCharTurn();
                } else{
                    newBoard += currentBoard.charAt(i);
                }
                if (manager.checkWinner(newBoard, i)){
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


}
