package contacts;

public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        Contact contact = new Contact(book);
        contact.saveContact();
    }
}
