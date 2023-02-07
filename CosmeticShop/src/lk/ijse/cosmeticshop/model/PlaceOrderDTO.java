package lk.ijse.cosmeticshop.model;

/*
    @author BUDDINI
    @created 11/29/2022 - 9:31 PM   
*/

import javafx.scene.control.Button;
import lk.ijse.cosmeticshop.view.tdm.CartDetail;

import java.util.ArrayList;

public class PlaceOrderDTO {
    private String code;
    private String description;
    private double total;
    private Button btnDelete;

    String orderID;
    String orderDate;
    String customerID;

    String orderDetailID;
    String productCode;

    int Qty;
    double sellingPrice;
    private int qtyOnHand;
    private double unitPrice;

    private ArrayList<CartDetail> orderDetails = new ArrayList<>();


    public PlaceOrderDTO() {
    }

    public PlaceOrderDTO(String code, String description, double total, Button btnDelete, String orderID, String orderDate, String customerID, String orderDetailID, String productCode, int qty, double sellingPrice, int qtyOnHand, double unitPrice, ArrayList<CartDetail> orderDetails) {
        this.code = code;
        this.description = description;
        this.total = total;
        this.btnDelete = btnDelete;
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.orderDetailID = orderDetailID;
        this.productCode = productCode;
        this.Qty = qty;
        this.sellingPrice = sellingPrice;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
        this.orderDetails = orderDetails;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ArrayList<CartDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<CartDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "PlaceOrderDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", total=" + total +
                ", btnDelete=" + btnDelete +
                ", orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerID='" + customerID + '\'' +
                ", orderDetailID='" + orderDetailID + '\'' +
                ", productCode='" + productCode + '\'' +
                ", Qty=" + Qty +
                ", sellingPrice=" + sellingPrice +
                ", qtyOnHand=" + qtyOnHand +
                ", unitPrice=" + unitPrice +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

