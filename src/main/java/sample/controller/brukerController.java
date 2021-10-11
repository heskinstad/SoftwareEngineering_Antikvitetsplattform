package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class brukerController extends homeController {


    @FXML
    public Button btnLoginAdmin;

    @FXML
    public void initialize() {
    }


    public void kjopVare1(ActionEvent actionEvent) {
        openNewInterface("../view/sample.fxml", "Bruker Control", 600, 400);
    }

    public void kjopVare2(ActionEvent actionEvent) {
        openNewInterface("../view/sample.fxml", "Bruker Control", 600, 400);
    }

    public void kjopVare3(ActionEvent actionEvent) {
        openNewInterface("../view/sample.fxml", "Bruker Control", 600, 400);
    }

    public void backToLogin(ActionEvent actionEvent) {
        openNewInterface("../view/sample.fxml", "Bruker Control", 600, 400);
    }


}
