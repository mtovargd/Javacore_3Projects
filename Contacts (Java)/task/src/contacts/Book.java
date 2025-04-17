package contacts;

import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    ArrayList<Contact> contacts = new ArrayList<Contact>();

    public void addContact(Contact contact) {
        this.contacts.add(contact);
        System.out.println("The record added.\n");
    }

    public void removeContact(int index) {
        int id = this.validateInput(index);
        if (id > 0){
            this.contacts.remove(index - 1);
            System.out.println("The record removed!\n");
        }
    }

    public void countContacts() {
        System.out.println("The Phone Book has " + contacts.size() + " records.\n");
    }

    public void listContacts() {
        for (Contact contact : contacts) {
            if (contact.isPerson()) {
                Person person = (Person) contact;
                System.out.println((contacts.indexOf(contact) + 1) + ". " + person.getName() +
                        " " + person.getSurname());
            } else {
                System.out.println((contacts.indexOf(contact) + 1) + ". " + contact.getName());
            }
        }
    }

    public void showInfo(int index) {
        int id = this.validateInput(index);
        if (id > 0){
            this.contacts.get(id-1).getInfo();
        }
    }

    public void editContact(int index, Scanner scanner) {
        int id = this.validateInput(index);
        if (id > 0){
            Contact contact = this.contacts.get(id-1);
            if (contact.isPerson()){
                Person person = (Person) contact;
                System.out.print("Select a field (name, surname, birth, gender, number): ");
                String field = scanner.nextLine();
                switch (field){
                    case "name":
                        person.setName();
                        break;
                    case "surname":
                        person.setSurname();
                        break;
                    case "birth":
                        person.setBirthday();
                        break;
                    case "gender":
                        person.setGender();
                        break;
                    case "number":
                        person.setPhone();
                        break;
                    default:
                        System.out.println("Invalid field");
                        break;
                }
            } else {
                Organization org = (Organization) contact;
                System.out.print("Select a field (name, surname, birth, gender, number): ");
                String field = scanner.nextLine();
                switch (field){
                    case "address":
                        org.setAddress();
                        break;
                    case "number":
                        org.setPhone();
                        break;
                    default:
                        System.out.println("Invalid field");
                        break;
                }
            }
            contact.setEditDate();
            System.out.println("The record updated!\n");
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
