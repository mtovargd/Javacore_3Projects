package contacts;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Scanner;

public abstract class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    transient Scanner scanner = new Scanner(System.in);
    String name;
    String phone;
    String creationDate;
    String lastEditDate;

    public Contact() {
        this.creationDate = LocalDateTime.now().toString();
    }

    protected void setPhone() {
        System.out.print("Enter the number: ");
        String phoneInput = scanner.nextLine();
        if (isValidPhone(phoneInput)) {
            this.phone = phoneInput;
        } else {
            System.out.println("Wrong number format!");
            this.phone = "[no number]";
        }
    }

    protected void setPhone(String phoneInput) {
        if (isValidPhone(phoneInput)) {
            this.phone = phoneInput;
        } else {
            System.out.println("Wrong number format!");
            this.phone = "[no number]";
        }
    }

    protected void setEditDate() {
        this.lastEditDate = LocalDateTime.now().toString();
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getLastEditDate() {
        if (lastEditDate == null) {
            return this.creationDate;
        }
        return this.lastEditDate;
    }

    protected boolean isValidPhone(String number) {
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
        System.out.println(regex);
        return number.matches(regex);
    }

    protected String checkEmpty(String input) {
        if (!input.isEmpty()) {
            return input;
        }
        return "[no data]";
    }

    public abstract String getPreview();
    public abstract String[] getEditableFields();
    public abstract void setField(String field, String value);
    public abstract String getField(String field);
    public abstract void getInfo();
}

