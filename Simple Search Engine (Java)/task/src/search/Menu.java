package search;

import java.util.Scanner;

public class Menu {
        Scanner scanner = new Scanner(System.in);
        DataHandler dataHandler = new DataHandler();
    public void displayMenu() {
        System.out.println("Enter the number of people:");
        int n = checkNumInput();
        scanner.nextLine();

        System.out.println("Enter all the people:");
        for (int i = 0; i < n; i++) {
            dataHandler.saveInputs(scanner.nextLine());
        }

        System.out.println("Enter the number of search queries");
        n = checkNumInput();
        scanner.nextLine();

        System.out.println("Enter the data to search people");
        for (int i = 0; i < n; i++) {
            dataHandler.findPeople(scanner.nextLine());
        }
    }

    private int checkNumInput() {
        boolean valid = false;
        String input = "";
        while (!valid) {
            try{
                input = scanner.next();
                Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }
            valid = true;
        }
        return Integer.parseInt(input);
    }
}
