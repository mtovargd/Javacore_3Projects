package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

        Scanner scanner = new Scanner(System.in);
        DataHandler dataHandler = new DataHandler();
        boolean running = true;
        String fileName = "";

        public void setFileName(String fileName) {
            this.fileName = fileName;
            dataHandler.readInput(fileName);
        }

    public void displayMenu() {

        while (running) {
            switch (checkNumInput()) {
                case 1:
                    scanner.nextLine();
                    String strategy = checkStrategyInput();
                    scanner.nextLine();
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scanner.nextLine();
                    dataHandler.findPeople(query, strategy);
                    break;
                case 2:
                    System.out.println("=== List of people ===");
                    scanner.nextLine();
                    dataHandler.findPeople();
                    break;
                case 0:
                    running = false;
                    break;
            }
        }
    }

    private int checkNumInput() {
        boolean valid = false;
        String input = "";
        int numberInput = -1;
        while (!valid) {
            System.out.println("=== Menu ===\n" +
                    "1. Find a person\n" +
                    "2. Print all people\n" +
                    "0. Exit");
            try{
                input = scanner.next();
                numberInput = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect option! Try again.");
                continue;
            }
            if (numberInput >= 0 && numberInput <= 2) {
                valid = true;
            } else {
                System.out.println("Incorrect option! Try again.");
            }
        }
        return numberInput;
    }

    private String checkStrategyInput() {

        boolean valid = false;
        String input = "";
        List<String> options = Arrays.asList("ALL", "ANY", "NONE");
        while (!valid) {
            System.out.println("Select a matching strategy: ALL, ANY, NONE");
            input = scanner.next();

            if (options.contains(input.toUpperCase())) {
                valid = true;
            } else {
                System.out.println("Incorrect option! Try again.");
            }
        }
        return input;
    }
}
