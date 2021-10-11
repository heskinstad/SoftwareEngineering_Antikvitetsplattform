package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class adminController extends homeController {

    public void openNewUser(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/listWindow.fxml", "New User", 600, 400);
    }

    public void openOverviewSales(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/listWindow.fxml", "Sales overview", 600, 400);
    }

    public void openOverviewShops(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/listWindow.fxml", "Shops overview", 600, 400);
    }

    public void openComplaints(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/listWindow.fxml", "Complaints", 600, 400);
    }
}
