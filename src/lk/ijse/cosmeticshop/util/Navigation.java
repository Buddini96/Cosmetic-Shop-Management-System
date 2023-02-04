package lk.ijse.cosmeticshop.util;

/*
    @author BUDDINI
    @created 11/24/2022 - 7:10 PM   
*/

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route){
            case HOME:
                initUI("HomeForm.fxml");
                break;

            case LOGIN:
                initUI("LoginForm.fxml");
                break;

            case ADMIN_DASHBOARD:
                initUI("AdminDashboardForm.fxml");
                break;

            case OWNER_DASHBOARD:
                initUI("OwnerDashboardForm.fxml");
                break;

            case CUSTOMER:
                initUI("CustomerForm.fxml");
                break;

            case PRODUCT:
                initUI("ProductForm.fxml");
                break;

            case DELIVERY:
                initUI("DeliveryForm.fxml");
                break;

            case EMPLOYEE:
                initUI("EmployeeForm.fxml");
                break;

            case ORDER:
                initUI("OrderForm.fxml");
                break;

            case PRODUCT_ADMIN:
                initUI("ProductFormAdmin.fxml");
                break;

            case SUPPLIER:
                initUI("SupplierForm.fxml");
                break;

            case INCOME_REPORT:
                initUI("IncomeReportForm.fxml");
                break;

            case PLACE_ORDER:
                initUI("PlaceOrderForm.fxml");
                break;

            case PLACE_ORDER_NEW:
                initUI("PlaceOrderFormNew.fxml");
                break;

            default:
                new Alert(Alert.AlertType.ERROR,"Not Suitable UI found!").show();

        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/cosmeticshop/view/" + location)));
    }
}
