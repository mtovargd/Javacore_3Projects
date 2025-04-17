package contacts;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    Book book = new Book();

    public void displayMenu() {
        while (running) {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            String action = scanner.nextLine();
            switch (action) {
                case "add":
                    System.out.print("Enter the type (person, organization): ");
                    String type = scanner.nextLine();
                    if (type.equals("person")) {
                        Contact person = new Person();
                        this.book.addContact(person);
                    } else if (type.equals("organization")) {
                        Contact organization = new Organization();
                        this.book.addContact(organization);
                    }
                    break;
                case "remove":
                    if (this.book.validateInput()){
                        this.book.listContacts();
                        int id = validateNumber();
                        this.book.removeContact(id);
                    } else {
                        System.out.println("No records to remove!");
                    }
                    break;
                case "edit":
                    if (this.book.validateInput()) {
                        this.book.listContacts();
                        int id = validateNumber();
                        this.book.editContact(id, scanner);
                    } else {
                        System.out.println("No records to edit!");
                    }
                    break;
                case "count":
                    this.book.countContacts();
                    break;
                case "info":
                    if (this.book.validateInput()){
                        this.book.listContacts();
                        System.out.println("Enter index to show info: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        this.book.showInfo(id);
                    } else {
                        System.out.println("No records to show!");
                    }
                    break;
                case "exit":
                    System.out.println("\n");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid action! Try again");
                    break;
            }
        }
    }

    private int validateNumber() {
        boolean valid = false;
        int id = -1;
        while (!valid) {
            System.out.print("Select a record: ");
            String contactInput = scanner.next();
            scanner.nextLine();
            try {
                id = Integer.parseInt(contactInput);
            } catch (NumberFormatException e) {
                System.out.println("Enter a number!");
                continue;
            }
            valid = true;
        }
        return id;
    }

}
