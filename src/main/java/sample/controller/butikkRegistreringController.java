package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.awt.event.ActionEvent;

public class butikkRegistreringController extends homeController {

    @FXML
    public TextField butikkNavn;
    @FXML
    public TextField butikkSpesialtiet;
    @FXML
    public TextField butikkLeder;

    public void registrerButikk(ActionEvent actionEvent){
        if(butikkNavn.getText().equals("") || butikkSpesialtiet.getText().equals("") || butikkLeder.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ett eller flere tekstfelt er tomme.");
            alert.show();
        }
        else{

        }
    }

}
