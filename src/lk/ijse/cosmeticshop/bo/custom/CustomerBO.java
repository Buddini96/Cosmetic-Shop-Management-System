package lk.ijse.cosmeticshop.bo.custom;

/*
    @author BUDDINI
    @created 1/29/2023 - 11:23 AM   
*/


import lk.ijse.cosmeticshop.bo.SuperBO;
import lk.ijse.cosmeticshop.entity.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean add(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    CustomerDTO search(String id) throws SQLException, ClassNotFoundException;
}
