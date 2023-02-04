package lk.ijse.cosmeticshop.controller;

/*
    @author BUDDINI
    @created 12/2/2022 - 8:27 PM   
*/


import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;

import java.io.IOException;

public class OwnerDashboardFormController {
    public AnchorPane pane;

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
