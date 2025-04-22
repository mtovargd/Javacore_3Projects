package contacts;

public class Main {
    public static void main(String[] args) {
        Book book;

        // Check if a file name is provided
        if (args.length > 0) {
            String fileName = args[0];
            System.out.println("open " + fileName);
            book = new Book(fileName); // load from file
        } else {
            book = new Book(); // in-memory version
        }

        Menu menu = new Menu(book);
        menu.mainMenu();
    }
}