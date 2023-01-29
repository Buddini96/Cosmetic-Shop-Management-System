package lk.ijse.cosmeticshop.to;

/*
    @author BUDDINI
    @created 11/26/2022 - 10:49 PM   
*/


public class Delivery {
    private String Dcode;
    private String name;
    private Double payment;

    public Delivery(){

    }

    public Delivery(String Dcode, String name, double payment){
        this.setDcode(Dcode);
        this.setName(name);
        this.setPayment(payment);
    }

    public String getDcode() {
        return Dcode;
    }

    public void setDcode(String dcode) {
        Dcode = dcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "Dcode='" + Dcode + '\'' +
                ", name='" + name + '\'' +
                ", payment=" + payment +
                '}';
    }
}
