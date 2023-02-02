package lk.ijse.cosmeticshop.entity;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:14 AM   
*/


public class EmployeeDTO {
    String employeeID;
    String name;
    String address;
    double salary;
    String jobRole;
    String sectionCode;

//    public EmployeeDTO(String text, String txtNameText, String txtAddressText, String txtSalaryText, String txtJobRoleText, String txtSectionCodeText) {
//    }

    public EmployeeDTO(String employeeID, String name, String address, double salary, String jobRole, String sectionCode) {
        this.employeeID = employeeID;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.jobRole = jobRole;
        this.sectionCode = sectionCode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", jobRole='" + jobRole + '\'' +
                ", sectionCode='" + sectionCode + '\'' +
                '}';
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
