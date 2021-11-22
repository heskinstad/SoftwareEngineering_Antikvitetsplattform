package sample.controller;

import javafx.event.ActionEvent;

public class adminController extends homeController {



    public void openNewUser(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/adminView.fxml", "New User", 600, 400);
    }

    public void openOverviewSales(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/adminView.fxml", "Sales overview", 600, 400);
    }

    public void openOverviewShops(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/adminView.fxml", "Shops overview", 600, 400);
    }

    public void openComplaints(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/adminView.fxml", "Complaints", 600, 400);
    }

    public void changeUser(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/sample.fxml", "Antikvitetsplatform", 700, 500);
    }
}
