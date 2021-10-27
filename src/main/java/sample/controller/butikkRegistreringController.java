package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.data.DataHandlerButikk;
import sample.model.Butikk;

import java.util.ArrayList;

public class butikkRegistreringController extends homeController {

    @FXML
    public TextField butikkNavn;
    @FXML
    public TextField butikkSpesialtiet;
    @FXML
    public TextField butikkLeder;
    @FXML
    public Button registrerButikk;
    @FXML
    public Button butikkLoggInn;
    @FXML
    public ChoiceBox<Butikk> velgButikkSlide;

    @FXML
    public void initialize(){
        ArrayList<Butikk> registrerteButikker = DataHandlerButikk.hentButikker();
        ObservableList<Butikk> listeMedButikker = FXCollections.observableArrayList(registrerteButikker);
        velgButikkSlide.setItems(listeMedButikker);
    }

    public void trykkForRegistrering(ActionEvent actionEvent){
        if(butikkNavn.getText().equals("") || butikkSpesialtiet.getText().equals("") || butikkLeder.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ett eller flere tekstfelt er tomme.");
            alert.show();
        }
        else{
            Butikk butikk = new Butikk(butikkNavn.getText(), butikkSpesialtiet.getText(), butikkLeder.getText());
            DataHandlerButikk.registrerButikk(butikk);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Butikk er registrert");
            alert.showAndWait();
            DataHandlerButikk.setValgtButikk(butikk);
            avbryt(actionEvent);
        }
    }

    public void avbryt(javafx.event.ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/butikkView.fxml", "Butikk controll", 624, 648);
    }

    public void trykkForLoggInn(ActionEvent actionEvent){
        Butikk valgtButikk = velgButikkSlide.getValue();
        DataHandlerButikk.setValgtButikk(valgtButikk);
        avbryt(actionEvent);
    }

}
