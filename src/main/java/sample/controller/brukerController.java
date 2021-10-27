package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.data.DataHandlerKlage;
import sample.data.DataHandlerVare;
import sample.model.Klage;
import sample.model.Vare;

import java.util.ArrayList;

public class brukerController extends homeController {

    @FXML public Button btnLoginAdmin;
    @FXML public TextField varenavn1;
    @FXML public TextField varenavn2;
    @FXML public TextField varenavn3;
    @FXML public TextArea vareBeskrivelse1;
    @FXML public TextArea vareBeskrivelse2;
    @FXML public TextArea vareBeskrivelse3;


    @FXML
    public void initialize() {
        ArrayList<Vare> varer = DataHandlerVare.hentVarer();

        for (int i=0; i<3; i++) {
            varer.get(i).getId().toString();
            varenavn1.setText(varer.get(i).getNavn());
            varenavn2.setText(varer.get(i).getNavn());
            varenavn3.setText(varer.get(i).getNavn());
        }

        //varenavn1.setText(varer.get(0).getNavn());
        //varenavn2.setText(varer.get(1).getNavn());
        //varenavn3.setText(varer.get(2).getNavn());

        vareBeskrivelse1.setText(varer.get(0).getBeskrivelse());
        vareBeskrivelse2.setText(varer.get(1).getBeskrivelse());
        vareBeskrivelse3.setText(varer.get(2).getBeskrivelse());



        //DataHandlerVare.leggInnVare(new Vare("Mango", "bla bla bla...", "ikke helt beste butikken...", 1, "aaa"));
        //DataHandler.leggInnKlage(new Klage(3, "Per", "Dette fungerte ikke", "Heiabutikken", LocalDateTime.now()));
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
