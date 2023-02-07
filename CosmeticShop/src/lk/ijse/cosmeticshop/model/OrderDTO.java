package lk.ijse.cosmeticshop.model;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:15 AM   
*/


import java.time.LocalDate;

public class OrderDTO {
    String orderID;
    String orderDate;
    String customerID;

    public OrderDTO(String orderID, LocalDate now, String customerID) {
    }

    public OrderDTO(String orderID, String orderDate, String customerID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
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

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerID='" + customerID + '\'' +
                '}';
    }
}
