package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Vare;
import sample.data.DataHandler_varer;

import java.io.IOException;

public class brukerController extends homeController {


    @FXML
    public Button btnLoginAdmin;
    @FXML
    public TextField varenavn;
    public TextArea vareBeskrivelse;

    @FXML
    public void initialize() {
        Vare vare = DataHandler_varer.lastInnVare();
        varenavn.setText(vare.getNavn());
        vareBeskrivelse.setText(vare.getBeskrivelse());
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


}
