package lk.ijse.cosmeticshop.dao.custom.impl;

/*
    @author BUDDINI
    @created 2/1/2023 - 10:26 PM   
*/


import lk.ijse.cosmeticshop.dao.CrudUtil;
import lk.ijse.cosmeticshop.dao.custom.EmployeeDAO;
import lk.ijse.cosmeticshop.model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employees = new ArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Employee");
        while (rst.next()){
            EmployeeDTO employee = new EmployeeDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5), rst.getString(6));
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public boolean add(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?)", entity.getEmployeeID(), entity.getName(), entity.getAddress(), entity.getSalary(), entity.getJobRole(), entity.getSectionCode());
    }

    @Override
    public boolean update(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Employee SET Name=?, Address=?, Salary=?, jobRole=?, sectionCode=? WHERE employeeID=?", entity.getName(), entity.getAddress(), entity.getSalary(), entity.getJobRole(), entity.getSectionCode(), entity.getEmployeeID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Employee WHERE employeeID=?", id);
    }

    @Override
    public EmployeeDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Employee WHERE employeeID = ?", id + "");
        if (rst.next()) {
            return new EmployeeDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5), rst.getString(6));
        }
        return null;
    }
}
