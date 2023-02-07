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
import lk.ijse.cosmeticshop.bo.custom.OrderBO;
import lk.ijse.cosmeticshop.dao.custom.OrderDAO;
import lk.ijse.cosmeticshop.dao.custom.impl.OrderDAOImpl;
import lk.ijse.cosmeticshop.model.OrderDTO;
import lk.ijse.cosmeticshop.modelOld.OrderModel;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    public AnchorPane pane;
    public TextField txtOrderId;
    public TextField txtOrderDate;
    public TextField txtCustomerId;
    public TableView tblOrders;
    public JFXTextField txtSearch;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colCustomerId;

    ObservableList<OrderDTO> orderList = FXCollections.observableArrayList();

    OrderBO orderBO= (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER);

    public void initialize(URL url, ResourceBundle resourceBundle){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));


        //Search
        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) ->{
                    loadAllOrders(newValue);
                });
        loadAllOrders("");
    }

    private void loadAllOrders(String text) {
        ObservableList<OrderDTO> orderList = FXCollections.observableArrayList();
        try{
            ArrayList<OrderDTO> orderData = OrderModel.getOrdersData();
            for (OrderDTO order:orderData){
                if(order.getOrderID().contains(text) || order.getOrderDate().contains(text)){
                    OrderDTO c = new OrderDTO(order.getOrderID(), order.getOrderDate(), order.getCustomerID());
                    orderList.add(c);
                }
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }

        tblOrders.setItems(orderList);
    }



    public void btnAddOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        String orderDate = txtOrderDate.getText();
        String customerId = txtCustomerId.getText();

        OrderDTO orderDTO=new OrderDTO(orderId,orderDate,customerId);
        try{

            boolean isAdded = orderBO.addOrders(orderDTO);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Added Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Order not Added!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<OrderDTO> orders = tblOrders.getItems();
        orders.add(orderDTO);
        tblOrders.setItems(orders);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try{
            boolean isUpdated = orderBO.updateOrders(new OrderDTO(txtOrderId.getText(),txtOrderDate.getText(),txtCustomerId.getText()));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Updated Successfully!").show();
                colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderID"));
                colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
                colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));

                //Search bar
                txtSearch.textProperty()
                        .addListener((observable, oldValue, newValue) ->{
                            loadAllOrders(newValue);
                        });
                loadAllOrders("");
            }else {
                new Alert(Alert.AlertType.WARNING, "Order not Updated!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }


        ObservableList<OrderDTO> currentTableData = tblOrders.getItems();
        String currentOrderId = txtOrderId.getText();

        for(OrderDTO order : currentTableData){
            if(order.getOrderID() == currentOrderId){
                order.setOrderDate(txtOrderDate.getText());
                order.setCustomerID(txtCustomerId.getText());

                tblOrders.setItems(currentTableData);
                tblOrders.refresh();
                break;
            }
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtOrderId.setText("");
        txtOrderDate.setText("");
        txtCustomerId.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{
        String orderId = txtOrderId.getText();

        try{
            boolean isDeleted = orderBO.deleteOrders(orderId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Order not Deleted!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        int selectedID = tblOrders.getSelectionModel().getSelectedIndex();
        tblOrders.getItems().remove(selectedID);
    }

    public void OrderIdOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        try {
            OrderDTO order = orderBO.searchOrders(orderId);
            if (order != null){
                fillData(order);
            }else {
                new Alert(Alert.AlertType.ERROR,"Empty Result..!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void fillData(OrderDTO order) {
        txtOrderId.setText(order.getOrderID());
        txtOrderDate.setText(order.getOrderDate());
        txtCustomerId.setText(order.getCustomerID());
    }

    public void rowClicked(MouseEvent mouseEvent) {
        OrderDTO clickedOrder = (OrderDTO) tblOrders.getSelectionModel().getSelectedItem();
        txtOrderId.setText(String.valueOf(clickedOrder.getOrderID()));
        txtOrderDate.setText(String.valueOf(clickedOrder.getOrderDate()));
        txtCustomerId.setText(String.valueOf(clickedOrder.getCustomerID()));
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        try{
            OrderDAO orderDAO = new OrderDAOImpl();
            OrderDTO search = orderDAO.search(orderId);
            if (search != null){

                fillData(search);
            }else {
                new Alert(Alert.AlertType.ERROR,"Empty Result..!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
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
