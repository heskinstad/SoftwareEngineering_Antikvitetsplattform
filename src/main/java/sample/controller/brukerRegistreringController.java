package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.model.Bruker;
import sample.data.DataHandlerBruker;

import java.util.ArrayList;
import java.util.Objects;

import static sample.data.DataHandlerBruker.oppdaterSisteInnlogging;

public class brukerRegistreringController extends homeController {

    @FXML
    TextField registrerFornavn;
    @FXML
    TextField registrerEtternavn;
    @FXML
    TextField loggInnFornavn;
    @FXML
    TextField loggInnEtternavn;

    //TODO: fikse at brukeren man logger inn/registrerer blir satt som nåværende bruker i neste interface

    public void registrerNyBruker(ActionEvent actionEvent) {
        String fornavn = registrerFornavn.getText();
        String etternavn = registrerEtternavn.getText();
        if (Objects.equals(fornavn, "") || Objects.equals(etternavn, "")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Et eller flere felter er tomme!");
            alert.showAndWait();
            return;
        }
        bruker = new Bruker(fornavn, etternavn);
        ArrayList<Bruker> brukere = DataHandlerBruker.hentBrukere();
        if (!brukere.isEmpty()) {
            for (int i = 0; i < brukere.size(); i++) {
                if (brukere.get(i).getFornavn().equals(fornavn) && Objects.equals(brukere.get(i).getEtternavn(), etternavn)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Bruker allerede registrert!");
                    alert.showAndWait();
                    return;
                }
            }
        }
        DataHandlerBruker.leggInnBruker(bruker);
        openNewInterface(actionEvent, "../view/brukerView.fxml", "Bruker Control - " + bruker.getFornavn() + " " + bruker.getEtternavn(), 624, 648);
    }

    public void loggInnBruker(ActionEvent actionEvent) {
        String fornavn = loggInnFornavn.getText();
        String etternavn = loggInnEtternavn.getText();
        if (Objects.equals(fornavn, "") || Objects.equals(etternavn, "")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Et eller flere felter er tomme!");
            alert.showAndWait();
            return;
        }
        bruker = new Bruker(fornavn, etternavn);
        ArrayList<Bruker> brukere = DataHandlerBruker.hentBrukere();

        for (int i = 0; i < brukere.size(); i++) {
            if (Objects.equals(brukere.get(i).getFornavn(), fornavn) && Objects.equals(brukere.get(i).getEtternavn(), etternavn)) {
                oppdaterSisteInnlogging(bruker);
                openNewInterface(actionEvent, "../view/brukerView.fxml", "Bruker Control - " + bruker.getFornavn() + " " + bruker.getEtternavn(), 624, 648);
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.WARNING, "Bruker eksisterer ikke!");
        alert.showAndWait();
    }

}
