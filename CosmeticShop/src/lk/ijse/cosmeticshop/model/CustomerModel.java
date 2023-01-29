package lk.ijse.cosmeticshop.model;

/*
    @author BUDDINI
    @created 11/25/2022 - 9:39 AM   
*/


import lk.ijse.cosmeticshop.entity.CustomerDTO;
import lk.ijse.cosmeticshop.to.Customer;
import lk.ijse.cosmeticshop.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {

    public static ArrayList<CustomerDTO> getCustomerData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customersData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM Customer ORDER BY CAST(SUBSTRING(customerID, 2) AS UNSIGNED)");
        while (rs.next()){
            customersData.add(new CustomerDTO(rs.getString("customerID"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("contact")));
        }
        return customersData;
    }

    public static boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getId(), customer.getName(), customer.getAddress(), customer.getContact());
    }

    public static Customer search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        ResultSet result = CrudUtil.execute(sql,id);

        if(result.next()){
            return new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4)
            );
        }
        return null;
    }

    public static boolean update (Customer customer, String id) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Customer SET Name=?, Address=?, contact=? WHERE customerID=?";
        return CrudUtil.execute(sql, customer.getName(), customer.getAddress(), customer.getContact(),id);
    }

    public static boolean delete (Customer customer, String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Customer WHERE customerID=?";
        return CrudUtil.execute(sql,customer.getId());
    }

    public static ArrayList loadCustomerIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT customerID FROM Customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()){
            idList.add(result.getString(1));
        }
        return idList;
    }
}
