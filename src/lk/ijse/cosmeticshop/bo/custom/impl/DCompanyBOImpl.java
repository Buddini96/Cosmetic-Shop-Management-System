package lk.ijse.cosmeticshop.bo.custom.impl;

/*
    @author BUDDINI
    @created 2/1/2023 - 8:25 PM   
*/


import lk.ijse.cosmeticshop.bo.custom.DCompanyBO;
import lk.ijse.cosmeticshop.dao.DAOFactory;
import lk.ijse.cosmeticshop.dao.custom.CustomerDAO;
import lk.ijse.cosmeticshop.dao.custom.DCompanyDAO;
import lk.ijse.cosmeticshop.entity.CustomerDTO;
import lk.ijse.cosmeticshop.entity.DCompanyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class DCompanyBOImpl implements DCompanyBO {

    DCompanyDAO dCompanyDAO = (DCompanyDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DELIVERY_COMPANY);

    @Override
    public ArrayList<DCompanyDTO> getAllDCompany() throws SQLException, ClassNotFoundException {
        return dCompanyDAO.getAll();
    }

    @Override
    public boolean addDCompany(DCompanyDTO dto) throws SQLException, ClassNotFoundException {
        return dCompanyDAO.add(new DCompanyDTO(dto.getDeliveryCode(), dto.getName(), dto.getPayment()));
    }

    @Override
    public boolean updateDCompany(DCompanyDTO dto) throws SQLException, ClassNotFoundException {
        return dCompanyDAO.update(new DCompanyDTO(dto.getDeliveryCode(), dto.getName(), dto.getPayment()));
    }

    @Override
    public boolean deleteDCompany(String id) throws SQLException, ClassNotFoundException {
        return dCompanyDAO.delete(id);
    }

    @Override
    public DCompanyDTO searchDCompany(String id) throws SQLException, ClassNotFoundException {
        return dCompanyDAO.search(id);
    }


}
