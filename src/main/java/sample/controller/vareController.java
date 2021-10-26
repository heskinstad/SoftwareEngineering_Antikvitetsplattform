package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.data.DataHandlerVare;
import sample.model.Vare;

import java.time.LocalDateTime;

public class vareController extends homeController {

    @FXML
    public TextArea input_beskrivelse;
    @FXML
    public TextField input_navn;
    @FXML
    public TextField input_butikk;
    @FXML
    public TextField input_pris;
    @FXML
    public TextField input_url;

    public void addVare(ActionEvent actionEvent) {
        if (input_beskrivelse.getText().equals("") || input_navn.getText().equals("")
            || input_butikk.getText().equals("") || input_pris.getText().equals("") || input_url.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ett eller flere felter er tomme");
            alert.show();
        }
        else {
            Vare vare = new Vare(1, input_navn.getText(), input_beskrivelse.getText(), input_butikk.getText(),
                    Integer.parseInt(input_pris.getText()), LocalDateTime.now(), input_url.getText());
            DataHandlerVare.leggInnVare(vare, "/src/main/resources/JSON/varer.JSON");
            Alert alert = new Alert(Alert.AlertType.WARNING, "vare lagt til");
            alert.showAndWait();
            avbryt(actionEvent);
        }
    }

    public void avbryt(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/butikkView.fxml", "Butikk Control", 624, 648);
    }
}
