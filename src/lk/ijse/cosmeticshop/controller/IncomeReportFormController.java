package lk.ijse.cosmeticshop.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cosmeticshop.db.DBConnection;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class IncomeReportFormController {
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

    public void btnPrintCustomerOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass()
                .getResourceAsStream("/lk/ijse/cosmeticshop/reports/CustomerReport.jrxml");

        try {
            JasperReport jasperReport= JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());


             //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    public void btnPrintEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnPrintProductOnAction(ActionEvent actionEvent) {
    }
}
