package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {

    @FXML
    public Button btnLoginAdmin;

    @FXML
    public void initialize() {
    }

    public void loginAdmin(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/admin.fxml", "Admin Control", 600, 400);
    }

    public void loginButikk(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/butikkView.fxml", "Butikk Control", 400, 600);
    }

    public void loginBruker(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sluttbrukerView.fxml", "Bruker Control", 624, 648);
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
}
