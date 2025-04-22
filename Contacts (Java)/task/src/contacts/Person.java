package contacts;

import java.io.Serializable;
public class Person extends Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    String surname;
    String birthday;
    String gender;

    public Person() {
        super();
        this.setName();
        this.setSurname();
        this.setBirthday();
        this.setGender();
        this.setPhone();
    }

    protected void setName() {
        System.out.print("Enter the name: ");
        this.name = this.checkEmpty(scanner.next());
        scanner.nextLine();
    }

    protected void setSurname() {
        System.out.print("Enter the surname: ");
        this.surname = this.checkEmpty(scanner.next());
        scanner.nextLine();
    }

    protected void setBirthday() {
        System.out.print("Enter the birth date: ");
        this.birthday = this.checkEmpty(scanner.nextLine());
    }

    protected void setGender() {
        System.out.print("Enter the gender (M, F): ");
        String inputGender = scanner.nextLine();
        if (inputGender.equalsIgnoreCase("M") || inputGender.equals("F")) {
            this.gender = inputGender;
        } else {
            this.gender = "[no data]";
        }
    }

    public String[] getEditableFields() {
        return new String[]{"name", "surname", "birth", "gender", "number"};
    }

    public void setField(String field, String value) {
        switch (field) {
            case "name": this.name = checkEmpty(value); break;
            case "surname": this.surname = checkEmpty(value); break;
            case "birth": this.birthday = checkEmpty(value); break;
            case "gender":
                if (value.equalsIgnoreCase("M") || value.equalsIgnoreCase("F")) {
                    this.gender = value;
                } else {
                    this.gender = "[no data]";
                }
                break;
            case "number":
                this.setPhone(value);
                break;
        }
        setEditDate();
    }

    public String getField(String field) {
        switch (field) {
            case "name": return this.name;
            case "surname": return this.surname;
            case "birth": return this.birthday;
            case "gender": return this.gender;
            case "number": return this.phone;
            default: return "";
        }
    }

    public void getInfo() {
        System.out.println("Name: " + this.name +
                "\nSurname: " + this.surname +
                "\nBirth date: " + this.birthday +
                "\nGender: " + this.gender +
                "\nNumber: " + this.phone +
                "\nTime created: " + this.creationDate +
                "\nTime last edit: " + this.getLastEditDate());
    }

    @Override
    public String getPreview() {
        return this.name + " " + this.surname;
    }

}
