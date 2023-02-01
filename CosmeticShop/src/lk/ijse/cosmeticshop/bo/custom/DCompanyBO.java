package lk.ijse.cosmeticshop.bo.custom;

/*
    @author BUDDINI
    @created 2/1/2023 - 8:25 PM   
*/


import lk.ijse.cosmeticshop.bo.SuperBO;
import lk.ijse.cosmeticshop.entity.CustomerDTO;
import lk.ijse.cosmeticshop.entity.DCompanyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DCompanyBO extends SuperBO {
    ArrayList<DCompanyDTO> getAllDCompany() throws SQLException, ClassNotFoundException;

    boolean addDCompany(DCompanyDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateDCompany(DCompanyDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteDCompany(String id) throws SQLException, ClassNotFoundException;

    DCompanyDTO searchDCompany(String id) throws SQLException, ClassNotFoundException;
}

