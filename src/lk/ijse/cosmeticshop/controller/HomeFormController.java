package lk.ijse.cosmeticshop.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cosmeticshop.util.Navigation;
import lk.ijse.cosmeticshop.util.Routes;

import java.io.IOException;

public class HomeFormController {
    public AnchorPane pane;

    public void btnHomeOnAction(ActionEvent actionEvent) {
    }

    public void btnAboutOnAction(ActionEvent actionEvent) {
    }

    public void btnContactOnAction(ActionEvent actionEvent) {
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnStartOnAcction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane);
    }
}
