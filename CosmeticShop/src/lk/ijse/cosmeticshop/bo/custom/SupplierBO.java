package lk.ijse.cosmeticshop.bo.custom;

/*
    @author BUDDINI
    @created 2/2/2023 - 12:03 AM   
*/


import lk.ijse.cosmeticshop.bo.SuperBO;
import lk.ijse.cosmeticshop.entity.CustomerDTO;
import lk.ijse.cosmeticshop.entity.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;
}
