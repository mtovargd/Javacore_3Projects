package contacts;

import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    ArrayList<Contact> contacts = new ArrayList<Contact>();

    public void addContact(Contact contact) {
        this.contacts.add(contact);
        System.out.println("The record added");
    }

    public void removeContact(int index) {
        int id = this.validateInput(index);
        if (id > 0){
            this.contacts.remove(index - 1);
            System.out.println("The record removed!");
        }
    }

    public void countContacts() {
        System.out.println("The Phone Book has " + contacts.size() + " records.");
    }

    public void listContacts() {
        for (Contact contact : contacts) {
            System.out.println((contacts.indexOf(contact) + 1) + ". " + contact.getName() +
                    " " + contact.getSurname() + ", " + contact.getPhone());
        }
    }

    public void editContact(int index, Scanner scanner) {
        int id = this.validateInput(index);
        if (id > 0){
            System.out.print("Select a field (name, surname, number): ");
            String field = scanner.nextLine();
            switch (field){
                case "name":
                    this.contacts.get(id-1).setName();
                    break;
                case "surname":
                    this.contacts.get(id-1).setSurname();
                    break;
                case "number":
                    this.contacts.get(id-1).setPhone();
                    break;
                default:
                    System.out.println("Invalid field");
                    break;
            }
            System.out.println("The record updated!");
        }
    }

    private int validateInput(int id) {
        if (id > 0 && id <= this.contacts.size()) {
            return id;
        }
        else {
            System.out.println("The id must be between 1 and " + (this.contacts.size()));
            return -1;
        }
    }

    public boolean validateInput() {
        if (this.contacts.isEmpty()) {
            return false;
        }
        return true;
    }
}
