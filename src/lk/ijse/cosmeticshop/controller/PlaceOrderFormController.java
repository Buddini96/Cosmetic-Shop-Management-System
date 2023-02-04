package lk.ijse.cosmeticshop.controller;

/*
    @author BUDDINI
    @created 11/28/2022 - 5:59 PM   
*/


import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cosmeticshop.model.CustomerModel;
import lk.ijse.cosmeticshop.model.OrderModel;
import lk.ijse.cosmeticshop.model.PlaceOrderModel;
import lk.ijse.cosmeticshop.model.ProductModel;
import lk.ijse.cosmeticshop.to.CartDetail;
import lk.ijse.cosmeticshop.to.Customer;
import lk.ijse.cosmeticshop.to.PlaceOrder;
import lk.ijse.cosmeticshop.to.Product;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;
import lk.ijse.cosmeticshop.view.tm.PlaceOrderTM;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {
    public AnchorPane pane;
    public ComboBox <String> cmbCustomerId;
    public ComboBox <String>cmbProductCode;
    public TextField txtDescription;
    public TextField txtPrice;
    public TextField txtQty;

    public JFXTextField txtSearch;
    public Label lblTotal;
    public TextField txtQtyOnHand;
    //public TableView tblOrderCart;
    @FXML
    private TableView<PlaceOrderTM> tblOrderCart;
    public TableColumn colProductCode;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colAction;
    public TableColumn colTotal;
    public TextField txtCustomerName;
    public TextField txtOrderId;
    public TextField txtOrderDate;

    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadOrderDate();
        loadCustomerIds();
        loadNextOrderId();
        loadProductCodes();
        setCellValueFactory();
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

    private void loadNextOrderId() {
        try {
            String orderId = OrderModel.generateNextOrderId();
            txtOrderId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = CustomerModel.loadCustomerIds();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbCustomerId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadProductCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = ProductModel.loadProductCodes();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbProductCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colProductCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }

    private void loadOrderDate() {
        txtOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HOME, pane);
    }

    public void btnDelveryOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELIVERY, pane);
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbProductCode.getValue());
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());
        double total = price * qty;
        Button btnDelete = new Button("Delete");

        txtQty.setText("");

        if (!obList.isEmpty()) {
            L1:

            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colProductCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = price * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    tblOrderCart.refresh();
                    return;
                }

            }

        }
        setTotal();

        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {


                tblOrderCart.getItems().removeAll(tblOrderCart.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new PlaceOrderTM(code, description, qty, price, total, btnDelete));
        tblOrderCart.setItems(obList);
    }

    public void btnNewOrderOnAction(ActionEvent actionEvent) {
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        btnAddToCartOnAction(actionEvent);
    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        String id = String.valueOf(cmbCustomerId.getValue());
        try {
            Customer customer = CustomerModel.search(id);
            fillCustomerFields(customer);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillCustomerFields(Customer customer) {
        txtCustomerName.setText(customer.getName());
    }

    public void cmbProductCodeOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbProductCode.getValue());
        try {
            Product product = ProductModel.search(code);
            fillProductFields(product);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillProductFields(Product product) {
        txtDescription.setText(product.getDescription());
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtQtyOnHand.setText(String.valueOf(product.getQtyOnHand()));
    }

    public void btnPlaceOrderCartOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        String customerId = String.valueOf(cmbCustomerId.getValue());

        ArrayList<CartDetail> cartDetails = new ArrayList<>();

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            PlaceOrderTM tm = obList.get(i);
            cartDetails.add(new CartDetail(orderId, tm.getCode(), tm.getQty(), tm.getDescription(), tm.getUnitPrice()));
        }

        PlaceOrder placeOrder = new PlaceOrder(customerId, orderId, cartDetails);
        try {
            boolean isPlaced = PlaceOrderModel.placeOrder(placeOrder);
            if (isPlaced) {
                obList.clear();
                loadNextOrderId();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTotal(){
        tblOrderCart.refresh();;
        double total1 = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total1+= Double.parseDouble(String.valueOf(colTotal.getCellData(i)));
            lblTotal.setText(String.valueOf(total1));
        }
        if (tblOrderCart.getItems().size()==0){
            lblTotal.setText("0.0");
        }
    }


    public void btnPrintReportOnAction(ActionEvent actionEvent) {
    }
}
