package lk.ijse.cosmeticshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cosmeticshop.bo.BOFactory;
import lk.ijse.cosmeticshop.bo.custom.CustomerBO;
import lk.ijse.cosmeticshop.dao.custom.CustomerDAO;
import lk.ijse.cosmeticshop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.cosmeticshop.model.CustomerModel;
import lk.ijse.cosmeticshop.model.ProductModel;
import lk.ijse.cosmeticshop.to.Product;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;

import lk.ijse.cosmeticshop.entity.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFormController implements Initializable {
    public AnchorPane pane;
    public TextField txtCustomerId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TableView tblCustomer;
    public JFXTextField txtSearch;
    public TableColumn colCustomerId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;

    private Matcher userId;
    private Matcher userName;
    private Matcher userAddress;
    private Matcher userContact;
    private String searchText="";

    ObservableList<CustomerDTO> cusList = FXCollections.observableArrayList();

    CustomerBO customerBO= (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        //Search bar
        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) ->{
                    loadAllCustomers(newValue);
                });
        loadAllCustomers("");
    }

    private void loadAllCustomers(String text) {
        ObservableList<CustomerDTO> cusList = FXCollections.observableArrayList();
        try{
            ArrayList<CustomerDTO> customerData = CustomerModel.getCustomerData();
            for (CustomerDTO customer:customerData){
                if(customer.getCustomerID().contains(text) || customer.getName().contains(text)){
                    CustomerDTO c = new CustomerDTO(customer.getCustomerID(), customer.getName(), customer.getAddress(), customer.getContact());
                    cusList.add(c);
                }
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }

        tblCustomer.setItems(cusList);
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, pane);
    }

    public void btnProductsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PRODUCT_ADMIN, pane);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDER, pane);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACE_ORDER, pane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HOME, pane);
    }

    public void btnDelveryOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELIVERY, pane);
    }

    //Form

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        Pattern pattern = Pattern.compile("(C0)([1-9]{1,2})");
        Matcher matcher = pattern.matcher(txtCustomerId.getText());

        boolean isMatcheId = matcher.matches();
        if(!isMatcheId){
            new Alert(Alert.AlertType.WARNING, "Invalid Customer Id!").show();
        }

        //Customer customer = new Customer(id,name,address,contact);
        CustomerDTO customerDTO=new CustomerDTO(id,name,address,contact);
            try{

            boolean isAdded = customerBO.add(customerDTO);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Customer not Added!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
                throw new RuntimeException(e);
            }

          ObservableList<CustomerDTO> customers = tblCustomer.getItems();
            customers.add(customerDTO);
            tblCustomer.setItems(customers);

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());

        try{
            //Customer customer = new Customer(id,name,address,contact);
            boolean isUpdated = customerBO.update(new CustomerDTO(txtCustomerId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText()));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated Successfully!").show();
                colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

                //Search bar
                txtSearch.textProperty()
                        .addListener((observable, oldValue, newValue) ->{
                            loadAllCustomers(newValue);
                        });
                loadAllCustomers("");
            }else {
                new Alert(Alert.AlertType.WARNING, "Customer not Updated!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }


        ObservableList<CustomerDTO> currentTableData = tblCustomer.getItems();
        String currentCustomerId = txtCustomerId.getText();

        for(CustomerDTO customer : currentTableData){
            if(customer.getCustomerID() == currentCustomerId){
                customer.setName(txtName.getText());
                customer.setAddress(txtAddress.getText());
                customer.setContact(txtContact.getText());

                tblCustomer.setItems(currentTableData);
                tblCustomer.refresh();
                break;
            }
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtCustomerId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtCustomerId.getText();
//        String name = txtName.getText();
//        String address = txtAddress.getText();
//        int contact = Integer.parseInt(txtContact.getText());

        try{
            //Customer customer = new Customer(id,name,address,contact);
            boolean isDeleted = customerBO.delete(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Customer not Deleted!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        int selectedID = tblCustomer.getSelectionModel().getSelectedIndex();
        tblCustomer.getItems().remove(selectedID);
        
    }

    public void txtCustomerIdOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        try {
            CustomerDTO customer = customerBO.search(id);
            if (customer != null){
                fillData(customer);
            }else {
                new Alert(Alert.AlertType.ERROR,"Empty Result..!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();
        try{
            CustomerDAO customerDAO = new CustomerDAOImpl();
           CustomerDTO search = customerDAO.search(id);
            if (search != null){

                fillData(search);
            }else {
                new Alert(Alert.AlertType.ERROR,"Empty Result..!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void fillData(CustomerDTO customer){
        txtCustomerId.setText(customer.getCustomerID());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtContact.setText(String.valueOf(customer.getContact()));
    }

    public void rowClicked(MouseEvent mouseEvent) {
        CustomerDTO clickedCustomer = (CustomerDTO) tblCustomer.getSelectionModel().getSelectedItem();
        txtCustomerId.setText(String.valueOf(clickedCustomer.getCustomerID()));
        txtName.setText(String.valueOf(clickedCustomer.getName()));
        txtAddress.setText(String.valueOf(clickedCustomer.getAddress()));
        txtContact.setText(String.valueOf(clickedCustomer.getContact()));

    }

}

