package lk.ijse.cosmeticshop.bo.custom;

/*
    @author BUDDINI
    @created 2/1/2023 - 10:39 PM   
*/


import lk.ijse.cosmeticshop.bo.SuperBO;
import lk.ijse.cosmeticshop.entity.CustomerDTO;
import lk.ijse.cosmeticshop.entity.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException;
}
