package lk.ijse.cosmeticshop.to;

/*
    @author BUDDINI
    @created 11/26/2022 - 8:37 PM   
*/


public class Product {
    private String code;
    private String description;
    private double price;
    private int qtyOnHand;




    //private int qtyOnHand;

    public Product(){

    }

    public Product(String code, String description, double price, int qtyOnHand){
        this.setCode(code);
        this.setDescription(description);
        this.setPrice(price);
        this.setQtyOnHand(qtyOnHand);
    }
    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
