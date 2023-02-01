package lk.ijse.cosmeticshop.entity;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:17 AM   
*/


public class SupplierDetailDTO {
    String supplierID;
    String productCode;
    int qty;
    double sellingPrice;

    public SupplierDetailDTO() {
    }

    public SupplierDetailDTO(String supplierID, String productCode, int qty, double sellingPrice) {
        this.supplierID = supplierID;
        this.productCode = productCode;
        this.qty = qty;
        this.sellingPrice = sellingPrice;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return "SupplierDetail{" +
                "supplierID='" + supplierID + '\'' +
                ", productCode='" + productCode + '\'' +
                ", qty=" + qty +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
