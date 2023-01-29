package lk.ijse.cosmeticshop.to;

/*
    @author BUDDINI
    @created 11/27/2022 - 8:12 PM   
*/


public class Supplier {
    private String supId;
    private String name;
    private String description;

    public Supplier(){

    }

    public Supplier(String supId, String name, String description){
        this.setSupId(supId);
        this.setName(name);
        this.setDescription(description);
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
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
                "supId='" + supId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
