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

    ObservableList<Delivery> delList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colDeliveryCode.setCellValueFactory(new PropertyValueFactory<>("Dcode"));
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
        ObservableList<Delivery> delList = FXCollections.observableArrayList();

        try{
            ArrayList<Delivery> deliveriesData = DeliveryModel.getDeliveryData();
            for (Delivery delivery:deliveriesData){
                if(delivery.getDcode().contains(text) || delivery.getName().contains(text)){
                    Delivery d = new Delivery(delivery.getDcode(), delivery.getName(), delivery.getPayment());
                    delList.add(d);
                }
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }

        tblDelivery.setItems(delList);
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

    //form

    public void btnAddOnAction(ActionEvent actionEvent) {
        String Dcode = txtDeliveryCode.getText();
        String name = txtName.getText();
        double payment = Double.parseDouble(txtPayment.getText());

        Delivery delivery = new Delivery(Dcode,name,payment);
        try{
            boolean isAdded = DeliveryModel.save(delivery);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery Added Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Delivery not Added!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<Delivery> deliveries = tblDelivery.getItems();
        deliveries.add(delivery);
        tblDelivery.setItems(deliveries);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String Dcode = txtDeliveryCode.getText();
        String name = txtName.getText();
        double payment = Double.parseDouble(txtPayment.getText());

        try{
            Delivery delivery = new Delivery(Dcode,name,payment);
            boolean isUpdated = DeliveryModel.update(delivery, Dcode);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery Updated Successfully!").show();
                colDeliveryCode.setCellValueFactory(new PropertyValueFactory<>("Dcode"));
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

        ObservableList<Delivery> currentTableData = tblDelivery.getItems();
        String currentDeliveryCode = txtDeliveryCode.getText();

        for(Delivery delivery : currentTableData){
            if(delivery.getDcode() == currentDeliveryCode){
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
        String name = txtName.getText();
        double payment = Double.parseDouble(txtPayment.getText());

        try{
            Delivery delivery = new Delivery(Dcode,name,payment);
            boolean isDeleted = DeliveryModel.delete(delivery, Dcode);
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
            Delivery delivery = DeliveryModel.search(Dcode);
            if (delivery != null){
                fillData(delivery);
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void fillData(Delivery delivery) {
        txtDeliveryCode.setText(delivery.getDcode());
        txtName.setText(delivery.getName());
        txtPayment.setText(String.valueOf(delivery.getPayment()));
    }

    public void rowClicked(MouseEvent mouseEvent) {
        Delivery clickedDelivery = (Delivery) tblDelivery.getSelectionModel().getSelectedItem();
        txtDeliveryCode.setText(String.valueOf(clickedDelivery.getDcode()));
        txtName.setText(String.valueOf(clickedDelivery.getName()));
        txtPayment.setText(String.valueOf(clickedDelivery.getPayment()));
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String Dcode = txtSearch.getText();
        try{
            Delivery delivery = DeliveryModel.search(Dcode);
            if (delivery != null){
                fillData(delivery);
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
