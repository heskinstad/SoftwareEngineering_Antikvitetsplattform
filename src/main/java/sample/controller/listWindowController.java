package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import sample.data.DataHandler;

public class listWindowController {

    @FXML
    TableView table_SaleHistory;
    @FXML
    TableView table_Complaints;

    @FXML
    public void initialize() {
        table_Complaints.setItems((ObservableList) DataHandler.lastInnKlager("src/main/resources/JSON/klager.JSON"));
    }

    public void refresh(ActionEvent actionEvent) {
        table_Complaints.setItems((ObservableList) DataHandler.lastInnKlager("src/main/resources/JSON/klager.JSON"));
    }

    public void changeUser(ActionEvent actionEvent) {

    }



}
