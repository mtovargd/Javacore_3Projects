package contacts;

import java.time.LocalDateTime;

public class Person extends Contact{

    String surname;
    String birthday;
    String gender;

    public Person(){
        super(true);
        this.setName();
        this.setSurname();
        this.setBirthday();
        this.setGender();
        this.setPhone();
        this.creationDate = LocalDateTime.now().toString();
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
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        }
    }

    public String getSurname() { return surname; }
    public String getGender() { return gender; }
    public  String getBirthday() { return birthday; }

    public void getInfo() {
        System.out.println("Name: " + this.getName() + "\nSurname: " + this.getSurname() +
                "\nBirth date: " + this.getBirthday() + "\nGender: " + this.getGender() +
                "\nNumber: " + this.getPhone() + "\nTime created: " + this.getCreationDate() +
                "\nTime last edit: " + this.getLastEditDate() + "\n");
    }
}
