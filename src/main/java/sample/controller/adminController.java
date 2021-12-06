package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerKlage;
import sample.data.DataHandlerSalg;
import sample.model.Butikk;
import sample.model.Klage;
import sample.model.Salg;

import java.util.ArrayList;

public class adminController extends homeController {

    @FXML TableView<Salg> table_SaleHistory;
    @FXML TableColumn<Salg, Integer> col_Salg_Pris;
    @FXML TableView<Klage> table_Complaints;
    @FXML TableView<Butikk> table_shops;
    @FXML Text pengerTjent;

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
            // For å beregne pengene rundes det butikken får alltid ned. Plattformeiers 5 % rundes alltid opp
            tjentePenger += Math.ceil(etSalg.getSolgtVare().getPris() * 0.05);
        }

        pengerTjent.setText(String.valueOf(tjentePenger) + " kr tjent fra butikkers salg (5 %)");
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
