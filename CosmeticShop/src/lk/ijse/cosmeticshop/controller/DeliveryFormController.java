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
import lk.ijse.cosmeticshop.bo.custom.DCompanyBO;
import lk.ijse.cosmeticshop.dao.custom.CustomerDAO;
import lk.ijse.cosmeticshop.dao.custom.DCompanyDAO;
import lk.ijse.cosmeticshop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.cosmeticshop.dao.custom.impl.DCompanyDAOImpl;
import lk.ijse.cosmeticshop.entity.CustomerDTO;
import lk.ijse.cosmeticshop.entity.DCompanyDTO;
import lk.ijse.cosmeticshop.model.DeliveryModel;
import lk.ijse.cosmeticshop.to.Delivery;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeliveryFormController implements Initializable {
    public AnchorPane pane;
    public TextField txtDeliveryCode;
    public TextField txtName;
    public TextField txtPayment;

    public TableView tblDelivery;
    public JFXTextField txtSearch;
    public TableColumn colDeliveryCode;
    public TableColumn colName;
    public TableColumn colPayment;

    ObservableList<DCompanyDTO> delList = FXCollections.observableArrayList();
    DCompanyBO dCompanyBO = (DCompanyBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.DELIVERY_COMPANY);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colDeliveryCode.setCellValueFactory(new PropertyValueFactory<>("deliveryCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));

        //Search bar
        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) ->{
                    loadAllDeliveries(newValue);
                });
        loadAllDeliveries("");
    }

    private void loadAllDeliveries(String text) {
        ObservableList<DCompanyDTO> delList = FXCollections.observableArrayList();
        try{
            ArrayList<DCompanyDTO> deliveriesData = DeliveryModel.getDeliveryData();
            for (DCompanyDTO delivery:deliveriesData){
                if(delivery.getDeliveryCode().contains(text) || delivery.getName().contains(text)){
                    DCompanyDTO d = new DCompanyDTO(delivery.getDeliveryCode(), delivery.getName(), delivery.getPayment());
                    delList.add(d);
                }
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }

        tblDelivery.setItems(delList);
    }


    //form

    public void btnAddOnAction(ActionEvent actionEvent) {
        String Dcode = txtDeliveryCode.getText();
        String name = txtName.getText();
        double payment = Double.parseDouble(txtPayment.getText());

        DCompanyDTO dCompanyDTO = new DCompanyDTO(Dcode,name,payment);
        try{
            boolean isAdded = dCompanyBO.addDCompany(dCompanyDTO);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery Added Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Delivery not Added!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<DCompanyDTO> deliveries = tblDelivery.getItems();
        deliveries.add(dCompanyDTO);
        tblDelivery.setItems(deliveries);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try{
            boolean isUpdated = dCompanyBO.updateDCompany(new DCompanyDTO(txtDeliveryCode.getText(), txtName.getText(), Double.parseDouble(txtPayment.getText())));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery Updated Successfully!").show();
                colDeliveryCode.setCellValueFactory(new PropertyValueFactory<>("deliveryCode"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));

                //Search bar
                txtSearch.textProperty()
                        .addListener((observable, oldValue, newValue) ->{
                            loadAllDeliveries(newValue);
                        });
                loadAllDeliveries("");

            }else {
                new Alert(Alert.AlertType.WARNING, "Delivery not Updated!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<DCompanyDTO> currentTableData = tblDelivery.getItems();
        String currentDeliveryCode = txtDeliveryCode.getText();

        for(DCompanyDTO delivery : currentTableData){
            if(delivery.getDeliveryCode() == currentDeliveryCode){
                delivery.setName(txtName.getText());
                delivery.setPayment(Double.parseDouble(txtPayment.getText()));

                tblDelivery.setItems(currentTableData);
                tblDelivery.refresh();
                break;
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtDeliveryCode.setText("");
        txtName.setText("");
        txtPayment.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String Dcode = txtDeliveryCode.getText();
        try{

            boolean isDeleted = dCompanyBO.deleteDCompany(Dcode);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Delivery not Deleted!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        int selectedID = tblDelivery.getSelectionModel().getSelectedIndex();
        tblDelivery.getItems().remove(selectedID);

    }

    public void txtDeliveryCodeOnAction(ActionEvent actionEvent) {
        String Dcode = txtDeliveryCode.getText();
        try {
            DCompanyDTO delivery = dCompanyBO.searchDCompany(Dcode);
            if (delivery != null) {
                fillData(delivery);
            }
            new Alert(Alert.AlertType.ERROR, "Empty Result..!").show();
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void fillData(DCompanyDTO delivery) {
        txtDeliveryCode.setText(delivery.getDeliveryCode());
        txtName.setText(delivery.getName());
        txtPayment.setText(String.valueOf(delivery.getPayment()));
    }

    public void rowClicked(MouseEvent mouseEvent) {
        DCompanyDTO clickedDelivery = (DCompanyDTO) tblDelivery.getSelectionModel().getSelectedItem();
        txtDeliveryCode.setText(String.valueOf(clickedDelivery.getDeliveryCode()));
        txtName.setText(String.valueOf(clickedDelivery.getName()));
        txtPayment.setText(String.valueOf(clickedDelivery.getPayment()));
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();
        try {
            DCompanyDAO dCompanyDAO = new DCompanyDAOImpl();
            DCompanyDTO search = dCompanyDAO.search(id);
            if (search != null) {

                fillData(search);
            } else {
                new Alert(Alert.AlertType.ERROR, "Empty Result..!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

}
