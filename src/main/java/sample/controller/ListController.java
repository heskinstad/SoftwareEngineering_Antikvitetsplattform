package sample.controller;

import javafx.event.ActionEvent;

public class ListController extends homeController {

    public void toAdmin(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/admin.fxml", "Admin Control", 600, 400);
    }
}
