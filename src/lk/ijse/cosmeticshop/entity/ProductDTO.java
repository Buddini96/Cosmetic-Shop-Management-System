package lk.ijse.cosmeticshop.entity;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:15 AM   
*/


public class ProductDTO {
    String productCode;
    String description;
    double unitprice;
    int qtyOnHand;

    public ProductDTO(String text, String txtDescriptionText, String txtUnitPriceText, String txtQtyOnHandText) {
    }

    public ProductDTO(String productCode, String description, double unitprice, int qtyOnHand) {
        this.productCode = productCode;
        this.description = description;
        this.unitprice = unitprice;
        this.qtyOnHand = qtyOnHand;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", description='" + description + '\'' +
                ", unitprice=" + unitprice +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
