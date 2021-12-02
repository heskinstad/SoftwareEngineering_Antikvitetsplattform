package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import sample.data.*;
import sample.model.*;
import sample.data.DataHandlerKlage;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class adminController extends homeController {

    @FXML TableView<Salg> table_SaleHistory;
    @FXML TableColumn<Salg,String> col_Salg_Kjoper;
    @FXML TableColumn<Salg,String> col_Salg_Selger;
    @FXML TableColumn<Salg,Vare> col_Salg_SolgtVare;
    @FXML TableColumn<Salg,LocalDateTime> col_Salg_Tidspunkt;
    @FXML TableColumn<Salg, Integer> col_Salg_Pris;
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
    Text pengerTjent;

    @FXML
    public void initialize() {
        refreshSalgsListe();
        refreshKlageListe();
        refreshButikkListe();
    }

    public void refresh(ActionEvent actionEvent) {
        refreshSalgsListe();
        refreshKlageListe();
        refreshButikkListe();
    }

    public void changeUser(ActionEvent actionEvent) {
        openHomeView(actionEvent);
    }

    private void refreshSalgsListe() {
        ArrayList<Salg> salgsListe = new ArrayList<Salg>();
        salgsListe.addAll(DataHandlerSalg.hentSalg());
        ObservableList<Salg> salgObservableList = FXCollections.observableArrayList(salgsListe);
        table_SaleHistory.setItems(salgObservableList);
        col_Salg_Pris.setCellValueFactory(tf -> tf.getValue().getSolgtVare().prisProperty());

        refreshPengerTjent();
    }

    private void refreshPengerTjent() {
        ArrayList<Salg> alleSalg = DataHandlerSalg.hentSalg();
        int tjentePenger = 0;

        for(Salg etSalg : alleSalg){
            tjentePenger += etSalg.getSolgtVare().getPris() * 0.05;
        }

        pengerTjent.setText(String.valueOf(tjentePenger) + " kr tjent fra butikkers salg.");
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
