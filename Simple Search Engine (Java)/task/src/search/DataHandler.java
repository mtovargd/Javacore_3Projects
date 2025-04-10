package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataHandler {
    private final List<String> people = new ArrayList<>();
    private final Map<String, Set<Integer>> invertedIndex = new HashMap<>();

    public void saveInputs(String person) {
        int index = people.size();  // current line number
        people.add(person);

        // Build inverted index
        String[] words = person.split("\\s+");
        for (String word : words) {
            String key = word.toLowerCase();
            if (!invertedIndex.containsKey(key)) {
                invertedIndex.put(key, new HashSet<>());
            }
            invertedIndex.get(key).add(index);
        }
    }

    public void readInput(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                this.saveInputs(line);  // saves to people and builds inverted index
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    // Search by keyword using inverted index
    public void findPeople(String searchString) {
        String key = searchString.toLowerCase();
        Set<Integer> matchedIndexes = invertedIndex.get(key);

        if (matchedIndexes != null && !matchedIndexes.isEmpty()) {
            for (int index : matchedIndexes) {
                System.out.println(people.get(index));
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

    // Print all records
    public void findPeople() {
        for (String person : people) {
            System.out.println(person);
        }
    }
}