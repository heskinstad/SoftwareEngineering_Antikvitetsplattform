package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.data.DataHandlerBruker;
import sample.model.Bruker;

import java.io.IOException;
import java.util.ArrayList;

public class homeController {

    @FXML
    public Button btnLoginAdmin;

    @FXML
    public void initialize() {
    }

    public void loginAdmin(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/listWindow.fxml", "Admincontroller", 600, 400);
    }

    public void loginButikk(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/butikkRegistrering.fxml", "Butikkregistrering", 600, 400);
    }

    public void loginBruker(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/brukerRegistrering.fxml", "Brukerregistrering", 624, 648);
    }

    /*
        Opens a new window defined by the parameters given
     */
    public void openNewInterface(ActionEvent actionEvent, String path, String windowTitle, int width, int height) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(root, width, height));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void hentBrukere() {}
        ArrayList<Bruker> brukere = DataHandlerBruker.hentBrukere();
}
