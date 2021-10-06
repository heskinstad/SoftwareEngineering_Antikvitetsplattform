package sample.controller;

import javafx.event.ActionEvent;

public class adminController extends homeController {

    public void openNewUser(ActionEvent actionEvent) {
        openNewInterface("../view/listWindow.fxml", "New User", 600, 400);
    }

    public void openOverviewSales(ActionEvent actionEvent) {
        openNewInterface("../view/listWindow.fxml", "Sales overview", 600, 400);
    }

    public void openOverviewShops(ActionEvent actionEvent) {
        openNewInterface("../view/listWindow.fxml", "Shops overview", 600, 400);
    }

    public void openComplaints(ActionEvent actionEvent) {
        openNewInterface("../view/listWindow.fxml", "Complaints", 600, 400);
    }
}
