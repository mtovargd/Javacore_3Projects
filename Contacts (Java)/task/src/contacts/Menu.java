package contacts;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    Book book = new Book();

    public void displayMenu() {
        while (running) {
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            String action = scanner.nextLine();
            switch (action) {
                case "add":
                    Contact contact = new Contact();
                    this.book.addContact(contact);
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
                case "list":
                    this.book.listContacts();
                    break;
                case "exit":
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
            String contactRemove = scanner.next();
            scanner.nextLine();
            try {
                id = Integer.parseInt(contactRemove);
            } catch (NumberFormatException e) {
                System.out.println("Enter a number!");
                continue;
            }
            valid = true;
        }
        return id;
    }

}
