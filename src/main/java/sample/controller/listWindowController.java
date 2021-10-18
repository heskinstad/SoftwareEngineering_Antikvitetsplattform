package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.data.DataHandler;

public class listWindowController {

    @FXML
    TableView tableList;

    @FXML
    public void initialize() {
        //tableList.setItems((ObservableList) DataHandler.lastInnKlage("src/main/resources/JSON/klager.JSON"));
    }



}
