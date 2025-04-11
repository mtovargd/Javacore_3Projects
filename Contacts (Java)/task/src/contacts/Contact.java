package contacts;

import java.util.Scanner;

public class Contact {

    Scanner scanner = new Scanner(System.in);
    String name;
    String surname;
    String phone;
    Book book;

    public Contact(Book book) {
        this.setName();
        this.setSurname();
        this.setPhone();
        this.book = book;
    }

    private void setName() {
        System.out.println("Enter the name of the person:");
        this.name = scanner.next();
        scanner.nextLine();
    }
    private void setSurname() {
        System.out.println("Enter the surname of the person:");
        this.surname = scanner.next();
        scanner.nextLine();
    }
    private void setPhone() {
        System.out.println("Enter the number:");
        this.phone = scanner.nextLine();
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPhone() {
        return phone;
    }

    public void saveContact() {
        this.book.addContact(this);
    }
}
