package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler {
    ArrayList<String> people = new ArrayList<>();

    public void saveInputs(String person) {
        this.people.add(person);
    }

    public void readInput(String fileName) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                this.saveInputs(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    public void findPeople(String searchString) {
        for (String i : people){
            if (i.toLowerCase().contains(searchString.toLowerCase())) {
                System.out.println(people.get(people.indexOf(i)));
            }
        }
    }
    public void findPeople() {
        for (String i : people){
            System.out.println(people.get(people.indexOf(i)));
        }
    }
}
