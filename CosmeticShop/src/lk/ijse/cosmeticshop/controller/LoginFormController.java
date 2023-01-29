package lk.ijse.cosmeticshop.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane pane;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUsername.getText().equals("Admin") && txtPassword.getText().equals("1234")) {
            Navigation.navigate(Routes.ADMIN_DASHBOARD, pane);
        }else if (txtUsername.getText().equals("Owner") && txtPassword.getText().equals("1234")) {
            Navigation.navigate(Routes.OWNER_DASHBOARD, pane);
        }else{
            Alert alert  = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Password");
            alert.setHeaderText("Invalid Username or Password !");
            alert.setContentText("Please Check Your Username and Password , and Try again !");
            alert.showAndWait();
        }
    }
}
