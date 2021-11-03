package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.data.*;
import sample.model.Butikk;
import sample.model.Klage;
import sample.data.DataHandlerKlage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class listWindowController extends homeController {

    @FXML TableView table_SaleHistory;
    @FXML TableView<Klage> table_Complaints;
    @FXML TableColumn<Klage,Integer> col_Klage_ID;
    @FXML TableColumn<Klage,String> col_Klage_Innsender;
    @FXML TableColumn<Klage,String> col_Klage_Melding;
    @FXML TableColumn<Klage,String> col_Klage_Butikk;
    @FXML TableColumn<Klage,LocalDateTime> col_Klage_Tidspunkt;
    @FXML TableView<Butikk> table_shops;
    @FXML TableColumn<Butikk,String> col_Butikk_Navn;
    @FXML TableColumn<Butikk,String> col_Butikk_Spesialitet;
    @FXML TableColumn<Butikk,String> col_Butikk_DagligLeder;
    @FXML TableColumn<Butikk,String> col_Butikk_Tidspunkt;

    @FXML
    public void initialize() {
        refreshKlageListe();
        refreshButikkListe();
    }

    public void refresh(ActionEvent actionEvent) {
        refreshKlageListe();
        refreshButikkListe();
    }

    public void changeUser(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/sample.fxml", "Antikvitetsplatform", 600, 400);
    }

    private void refreshKlageListe() {
        ArrayList<Klage> klager = new ArrayList<Klage>();
        klager.addAll(DataHandlerKlage.hentKlager());
        ObservableList<Klage> klagerObservable = FXCollections.observableArrayList(klager);
        table_Complaints.setItems(klagerObservable);
    }

    private void refreshButikkListe() {
        ArrayList<Butikk> butikker = new ArrayList<Butikk>();
        butikker.addAll(DataHandlerButikk.hentButikker());
        ObservableList<Butikk> butikkerObservable = FXCollections.observableArrayList(butikker);
        table_shops.setItems(butikkerObservable);
    }

    public void deleteSelectedRow(ActionEvent actionEvent) {
        DataHandlerKlage.slettKlage(table_Complaints.getSelectionModel().getSelectedItem());
        table_Complaints.getItems().removeAll(table_Complaints.getSelectionModel().getSelectedItem());

    }

}
