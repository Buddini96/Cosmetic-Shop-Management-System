package lk.ijse.cosmeticshop.dao.custom.impl;

/*
    @author BUDDINI
    @created 2/1/2023 - 7:59 PM   
*/


import lk.ijse.cosmeticshop.dao.CrudUtil;
import lk.ijse.cosmeticshop.dao.custom.DCompanyDAO;
import lk.ijse.cosmeticshop.entity.CustomerDTO;
import lk.ijse.cosmeticshop.entity.DCompanyDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DCompanyDAOImpl implements DCompanyDAO {
    @Override
    public ArrayList<DCompanyDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<DCompanyDTO> dCompanies = new ArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM deliverycompany");
        while (rst.next()){
            DCompanyDTO dCompany = new DCompanyDTO(rst.getString(1), rst.getString(2), rst.getDouble(3));
            dCompanies.add(dCompany);
        }
        return dCompanies;
    }

    @Override
    public boolean add(DCompanyDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO deliverycompany VALUES (?, ?, ?)", entity.getDeliveryCode(), entity.getName(), entity.getPayment());
    }

    @Override
    public boolean update(DCompanyDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE deliverycompany SET Name=?, Payment=? WHERE deliveryCode=?", entity.getName(), entity.getPayment(), entity.getDeliveryCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM deliverycompany WHERE deliveryCode=?", id);

    }

    @Override
    public DCompanyDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM  deliverycompany WHERE deliveryCode = ?", id + "");
        if (rst.next()) {
            return new DCompanyDTO(rst.getString(1), rst.getString(2), rst.getDouble(3));
        }
        return null;
    }
}
