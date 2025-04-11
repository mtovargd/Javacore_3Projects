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
    public void findPeople(String query, String strategy) {
        String[] words = query.toLowerCase().split("\\s+");

        Set<Integer> resultIndexes = new HashSet<>();

        switch (strategy.toUpperCase()) {
            case "ALL":
                resultIndexes = new HashSet<>(getIndexes(words[0]));
                for (int i = 1; i < words.length; i++) {
                    resultIndexes.retainAll(getIndexes(words[i]));
                }
                break;

            case "ANY":
                for (String word : words) {
                    resultIndexes.addAll(getIndexes(word));
                }
                break;

            case "NONE":
                Set<Integer> allIndexes = new HashSet<>();
                for (int i = 0; i < people.size(); i++) {
                    allIndexes.add(i);
                }
                for (String word : words) {
                    allIndexes.removeAll(getIndexes(word));
                }
                resultIndexes = allIndexes;
                break;

            default:
                System.out.println("Unknown strategy: " + strategy);
                return;
        }

        if (resultIndexes.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            for (int index : resultIndexes) {
                System.out.println(people.get(index));
            }
        }
    }

    // Print all records
    public void findPeople() {
        for (String person : people) {
            System.out.println(person);
        }
    }

    private Set<Integer> getIndexes(String word) {
        return invertedIndex.getOrDefault(word.toLowerCase(), new HashSet<>());
    }
}