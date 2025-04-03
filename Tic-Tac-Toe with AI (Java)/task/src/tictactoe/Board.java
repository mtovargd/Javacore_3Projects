package tictactoe;

public class Board {

    private String currentBoard;

    public void setInitialBoard(String initialBoard) {
        System.out.println("Welcome to Tic-Tac-Toe!");

        boolean validBoard = false; // Loops until user enter a valid board

        while (!validBoard) {
            System.out.print("Enter the cells: > ");
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

    public static void printBoard(String newBoard) {

        int counterX = 0;
        int counterO = 0;
        /*
         * Format the board to print it visually
         * Use the loop to set the first turn, according to the number of each chars at the initial state
         */
        String board = "---------\n| ";
        for (int i = 0; i < newBoard.length(); i++) {
            if (firstTurn) {            // Counts each char to set the first turn
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
        if (firstTurn) {
            if (counterX > counterO) { // Just if there's more X, O starts. Any other case, X starts
                turnX = true;
            }
            firstTurn = false;
        }
        board = board.replace("_", " ");

        System.out.println(board);
        if (endGame) {
            System.out.println(printCharTurn() + " wins");
        }
    }

    private void updateBoard() {
        int x = 0;
        int y = 0;
        boolean emptyCell = false;
        String newBoard = "";
        if (currentBoard.contains("_")){ // Verify that the board contains at least one empty cell
            int cell = 0;
            while (!emptyCell) {
                System.out.print("Enter the coordinates > ");
                // TODO (optional) read the 'X Y' coords format to just 2 chars
                String inputX = scanner.next(); // Read next string and tries to parse to int
                String inputY = scanner.next();
                try{
                    x = Integer.parseInt(inputX);
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
                    cell = (x * y) - 1;
                    if (currentBoard.charAt(cell) == '_') {
                        emptyCell = true;
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
                    newBoard += printCharTurn();
                } else{
                    newBoard += currentBoard.charAt(i);
                }
                if (checkWinner(newBoard, i)){
                    /* If there's a winner, the loop breaks, so the string of the new board will not be complete,
                     * so it is completed with the remained string that wasn't checked at that point
                     * */
                    endGame = true;
                    printCharTurn();
                    newBoard += currentBoard.substring(i + 1);
                    break;
                }
            }
            printBoard(newBoard);

            while (!endGame) { // Loops the update until the game is finished
                updateBoard(newBoard);
            }
        } else {
            endGame = true;
            System.out.println("Draw");
        }
    }
}
