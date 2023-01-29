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
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean add(CustomerDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(CustomerDTO entity) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException;
}
