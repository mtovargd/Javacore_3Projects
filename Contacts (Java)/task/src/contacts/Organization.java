package contacts;

import java.time.LocalDateTime;

public class Organization extends Contact{
    String address;
    public Organization(){
        super(false);
        this.setName();
        this.setAddress();
        this.setPhone();
        this.creationDate = LocalDateTime.now().toString();
    }

    protected void setName() {
        System.out.println("Enter the organization name: ");
        this.name = this.checkEmpty(scanner.nextLine());
    }
    protected void setAddress() {
        System.out.println("Enter the address: ");
        this.address = this.checkEmpty(scanner.nextLine());
    }
    public String getAddress() {
        return address;
    }

    public void getInfo() {
        System.out.println("Organization name: " + this.getName() + "\nAddress: " + this.getAddress() +
               "\nNumber: " + this.getPhone() + "\nTime created: " + this.getCreationDate() +
                "\nTime last edit: " + this.getLastEditDate() + "\n");
    }
}
