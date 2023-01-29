package lk.ijse.cosmeticshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cosmeticshop.model.EmployeeModel;
import lk.ijse.cosmeticshop.to.Employee;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {
    public AnchorPane pane;
    public JFXTextField txtSearch;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtJobRole;
    public TextField txtSalary;
    public TableView tblEmployee;
    public TextField txtEmployeeID;
    public TableColumn colEmployeeId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colJobRole;
    public TableColumn colSalary;
    public TableColumn colSecCode;
    public TextField txtSectionCode;

    ObservableList<Employee> empList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
        colSecCode.setCellValueFactory(new PropertyValueFactory<>("secCode"));

        //Search bar
        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) ->{
                    loadAllEmployees(newValue);
                });
        loadAllEmployees("");
    }

    private void loadAllEmployees(String text) {
        ObservableList<Employee> empList = FXCollections.observableArrayList();

        try{
            ArrayList<Employee> employeesData = EmployeeModel.getEmployeeData();
            for (Employee employee:employeesData){
                if(employee.getId().contains(text) || employee.getName().contains(text)){
                    Employee e = new Employee(employee.getId(), employee.getName(), employee.getAddress(), employee.getSalary(), employee.getJobRole(), employee.getSecCode());
                    empList.add(e);
                }
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }

        tblEmployee.setItems(empList);
    }

    public void btnEmployeenAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE, pane);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER, pane);
    }

    public void btnProductsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PRODUCT, pane);
    }

    public void btnIncomeReportOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.INCOME_REPORT, pane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HOME, pane);
    }



    //form

    public void btnAddOnAction(ActionEvent actionEvent) {
        String eId = txtEmployeeID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String jobRole = txtJobRole.getText();
        String secCode = txtSectionCode.getText();

        Employee employee = new Employee(eId,name,address,salary, jobRole, secCode);
        try{
            boolean isAdded = EmployeeModel.save(employee);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Employee not Added!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<Employee> employees = tblEmployee.getItems();
        employees.add(employee);
        tblEmployee.setItems(employees);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String eId = txtEmployeeID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String jobRole = txtJobRole.getText();
        String secCode = txtSectionCode.getText();

        try{
            Employee employee = new Employee(eId,name,address,salary, jobRole, secCode);
            boolean isUpdated = EmployeeModel.update(employee, eId);
            if (isUpdated){
                colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
                colJobRole.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
                colSecCode.setCellValueFactory(new PropertyValueFactory<>("secCode"));

                //Search bar
                txtSearch.textProperty()
                        .addListener((observable, oldValue, newValue) ->{
                            loadAllEmployees(newValue);
                        });
                loadAllEmployees("");
            }else {
                new Alert(Alert.AlertType.WARNING, "Employee not Updated!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<Employee> currentTableData = tblEmployee.getItems();
        String currentEmployeeId = txtEmployeeID.getText();

        for(Employee employee : currentTableData){
            if(employee.getId() == currentEmployeeId){
                employee.setName(txtName.getText());
                employee.setAddress(txtAddress.getText());
                employee.setSalary(Double.parseDouble(txtSalary.getText()));
                employee.setJobRole(txtJobRole.getText());
                employee.setSecCode(txtSectionCode.getText());

                tblEmployee.setItems(currentTableData);
                tblEmployee.refresh();
                break;
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtEmployeeID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtSalary.setText("");
        txtJobRole.setText("");
        txtSectionCode.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String eId = txtEmployeeID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String jobRole = txtJobRole.getText();
        String secCode = txtSectionCode.getText();

        try{
            Employee employee = new Employee(eId,name,address,salary, jobRole, secCode);
            boolean isDeleted = EmployeeModel.delete(employee, eId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Employee not Deleted!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        int selectedID = tblEmployee.getSelectionModel().getSelectedIndex();
        tblEmployee.getItems().remove(selectedID);
    }

    public void txtEmployeeIdOnAction(ActionEvent actionEvent) {
        String eId = txtEmployeeID.getText();
        try {
            Employee employee = EmployeeModel.search(eId);
            if (employee != null){
                fillData(employee);
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void fillData(Employee employee) {
        txtEmployeeID.setText(employee.getId());
        txtName.setText(employee.getName());
        txtAddress.setText(employee.getAddress());
        txtSalary.setText(String.valueOf(employee.getSalary()));
        txtJobRole.setText(employee.getJobRole());
        txtSectionCode.setText(employee.getSecCode());
    }

    public void rowClicked(MouseEvent mouseEvent) {
        Employee clickedEmployee = (Employee) tblEmployee.getSelectionModel().getSelectedItem();
        txtEmployeeID.setText(String.valueOf(clickedEmployee.getId()));
        txtName.setText(String.valueOf(clickedEmployee.getName()));
        txtAddress.setText(String.valueOf(clickedEmployee.getAddress()));
        txtSalary.setText(String.valueOf(clickedEmployee.getSalary()));
        txtJobRole.setText(String.valueOf(clickedEmployee.getJobRole()));
        txtSectionCode.setText(String.valueOf(clickedEmployee.getSecCode()));
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String eId = txtEmployeeID.getText();
//        String name = txtName.getText();
//        String address = txtAddress.getText();
//        double salary = Double.parseDouble(txtSalary.getText());
//        String jobRole = txtJobRole.getText();
//        String secCode = txtSectionCode.getText();

        try {
            Employee employee = EmployeeModel.search(eId);
            if (employee != null){
                fillData(employee);
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void OwnerDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.OWNER_DASHBOARD, pane);
    }
}
