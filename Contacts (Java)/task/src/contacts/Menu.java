package contacts;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    Book book;

    public Menu(Book book) {
        this.book = book;
    }

    public void mainMenu() {
        while (running) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
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
                case "list":
                    this.book.listContacts();
                    listMenu();
                    break;
                case "search":
                    this.searchMenu();
                    break;
                case "count":
                    this.book.countContacts();
                    break;
                case "exit":
                    System.out.println();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid action! Try again\n");
                    break;
            }
        }
    }

    private void searchMenu() {
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter search query: ");
            String query = scanner.nextLine();
            this.book.searchQuery(query);
            int idx = -1;
            System.out.print("[search] Enter action ([number], back, again): ");
            String option = scanner.nextLine();
            try{
                idx = Integer.parseInt(option);
            } catch (NumberFormatException e) {
                if (option.equals("back")) {
                    valid = true;
                    mainMenu();
                } else if (option.equals("again")) {
                    continue;
                }
            }
            if (idx > 0) {
                valid = true;
                this.book.showInfo(idx);
                recordMenu(idx);
            } else {
                System.out.println("Invalid index\n");

            }

        }
    }

    private void recordMenu(int idx) {
        boolean exit = false;
        while (!exit) {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String action = scanner.nextLine();
            switch (action) {
                case "edit":
                    this.book.editContact(idx, scanner);
                    break;
                case "delete":
                    this.book.removeContact(idx);
                    mainMenu();
                    break;
                case "menu":
                    exit = true;
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid action! Try again\n");
            }
        }
    }

    private void listMenu() {
        int idx = -1;
        System.out.print("[list] Enter action ([number], back): ");
        String option = scanner.nextLine();
        try{
            idx = Integer.parseInt(option);
        } catch (NumberFormatException e) {
            if (option.equals("back")) {
                mainMenu();
            }
        }
        if (idx > 0) {
            this.book.showInfo(idx);
            recordMenu(idx);
        }
    }

}
