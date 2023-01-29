package lk.ijse.cosmeticshop.model;

/*
    @author BUDDINI
    @created 11/26/2022 - 8:12 PM   
*/

import lk.ijse.cosmeticshop.to.Employee;
import lk.ijse.cosmeticshop.dao.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {

    public static ArrayList<Employee> getEmployeeData() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employeesData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM Employee ORDER BY CAST(SUBSTRING(employeeID, 2) AS UNSIGNED)");
        while (rs.next()){
            employeesData.add(new Employee(rs.getString("employeeID"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getDouble("salary"),
                    rs.getString("jobRole"),
                    rs.getString("sectionCode")));
        }
        return employeesData;
    }
    public static boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, employee.getId(), employee.getName(), employee.getAddress(), employee.getSalary(), employee.getJobRole(), employee.getSecCode());
    }

    public static boolean update (Employee employee, String eId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Employee SET Name=?, Address=?, Salary=?, jobRole=?, sectionCode=? WHERE employeeID=?";
        return CrudUtil.execute(sql, employee.getName(), employee.getAddress(), employee.getSalary(), employee.getJobRole(), employee.getSecCode(),eId);
    }

    public static Employee search(String eId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Employee WHERE employeeID = ?";
        ResultSet result = CrudUtil.execute(sql,eId);

        if(result.next()){
            return new Employee(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4),
                    result.getString(5),
                    result.getString(6)
            );
        }
        return null;
    }

    public static boolean delete (Employee employee, String eId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Employee WHERE employeeID=?";
        return CrudUtil.execute(sql,employee.getId());
    }
}
