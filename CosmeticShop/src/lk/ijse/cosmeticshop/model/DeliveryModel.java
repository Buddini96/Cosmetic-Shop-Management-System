package lk.ijse.cosmeticshop.model;

/*
    @author BUDDINI
    @created 11/26/2022 - 10:49 PM   
*/

import lk.ijse.cosmeticshop.to.Delivery;
import lk.ijse.cosmeticshop.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryModel {

        public static ArrayList<Delivery> getDeliveryData() throws SQLException, ClassNotFoundException {
        ArrayList<Delivery> deliveriesData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM deliverycompany ORDER BY CAST(SUBSTRING(deliveryCode, 2) AS UNSIGNED)");
        while (rs.next()){
            deliveriesData.add(new Delivery(rs.getString("deliveryCode"),
                    rs.getString("Name"),
                    rs.getDouble("Payment")));
        }
        return deliveriesData;
    }
    public static boolean save(Delivery delivery) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO deliverycompany VALUES (?, ?, ?)";
        return CrudUtil.execute(sql, delivery.getDcode(), delivery.getName(), delivery.getPayment());
    }

    public static boolean update (Delivery delivery, String Dcode) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE deliverycompany SET Name=?, Payment=? WHERE deliveryCode=?";
        return CrudUtil.execute(sql, delivery.getName(), delivery.getPayment(),Dcode);
    }

    public static boolean delete (Delivery delivery, String Dcode) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM deliverycompany WHERE deliveryCode=?";
        return CrudUtil.execute(sql,delivery.getDcode());
    }

    public static Delivery search(String Dcode) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM  deliverycompany WHERE deliveryCode = ?";
        ResultSet result = CrudUtil.execute(sql,Dcode);

        if(result.next()){
            return new Delivery(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3)
            );
        }
        return null;
    }
}
