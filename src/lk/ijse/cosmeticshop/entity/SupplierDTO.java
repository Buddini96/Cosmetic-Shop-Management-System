package lk.ijse.cosmeticshop.entity;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:16 AM   
*/


public class SupplierDTO {
    String supplierID;
    String name;
    String description;

    public SupplierDTO() {
    }

    public SupplierDTO(String supplierID, String name, String description) {
        this.supplierID = supplierID;
        this.name = name;
        this.description = description;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierID='" + supplierID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
