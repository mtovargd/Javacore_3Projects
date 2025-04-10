package search;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int idx = checkWord();
        String result = (idx > 0) ? String.valueOf(idx) : "Not found";
        System.out.println(result);
    }

    public static int checkWord() {
        String str = scanner.nextLine();
        String word = scanner.next();
        for (int i = 0; i < str.split(" ").length; i++) {
            if (word.equals(str.split(" ")[i])) {
                return (i+1);
            }
        }
        return 0;
    }
}
