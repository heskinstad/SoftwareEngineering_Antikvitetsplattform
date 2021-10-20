package sample.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.data.DataHandler;
import sample.model.Klage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class listWindowController {

    @FXML TableView table_SaleHistory;
    @FXML TableView<Klage> table_Complaints;
    @FXML TableColumn<Klage,Integer> id;
    @FXML TableColumn<Klage,String> innsender;
    @FXML TableColumn<Klage,String> melding;
    @FXML TableColumn<Klage,String> butikk;
    @FXML TableColumn<Klage,LocalDateTime> tidspunkt;

    @FXML
    public void initialize() {
        refreshKlageListe();
    }

    public void refresh(ActionEvent actionEvent) {
        refreshKlageListe();
    }

    public void changeUser(ActionEvent actionEvent) {

    }

    private void refreshKlageListe() {
        ArrayList<Klage> klager = new ArrayList<Klage>();
        klager.addAll(DataHandler.lastInnKlagerV2("/src/main/resources/JSON/klager.JSON"));
        ObservableList<Klage> klagerObservable = FXCollections.observableArrayList(klager);
        table_Complaints.setItems(klagerObservable);
    }

}
