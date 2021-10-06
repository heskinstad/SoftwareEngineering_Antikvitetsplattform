package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {

    @FXML
    private Button btnLoginAdmin;

    @FXML
    public void initialize() {
    }


    public void loginAdmin(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/admin.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Admin Control");
            stage.setScene(new Scene(root, 400, 600));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginButikk(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/butikkView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Butikk Control");
            stage.setScene(new Scene(root, 400, 600));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginBruker(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/sluttbrukerView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Bruker Control");
            stage.setScene(new Scene(root, 624, 648));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
