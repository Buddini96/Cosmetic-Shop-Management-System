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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cosmeticshop.model.CustomerModel;
import lk.ijse.cosmeticshop.model.SupplierModel;
import lk.ijse.cosmeticshop.to.Customer;
import lk.ijse.cosmeticshop.to.Supplier;
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

    ObservableList<Supplier> supList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supId"));
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
        ObservableList<Supplier> supList = FXCollections.observableArrayList();

        try{
            ArrayList<Supplier> suppliersData = SupplierModel.getSupplierData();
            for (Supplier supplier:suppliersData){
                if(supplier.getSupId().contains(text) || supplier.getName().contains(text)){
                    Supplier s = new Supplier(supplier.getSupId(), supplier.getName(), supplier.getDescription());
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

        Supplier supplier = new Supplier(supId,name,description);
        try{
            boolean isAdded = SupplierModel.save(supplier);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Supplier not Added!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<Supplier> suppliers = tblSupplier.getItems();
        suppliers.add(supplier);
        tblSupplier.setItems(suppliers);

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String supId = txtSupplierId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();

        try{
            Supplier supplier = new Supplier(supId,name,description);
            boolean isUpdated = SupplierModel.update(supplier, supId);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated Successfully!").show();
                colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supId"));
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

        ObservableList<Supplier> currentTableData = tblSupplier.getItems();
        String currentSupplierId = txtSupplierId.getText();

        for(Supplier supplier : currentTableData){
            if(supplier.getSupId() == currentSupplierId){
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
            Supplier supplier = new Supplier(supId,name,description);
            boolean isDeleted = SupplierModel.delete(supplier, supId);
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
        Supplier clickedSupplier = (Supplier) tblSupplier.getSelectionModel().getSelectedItem();
        txtSupplierId.setText(String.valueOf(clickedSupplier.getSupId()));
        txtName.setText(String.valueOf(clickedSupplier.getName()));
        txtDescription.setText(String.valueOf(clickedSupplier.getDescription()));
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String supId = txtSearch.getText();
        try{
            Supplier supplier = SupplierModel.search(supId);
            if (supplier != null){
                fillData(supplier);
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void fillData(Supplier supplier) {
        txtSupplierId.setText(supplier.getSupId());
        txtName.setText(supplier.getName());
        txtDescription.setText(supplier.getDescription());
    }

    public void txtSupplierIdOnAction(ActionEvent actionEvent) {
        String supId = txtSupplierId.getText();
        try{
            Supplier supplier = SupplierModel.search(supId);
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
