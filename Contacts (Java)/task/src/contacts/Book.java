package contacts;

import java.util.ArrayList;

public class Book {
    ArrayList<Contact> contacts = new ArrayList<Contact>();

    public void addContact(Contact contact) {
        this.contacts.add(contact);
        System.out.println("A record created!\nA Phone Book with a single record created!");
    }
}
