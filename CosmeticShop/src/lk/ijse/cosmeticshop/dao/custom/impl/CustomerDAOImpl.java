package lk.ijse.cosmeticshop.dao.custom.impl;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:00 AM   
*/


import lk.ijse.cosmeticshop.dao.CrudUtil;
import lk.ijse.cosmeticshop.dao.custom.CustomerDAO;
import lk.ijse.cosmeticshop.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customers = new ArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer ORDER BY CAST(SUBSTRING(customerID, 2) AS UNSIGNED)");
        while (rst.next()){
            CustomerDTO customer = new CustomerDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public boolean add(CustomerDTO entity) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("INSERT INTO Customer VALUES (?, ?, ?, ?)", entity.getCustomerID(), entity.getName(), entity.getAddress(), entity.getContact());
    }

    @Override
    public boolean update(CustomerDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET Name=?, Address=?, contact=? WHERE customerID=?", entity.getName(), entity.getAddress(), entity.getContact(), entity.getCustomerID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE customerID=?", id);
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer WHERE customerID = ?", id + "");
        if (rst.next()) {
            return new CustomerDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
        }
        return null;
    }
}
