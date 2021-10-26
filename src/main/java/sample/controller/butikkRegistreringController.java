package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.data.DataHandlerButikk;
import sample.model.Butikk;

import java.time.LocalDateTime;

public class butikkRegistreringController extends homeController {

    @FXML
    public TextField butikkNavn;
    @FXML
    public TextField butikkSpesialtiet;
    @FXML
    public TextField butikkLeder;

    public void registrerButikk(javafx.event.ActionEvent actionEvent){
        if(butikkNavn.getText().equals("") || butikkSpesialtiet.getText().equals("") || butikkLeder.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ett eller flere tekstfelt er tomme.");
            alert.show();
        }
        else{
            Butikk butikk = new Butikk(2, butikkNavn.getText(), butikkSpesialtiet.getText(), butikkLeder.getText(), LocalDateTime.now());
            DataHandlerButikk.registrerButikk(butikk,"/src/main/resources/JSON/butikker.JSON" );
            Alert alert = new Alert(Alert.AlertType.WARNING, "Din butikk har blitt registret");
            alert.showAndWait();
            avbryt(actionEvent);

        }
    }

    public void avbryt(ActionEvent actionEvent){
        openNewInterface(actionEvent, "../view/butikkView.fxml","Butikk Control", 624, 648);
    }

}
