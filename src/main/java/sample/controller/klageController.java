package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.data.DataHandlerKlage;
import sample.model.Klage;

import java.time.LocalDateTime;

public class klageController extends homeController {

    @FXML
    public TextArea input_Melding;
    @FXML
    public TextField input_Navn;
    @FXML
    public TextField input_Butikk;

    @FXML
    public void initialize() {

    }

    public void sendKlage(ActionEvent actionEvent) {
        if (input_Melding.getText().equals("") || input_Navn.getText().equals("") || input_Butikk.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ett eller flere felter er tomme");
            alert.show();
        }
        else {
            Klage klage = new Klage(1, input_Navn.getText(), input_Melding.getText(), input_Butikk.getText(), LocalDateTime.now());
            DataHandlerKlage.leggInnKlage(klage, "/src/main/resources/JSON/klager.JSON");
            Alert alert = new Alert(Alert.AlertType.WARNING, "Klage sendt");
            alert.showAndWait();
            avbryt(actionEvent);
        }
    }

    public void avbryt(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/brukerView.fxml", "Bruker Control", 624, 648);
    }

}
