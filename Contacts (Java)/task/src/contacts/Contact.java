package contacts;

import java.util.Scanner;

public class Contact {

    Scanner scanner = new Scanner(System.in);
    String name;
    String surname;
    String phone;

    public Contact() {
        this.setName();
        this.setSurname();
        this.setPhone();
    }

    protected void setName() {
        System.out.print("Enter the name: ");
        this.name = scanner.next();
        scanner.nextLine();
    }
    protected void setSurname() {
        System.out.print("Enter the surname: ");
        this.surname = scanner.next();
        scanner.nextLine();
    }
    protected void setPhone() {
        System.out.print("Enter the number: ");
        String phoneInput = scanner.nextLine();
        if (isValidPhone(phoneInput)) {
            this.phone = phoneInput;
            //this.phone = phoneInput.replaceAll("\\s", "");
        } else {
            System.out.println("Wrong number format!");
            this.phone = "[no number]";
        }
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

    private boolean isValidPhone(String number) {
        /**
         * ^\\+? — Starts optionally with a plus '+'

         * ((\\([A-Za-z0-9]{2,}\\))|[A-Za-z0-9]{1,}) — First group: can be in parentheses
            (min 2 characters) or not (can be 1+ chars)

         * ([\\s-](\\([A-Za-z0-9]{2,}\\)|[A-Za-z0-9]{2,}))* — Followed by zero or more groups,
           separated by space or dash, each at least 2 characters, can be wrapped in parentheses or not

         * number.replaceAll("[^()]", "").length() — counts parentheses to make sure only one group
           (2 parentheses) max is wrapped
         */

        // First group (with optional + and optional parentheses)
        String regex = "^\\+?((\\([A-Za-z0-9]{2,}\\))|[A-Za-z0-9]{1,})" +
                "([\\s-](\\([A-Za-z0-9]{2,}\\)|[A-Za-z0-9]{2,}))*$";
        // Following groups (at least 2 chars, optionally in parentheses)

        // Check only one group is wrapped in parentheses
        int parenthesesCount = number.replaceAll("[^()]", "").length();
        if (parenthesesCount != 0 && parenthesesCount != 2) return false;

        return number.matches(regex);
    }
}
