package tictactoe;

import java.util.Scanner;
import java.util.Arrays;

public class Main {

    /* Starts at false just to don't leave it without initialization. Eventually assigns initial state */
    //public static boolean turnX = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String initialBoard = scanner.nextLine();
        Manager manager = new Manager();
        manager.setInitialState(initialBoard);



    }



}
