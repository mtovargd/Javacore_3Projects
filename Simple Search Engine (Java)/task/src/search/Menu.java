package search;

import java.util.Scanner;

public class Menu {

        Scanner scanner = new Scanner(System.in);
        DataHandler dataHandler = new DataHandler();
        boolean running = true;

    public void displayMenu() {
        System.out.println("Enter the number of people:");
        int n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter all the people:");
        for (int i = 0; i < n; i++) {
            dataHandler.saveInputs(scanner.nextLine());
        }

        while (running) {
            switch (checkNumInput()) {
                case 1:
                    System.out.println("Enter a name or email to search all suitable people.");
                    scanner.nextLine();
                    dataHandler.findPeople(scanner.nextLine());
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
        while (!valid) {
            System.out.println("=== Menu ===\n" +
                    "1. Find a person\n" +
                    "2. Print all people\n" +
                    "0. Exit");
            try{
                input = scanner.next();
                Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect option! Try again.");
                continue;
            }
            valid = true;
        }
        return Integer.parseInt(input);
    }
}
