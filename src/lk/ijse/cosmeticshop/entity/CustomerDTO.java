package lk.ijse.cosmeticshop.entity;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:12 AM   
*/


public class CustomerDTO {
    String customerID;
    String name;
    String address;
    String contact;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerID, String name, String address, String contact) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
