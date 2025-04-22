package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    ArrayList<Contact> contacts = new ArrayList<>();
    private String filePath;

    public Book(String filePath) {
        this.filePath = filePath;
        this.load();
    }

    public Book() {
        this.filePath = null;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
        save();
        System.out.println("The record added.\n");
    }

    public void removeContact(int index) {
        int id = this.validateInput(index);
        if (id > 0){
            this.contacts.remove(index - 1);
            save();
            System.out.println("The record removed!\n");
        }
    }

    public void countContacts() {
        System.out.println("The Phone Book has " +
                contacts.size() + " records.\n");
    }

    public void listContacts() {
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).getPreview());
        }
        System.out.println();
    }

    public void showInfo(int index) {
        int id = this.validateInput(index);
        if (id > 0){
            this.contacts.get(id-1).getInfo();
        }
        System.out.println();
    }

    public void editContact(int index, Scanner scanner) {
        int id = this.validateInput(index);
        if (id > 0) {
            Contact contact = this.contacts.get(id - 1);
            String[] fields = contact.getEditableFields();
            System.out.print("Select a field (" + String.join(", ", fields) + "): ");
            String field = scanner.nextLine();

            boolean valid = false;
            for (String f : fields) {
                if (f.equals(field)) {
                    valid = true;
                    break;
                }
            }

            if (!valid) {
                System.out.println("Invalid field!\n");
                return;
            }

            System.out.print("Enter " + field + ": ");
            String value = scanner.nextLine();
            contact.setField(field, value);
            save();
            System.out.println("Saved\n");
            this.showInfo(index);
        }
    }

    private int validateInput(int id) {
        if (id > 0 && id <= this.contacts.size()) {
            return id;
        }
        else {
            System.out.println("The id must be between 1 and " + (this.contacts.size()) + "\n");
            return -1;
        }
    }

    public void searchQuery(String query) {
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        ArrayList<Contact> results = new ArrayList<>();

        for (Contact contact : contacts) {
            StringBuilder record = new StringBuilder();
            for (String field : contact.getEditableFields()) {
                record.append(contact.getField(field)).append(" ");
            }

            Matcher matcher = pattern.matcher(record.toString().trim());
            if (matcher.find()) {
                results.add(contact);
            }
        }

        System.out.println("Found " + results.size() + " results:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getPreview());
        }

        System.out.println();
    }

    private void save() {
        if (filePath == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this.contacts);
            System.out.println("Saved " + contacts.size() + " contacts to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving contacts\n");
        }
    }

    private void load() {
        File file = new File(filePath);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            this.contacts = (ArrayList<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts:");
        }
    }

}
