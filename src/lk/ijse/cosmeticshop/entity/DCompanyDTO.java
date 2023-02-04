package lk.ijse.cosmeticshop.entity;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:13 AM   
*/


public class DCompanyDTO {
    String deliveryCode;
    String name;

    double payment;


   

    public DCompanyDTO(String deliveryCode, String name, double payment) {
        this.deliveryCode = deliveryCode;
        this.name = name;
        this.payment = payment;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "DCompanyDTO{" +
                "deliveryCode='" + deliveryCode + '\'' +
                ", name='" + name + '\'' +
                ", payment=" + payment +
                '}';
    }
}
