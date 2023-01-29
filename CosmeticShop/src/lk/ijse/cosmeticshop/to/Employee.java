package lk.ijse.cosmeticshop.to;

/*
    @author BUDDINI
    @created 11/26/2022 - 8:15 PM   
*/

public class Employee {
    private String id;
    private String name;
    private String address;
    private Double salary;
    private String jobRole;
    private String secCode;

    public Employee(){
    }

    public Employee(String id, String name, String address, double salary, String jobRole, String secCode){
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setSalary(salary);
        this.setJobRole(jobRole);
        this.setSecCode(secCode);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }


    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eId='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", jobRole='" + jobRole + '\'' +
                ", secCode='" + secCode + '\'' +
                '}';
    }
}
