package lk.ijse.cosmeticshop.model;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:15 AM   
*/


public class OrderDetailDTO {
    String orderID;
    String productCode;
    int Qty;
    double sellingPrice;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderID, String productCode, int qty, double sellingPrice) {
        this.orderID = orderID;
        this.productCode = productCode;
        Qty = qty;
        this.sellingPrice = sellingPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderID='" + orderID + '\'' +
                ", productCode='" + productCode + '\'' +
                ", Qty=" + Qty +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
