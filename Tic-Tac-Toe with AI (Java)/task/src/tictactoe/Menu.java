package tictactoe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    // TODO: Add menu to select who will play and the char they will use
    private final List<String> validPlayers = Arrays.asList("user", "easy");

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean game = true;

        while (game) {
            System.out.print("Input command: ");
            String[] input = scanner.nextLine().trim().split("\\s+");

            if (input.length == 1 && input[0].equals("exit")) {
                game = false;

            } else if (input.length == 3 && input[0].equals("start")
                    && validPlayers.contains(input[1])
                    && validPlayers.contains(input[2])) {

                // Pass player types to Manager
                Manager manager = new Manager(input[1], input[2]);
                manager.setInitialState();
            } else {
                System.out.println("Bad parameters!");
            }
        }

    }
}
