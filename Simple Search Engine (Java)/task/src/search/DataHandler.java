package search;

import java.util.ArrayList;

public class DataHandler {
    ArrayList<String> people = new ArrayList<>();

    public void saveInputs(String person) {
        this.people.add(person);
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
