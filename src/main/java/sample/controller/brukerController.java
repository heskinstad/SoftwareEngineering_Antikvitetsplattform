package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;

public class brukerController extends homeController {

    @FXML
    public void initialize() {
    }

    public void kjopVare1(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
    }

    public void kjopVare2(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
    }

    public void kjopVare3(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
    }

    public void backToLogin(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
    }

    @FXML
    private TextArea textArea;

}
