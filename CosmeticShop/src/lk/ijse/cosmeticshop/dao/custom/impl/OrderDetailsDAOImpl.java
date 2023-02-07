package lk.ijse.cosmeticshop.dao.custom.impl;

/*
    @author BUDDINI
    @created 1/29/2023 - 11:16 AM   
*/


import lk.ijse.cosmeticshop.dao.CrudUtil;
import lk.ijse.cosmeticshop.dao.custom.OrderDetailDAO;
import lk.ijse.cosmeticshop.model.OrderDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
            ArrayList<OrderDetailDTO> orderdetails = new ArrayList();
            ResultSet rst = CrudUtil.execute("SELECT * FROM OrderDetail");
            while (rst.next()){
                OrderDetailDTO orderdetail = new OrderDetailDTO(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDouble(4));
                orderdetails.add(orderdetail);
            }
            return orderdetails;
    }

    @Override
    public boolean add(OrderDetailDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO OrderDetail VALUES(?, ?, ?, ?)", entity.getOrderID(), entity.getProductCode(), entity.getQty(), entity.getSellingPrice());
    }

    @Override
    public boolean update(OrderDetailDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE OrderDetail SET productCode=?, Qty=?, sellingPrice=? WHERE orderID=?", entity.getProductCode(), entity.getQty(), entity.getSellingPrice(), entity.getOrderID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM OrderDetail WHERE orderID=?", id);
    }

    @Override
    public OrderDetailDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM OrderDetail WHERE orderID = ?", id + "");
        if (rst.next()) {
            return new OrderDetailDTO(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDouble(4));
        }
        return null;
    }


}
