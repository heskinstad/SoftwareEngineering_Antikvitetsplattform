package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.model.Bruker;
import sample.model.Butikk;

import java.io.IOException;

public class homeController {

    static Bruker bruker;
    static Butikk butikk;

    @FXML
    public Button btnLoginAdmin;

    @FXML
    public void initialize() {
        bruker = null;
        butikk = null;
    }

    public void loginAdmin(ActionEvent actionEvent) { openListWindowView(actionEvent); }

    public void loginButikk(ActionEvent actionEvent) { openButikkRegistreringView(actionEvent); }

    public void loginBruker(ActionEvent actionEvent) { openBrukerRegistreringView(actionEvent); }

    /*
        Opens a new window defined by the parameters given
     */
    private void openNewInterface(ActionEvent actionEvent, String path, String windowTitle, int width, int height) {
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

    public void openHomeView(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/homeView.fxml", "Antikvitetsplatform", 600, 400);
    }

    public void openAddVareView(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/addVareView.fxml", "Legg ny vare ut for salg", 610, 492);
    }

    public void openBrukerView(ActionEvent actionEvent, String windowTitle) {
        openNewInterface(actionEvent, "/view/brukerView.fxml", windowTitle, 676, 624);
    }

    public void openBrukerRegistreringView(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/brukerRegistreringView.fxml", "Brukerregistrering", 624, 648);
    }

    public void openBrukerKjoepsOversiktView(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/brukerKjoepsOversiktView.fxml", "Salgsoversikt", 600, 400);
    }

    public void openButikkView(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/butikkView.fxml", "Butikkside", 616, 680);
    }

    public void openButikkRegistreringView(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/butikkRegistreringView.fxml", "Butikkregistrering", 600, 400);
    }

    public void openButikkSalgsOversiktView(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/butikkSalgsOversiktView.fxml", "Salgsoversikt", 700, 500);
    }

    public void openKlageView(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/klageView.fxml", "Klageskjema", 600, 310);
    }

    public void openListWindowView(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/adminView.fxml", "Administratorside", 850, 550);
    }
}
