package lk.ijse.cosmeticshop.bo.custom.impl;

/*
    @author BUDDINI
    @created 1/29/2023 - 11:24 AM   
*/


import lk.ijse.cosmeticshop.bo.custom.CustomerBO;
import lk.ijse.cosmeticshop.dao.DAOFactory;
import lk.ijse.cosmeticshop.dao.custom.CustomerDAO;
import lk.ijse.cosmeticshop.entity.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
       return customerDAO.getAll();
    }

    @Override
    public boolean add(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new CustomerDTO(dto.getCustomerID(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new CustomerDTO(dto.getCustomerID(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
       return customerDAO.search(id);
    }
}
