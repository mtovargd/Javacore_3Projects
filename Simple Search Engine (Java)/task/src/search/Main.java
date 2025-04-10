package search;

public class Main {

    public static void main(String[] args) {

        String fileName = null;

        for (int i = 0; i < args.length; i++) {
            if ("--data".equals(args[i]) && i + 1 < args.length) {
                fileName = args[i + 1];
                break;
            }
        }
        Menu menu = new Menu();
        if (fileName != null) {
            menu.setFileName(fileName);
            menu.displayMenu();
        }
    }
}
