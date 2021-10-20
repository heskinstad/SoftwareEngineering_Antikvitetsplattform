package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.model.Klage;
import sample.model.Vare;
import sample.data.DataHandler;

import java.time.LocalDateTime;

public class brukerController extends homeController {

    @FXML
    public Button btnLoginAdmin;
    @FXML
    public TextField varenavn;
    @FXML
    public TextArea vareBeskrivelse;

    @FXML
    public void initialize() {
        //Vare vare = DataHandler_varer.lastInnVare("/src/main/java/resources/JSON/varer.JSON");
        //varenavn.setText(vare.getNavn());
        //vareBeskrivelse.setText(vare.getBeskrivelse());

        DataHandler.leggInnVare(new Vare(3, "test2", "bla bla bla...", "ikke helt beste butikken...", 1, LocalDateTime.now(), "aaa"), "/src/main/resources/JSON/varer.JSON");
        DataHandler.leggInnKlage(new Klage(3, "Thomas", "Dette fungerte ikke", "Heiabutikken", LocalDateTime.now()), "/src/main/resources/JSON/klager.JSON");
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
