package lk.ijse.cosmeticshop.entity;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:15 AM   
*/


public class ProductDetailDTO {
    String productCode;
    String sectionCode;
    int qty;

    public ProductDetailDTO() {
    }

    public ProductDetailDTO(String productCode, String sectionCode, int qty) {
        this.productCode = productCode;
        this.sectionCode = sectionCode;
        this.qty = qty;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "productCode='" + productCode + '\'' +
                ", sectionCode='" + sectionCode + '\'' +
                ", qty=" + qty +
                '}';
    }
}
