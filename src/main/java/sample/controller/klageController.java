package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerKlage;
import sample.model.Butikk;
import sample.model.Klage;

import java.util.ArrayList;

public class klageController extends homeController {

    @FXML
    public TextArea input_Melding;
    @FXML
    public ChoiceBox input_Butikk;

    @FXML
    public void initialize() {
        ArrayList<Butikk> registrerteButikker = DataHandlerButikk.hentButikker();
        ObservableList<Butikk> listeMedButikker = FXCollections.observableArrayList(registrerteButikker);
        input_Butikk.setItems(listeMedButikker);
    }

    public void sendKlage(ActionEvent actionEvent) {
        if (input_Melding.getText().equals("") || input_Butikk == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ett eller flere felter er tomme");
            alert.show();
        }
        else {
            Klage klage = new Klage(bruker.getFornavn() + " " + bruker.getEtternavn(), input_Melding.getText(), input_Butikk.getValue().toString());
            DataHandlerKlage.leggInnKlage(klage);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Klage sendt");
            alert.showAndWait();
            avbryt(actionEvent);
        }
    }

    public void avbryt(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/brukerView.fxml", "Bruker Control", 676, 624);
    }

}
