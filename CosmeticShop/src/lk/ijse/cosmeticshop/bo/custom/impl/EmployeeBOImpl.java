package lk.ijse.cosmeticshop.bo.custom.impl;

/*
    @author BUDDINI
    @created 2/1/2023 - 10:39 PM   
*/


import lk.ijse.cosmeticshop.bo.custom.EmployeeBO;
import lk.ijse.cosmeticshop.dao.DAOFactory;
import lk.ijse.cosmeticshop.dao.custom.EmployeeDAO;
import lk.ijse.cosmeticshop.entity.CustomerDTO;
import lk.ijse.cosmeticshop.entity.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new EmployeeDTO(dto.getEmployeeID(), dto.getName(), dto.getAddress(), dto.getSalary(), dto.getJobRole(), dto.getSectionCode()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new EmployeeDTO(dto.getEmployeeID(), dto.getName(), dto.getAddress(), dto.getSalary(), dto.getJobRole(), dto.getSectionCode()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }
}
