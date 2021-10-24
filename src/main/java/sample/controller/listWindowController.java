package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.data.DataHandler;
import sample.model.Klage;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class listWindowController extends homeController {

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
        openNewInterface(actionEvent, "../view/sample.fxml", "Antikvitetsplatform", 600, 400);
    }

    private void refreshKlageListe() {
        ArrayList<Klage> klager = new ArrayList<Klage>();
        klager.addAll(DataHandler.hentKlager("/src/main/resources/JSON/klager.JSON"));
        ObservableList<Klage> klagerObservable = FXCollections.observableArrayList(klager);
        table_Complaints.setItems(klagerObservable);
    }

}
