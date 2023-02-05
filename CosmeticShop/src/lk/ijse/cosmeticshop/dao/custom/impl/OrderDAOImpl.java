package lk.ijse.cosmeticshop.dao.custom.impl;

/*
    @author BUDDINI
    @created 1/29/2023 - 11:16 AM   
*/


import lk.ijse.cosmeticshop.dao.CrudUtil;
import lk.ijse.cosmeticshop.dao.custom.OrderDAO;
import lk.ijse.cosmeticshop.entity.CustomerDTO;
import lk.ijse.cosmeticshop.entity.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> orders = new ArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Orders");
        while (rst.next()){
            OrderDTO order = new OrderDTO(rst.getString(1), rst.getString(2), rst.getString(3));
            orders.add(order);
        }
        return orders;
    }

    @Override
    public boolean add(OrderDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Orders VALUES(?, ?, ?)", entity.getOrderID(), entity.getOrderDate(), entity.getCustomerID());
    }

    @Override
    public boolean update(OrderDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET orderDate=?, customerID=? WHERE orderID=?", entity.getOrderDate(), entity.getCustomerID(), entity.getCustomerID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Orders WHERE orderID=?", id);

    }

    @Override
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Orders WHERE orderID = ?", id + "");
        if (rst.next()) {
            return new OrderDTO(rst.getString(1), rst.getString(2), rst.getString(3));
        }
        return null;
    }
}
