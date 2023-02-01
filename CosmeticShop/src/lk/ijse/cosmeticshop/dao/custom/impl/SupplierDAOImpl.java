package lk.ijse.cosmeticshop.dao.custom.impl;

/*
    @author BUDDINI
    @created 2/1/2023 - 11:53 PM   
*/


import lk.ijse.cosmeticshop.dao.CrudUtil;
import lk.ijse.cosmeticshop.dao.custom.SupplierDAO;
import lk.ijse.cosmeticshop.entity.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> suppliers = new ArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Supplier");
        while (rst.next()){
            SupplierDTO supplier = new SupplierDTO(rst.getString(1), rst.getString(2), rst.getString(3));
            suppliers.add(supplier);
        }
        return suppliers;
    }

    @Override
    public boolean add(SupplierDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Supplier VALUES (?, ?, ?)", entity.getSupplierID(), entity.getName(), entity.getDescription());
    }

    @Override
    public boolean update(SupplierDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Supplier SET Name=?, Description=? WHERE supplierID=?", entity.getName(), entity.getDescription(), entity.getSupplierID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Supplier WHERE supplierID=?", id);
    }

    @Override
    public SupplierDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Supplier WHERE supplierID = ?", id + "");
        if (rst.next()) {
            return new SupplierDTO(rst.getString(1), rst.getString(2), rst.getString(3));
        }
        return null;
    }
}
