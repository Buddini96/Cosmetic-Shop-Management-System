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
import lk.ijse.cosmeticshop.bo.BOFactory;
import lk.ijse.cosmeticshop.bo.custom.SupplierBO;
import lk.ijse.cosmeticshop.dao.custom.SupplierDAO;
import lk.ijse.cosmeticshop.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.cosmeticshop.model.SupplierDTO;
import lk.ijse.cosmeticshop.modelOld.SupplierModel;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    public AnchorPane pane;
    public JFXTextField txtSearch;
    public TextField txtSupplierId;
    public TextField txtName;
    public TextField txtDescription;
    public TableView tblSupplier;
    public TableColumn colSupplierId;
    public TableColumn colName;
    public TableColumn colDescription;

    ObservableList<SupplierDTO> supList = FXCollections.observableArrayList();
    SupplierBO supplierBO = (SupplierBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        //Search bar
        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) ->{
                    loadAllSuppliers(newValue);
                });
        loadAllSuppliers("");
    }

    private void loadAllSuppliers(String text) {
        ObservableList<SupplierDTO> supList = FXCollections.observableArrayList();

        try{
            ArrayList<SupplierDTO> suppliersData = SupplierModel.getSupplierData();
            for (SupplierDTO supplier:suppliersData){
                if(supplier.getSupplierID().contains(text) || supplier.getName().contains(text)){
                    SupplierDTO s = new SupplierDTO(supplier.getSupplierID(), supplier.getName(), supplier.getDescription());
                    supList.add(s);
                }
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }

        tblSupplier.setItems(supList);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String supId = txtSupplierId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();

        SupplierDTO supplierDTO = new SupplierDTO(supId,name,description);
        try{
            boolean isAdded = supplierBO.addSupplier(supplierDTO);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Supplier not Added!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<SupplierDTO> suppliers = tblSupplier.getItems();
        suppliers.add(supplierDTO);
        tblSupplier.setItems(suppliers);

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try{
            boolean isUpdated = supplierBO.updateSupplier(new SupplierDTO(txtSupplierId.getText(), txtName.getText(), txtDescription.getText()));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated Successfully!").show();
                colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

                //Search bar
                txtSearch.textProperty()
                        .addListener((observable, oldValue, newValue) ->{
                            loadAllSuppliers(newValue);
                        });
                loadAllSuppliers("");
            }else {
                new Alert(Alert.AlertType.WARNING, "Supplier not Updated!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<SupplierDTO> currentTableData = tblSupplier.getItems();
        String currentSupplierId = txtSupplierId.getText();

        for(SupplierDTO supplier : currentTableData){
            if(supplier.getSupplierID() == currentSupplierId){
                supplier.setName(txtName.getText());
                supplier.setDescription(txtDescription.getText());

                tblSupplier.setItems(currentTableData);
                tblSupplier.refresh();
                break;
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtSupplierId.setText("");
        txtName.setText("");
        txtDescription.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String supId = txtSupplierId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();

        try{
            boolean isDeleted = supplierBO.deleteSupplier(supId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Supplier not Deleted!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        int selectedID = tblSupplier.getSelectionModel().getSelectedIndex();
        tblSupplier.getItems().remove(selectedID);

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

    public void rowClicked(MouseEvent mouseEvent) {
        SupplierDTO clickedSupplier = (SupplierDTO) tblSupplier.getSelectionModel().getSelectedItem();
        txtSupplierId.setText(String.valueOf(clickedSupplier.getSupplierID()));
        txtName.setText(String.valueOf(clickedSupplier.getName()));
        txtDescription.setText(String.valueOf(clickedSupplier.getDescription()));
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String supId = txtSearch.getText();
        try{
            SupplierDAO supplierDAO = new SupplierDAOImpl();
            SupplierDTO search = supplierDAO.search(supId);
            if (search != null){
                fillData(search);
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void fillData(SupplierDTO supplier) {
        txtSupplierId.setText(supplier.getSupplierID());
        txtName.setText(supplier.getName());
        txtDescription.setText(supplier.getDescription());
    }

    public void txtSupplierIdOnAction(ActionEvent actionEvent) {
        String supId = txtSupplierId.getText();
        try{
            SupplierDTO supplier = supplierBO.searchSupplier(supId);
            if (supplier != null){
                fillData(supplier);
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void OwnerDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.OWNER_DASHBOARD, pane);
    }
}
