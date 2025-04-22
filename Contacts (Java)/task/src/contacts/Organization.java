package contacts;
import java.io.Serializable;

public class Organization extends Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    String address;

    public Organization() {
        super();
        this.setName();
        this.setAddress();
        this.setPhone();
    }

    protected void setName() {
        System.out.print("Enter the organization name: ");
        this.name = this.checkEmpty(scanner.nextLine());
    }

    protected void setAddress() {
        System.out.print("Enter the address: ");
        this.address = this.checkEmpty(scanner.nextLine());
    }

    public String[] getEditableFields() {
        return new String[]{"name", "address", "number"};
    }

    public void setField(String field, String value) {
        switch (field) {
            case "name": this.name = checkEmpty(value); break;
            case "address": this.address = checkEmpty(value); break;
            case "number":
                this.setPhone(value);
                break;
        }
        setEditDate();
    }

    public String getField(String field) {
        switch (field) {
            case "name": return this.name;
            case "address": return this.address;
            case "number": return this.phone;
            default: return "";
        }
    }

    public void getInfo() {
        System.out.println("Organization name: " + this.name +
                "\nAddress: " + this.address +
                "\nNumber: " + this.phone +
                "\nTime created: " + this.creationDate +
                "\nTime last edit: " + this.getLastEditDate());
    }

    @Override
    public String getPreview() {
        return this.name;
    }

}
