package lk.ijse.cosmeticshop.dao.custom;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:00 AM   
*/


import lk.ijse.cosmeticshop.dao.CrudDAO;
import lk.ijse.cosmeticshop.dao.CrudUtil;
import lk.ijse.cosmeticshop.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDTO> {
    public  ArrayList loadCustomerIds() throws SQLException, ClassNotFoundException;
}
