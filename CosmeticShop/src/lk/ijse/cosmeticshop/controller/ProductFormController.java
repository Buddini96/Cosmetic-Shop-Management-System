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
import lk.ijse.cosmeticshop.bo.custom.ProductBO;
import lk.ijse.cosmeticshop.dao.custom.ProductDAO;
import lk.ijse.cosmeticshop.dao.custom.impl.ProductDAOImpl;
import lk.ijse.cosmeticshop.model.ProductDTO;
import lk.ijse.cosmeticshop.modelOld.ProductModel;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductFormController implements Initializable {
    public AnchorPane pane;
    public TableView tblProduct;
    public JFXTextField txtSearch;
    public TextField txtProductCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TableColumn colQtyOnHand;
    public TextField txtQtyOnHand;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPrice;

    ObservableList<ProductDTO> proList = FXCollections.observableArrayList();
    ProductBO productBO= (ProductBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Unitprice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        //Search bar
        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) ->{
                    loadAllProducts(newValue);
                });
        loadAllProducts("");
    }

    private void loadAllProducts(String text) {
        ObservableList<ProductDTO> proList = FXCollections.observableArrayList();
        try{
            ArrayList<ProductDTO> productsData = ProductModel.getProductData();
            for (ProductDTO product:productsData){
                if(product.getProductCode().contains(text) || product.getDescription().contains(text)){
                    ProductDTO p = new ProductDTO(product.getProductCode(), product.getDescription(), product.getUnitprice(), product.getQtyOnHand());
                    proList.add(p);
                }
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }

        tblProduct.setItems(proList);
    }


    //form

    public void btnAddOnAction(ActionEvent actionEvent) {
        String code = txtProductCode.getText();
        String description = txtDescription.getText();
        double price = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        //Product product = new Product(code,description,price,qtyOnHand);
        ProductDTO productDTO = new ProductDTO(code,description,price,qtyOnHand);
        try{
            boolean isAdded = productBO.addProduct(productDTO);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Product Added Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Product not Added!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<ProductDTO> products = tblProduct.getItems();
        products.add(productDTO);
        tblProduct.setItems(products);

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try{
            //Product product = new Product(code,description,price,qtyOnHand);
            boolean isUpdated = productBO.updateProduct(new ProductDTO(txtProductCode.getText(), txtDescription.getText(), String.valueOf(Double.parseDouble(txtUnitPrice.getText())), txtQtyOnHand.getText()));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Product Updated Successfully!").show();
                colCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
                colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("Unitprice"));
                colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

                //Search bar
                txtSearch.textProperty()
                        .addListener((observable, oldValue, newValue) ->{
                            loadAllProducts(newValue);
                        });
                loadAllProducts("");

            }else {
                new Alert(Alert.AlertType.WARNING, "Product not Updated!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ObservableList<ProductDTO> currentTableData = tblProduct.getItems();
        String currentProductId = txtProductCode.getText();

        for(ProductDTO product : currentTableData){
            if(product.getProductCode() == currentProductId){
                product.setDescription(txtDescription.getText());
                product.setUnitprice(Double.parseDouble(txtUnitPrice.getText()));
                product.setQtyOnHand(Integer.parseInt(txtQtyOnHand.getText()));

                tblProduct.setItems(currentTableData);
                tblProduct.refresh();
                break;
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtProductCode.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String code = txtProductCode.getText();

        try{
            //Product product = new Product(code,description,price, qtyOnHand);
            boolean isDeleted = productBO.deleteProduct(code);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Product Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Product not Deleted!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        int selectedID = tblProduct.getSelectionModel().getSelectedIndex();
        tblProduct.getItems().remove(selectedID);
    }

    public void rowClicked(MouseEvent mouseEvent) {
        ProductDTO clickedProduct = (ProductDTO) tblProduct.getSelectionModel().getSelectedItem();
        txtProductCode.setText(String.valueOf(clickedProduct.getProductCode()));
        txtDescription.setText(String.valueOf(clickedProduct.getDescription()));
        txtUnitPrice.setText(String.valueOf(clickedProduct.getUnitprice()));
        txtQtyOnHand.setText(String.valueOf(clickedProduct.getQtyOnHand()));
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String code = txtSearch.getText();
        try{
            ProductDAO productDAO = new ProductDAOImpl();
            ProductDTO search = productDAO.search(code);
            if (search != null){
                fillData(search);
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void txtProductCodeOnAction(ActionEvent actionEvent) {
        String code = txtProductCode.getText();
        try {
            ProductDTO product = productBO.searchProduct(code);
            if (product != null){
                fillData(product);
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void fillData(ProductDTO product){
        txtProductCode.setText(product.getProductCode());
        txtDescription.setText(product.getDescription());
        txtUnitPrice.setText(String.valueOf(product.getUnitprice()));
        txtQtyOnHand.setText(String.valueOf(product.getQtyOnHand()));

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

    public void OwnerDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.OWNER_DASHBOARD, pane);
    }

}
