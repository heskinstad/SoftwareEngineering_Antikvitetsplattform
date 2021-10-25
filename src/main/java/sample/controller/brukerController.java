package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.Klage;
import sample.model.Vare;
import sample.data.DataHandlerVare;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class brukerController extends homeController {

    @FXML public Button btnLoginAdmin;
    @FXML public TextField varenavn;
    @FXML public TextArea vareBeskrivelse;


    @FXML
    public void initialize() {
        //Vare vare = DataHandler.lastInnVare("/src/main/java/resources/JSON/varer.JSON");
        //varenavn.setText(vare.getNavn());
        //vareBeskrivelse.setText(vare.getBeskrivelse());

        //DataHandler.leggInnVare(new Vare(3, "Mango", "bla bla bla...", "ikke helt beste butikken...", 1, LocalDateTime.now(), "aaa"), "/src/main/resources/JSON/varer.JSON");
        //DataHandler.leggInnKlage(new Klage(3, "Per", "Dette fungerte ikke", "Heiabutikken", LocalDateTime.now()), "/src/main/resources/JSON/klager.JSON");
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

    public void aapneKlageSkjema(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/klage.fxml", "Klageskjema", 600, 310);
    }


}
