package lk.ijse.cosmeticshop.bo.custom.impl;

/*
    @author BUDDINI
    @created 2/2/2023 - 12:04 AM   
*/


import lk.ijse.cosmeticshop.bo.custom.SupplierBO;
import lk.ijse.cosmeticshop.dao.DAOFactory;
import lk.ijse.cosmeticshop.dao.custom.SupplierDAO;
import lk.ijse.cosmeticshop.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }

    @Override
    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new SupplierDTO(dto.getSupplierID(), dto.getName(), dto.getDescription()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new SupplierDTO(dto.getSupplierID(), dto.getName(), dto.getDescription()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(id);
    }
}
