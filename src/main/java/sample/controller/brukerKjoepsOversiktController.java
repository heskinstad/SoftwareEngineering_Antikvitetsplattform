package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.data.DataHandlerSalg;
import sample.model.Butikk;
import sample.model.Salg;
import sample.model.Vare;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class brukerKjoepsOversiktController extends homeController {

    @FXML
    TableView<Salg> table_SaleHistory;
    @FXML TableColumn<Salg, String> col_Salg_Selger;
    @FXML TableColumn<Salg, Vare> col_Salg_Vare;
    @FXML TableColumn<Salg, Integer> col_Salg_Pris;
    @FXML TableColumn<Salg, LocalDateTime> col_Salg_Tidspunkt;

    @FXML
    public void initialize() {

        refreshBrukerSalgsListe();
        col_Salg_Pris.setCellValueFactory(tf -> tf.getValue().getSolgtVare().prisProperty());

    }

    private void refreshBrukerSalgsListe() {
        ArrayList<Salg> salgsListe = new ArrayList<Salg>();
        ArrayList<Salg> alleSalg = DataHandlerSalg.hentSalg();

        for(Salg etSalg: alleSalg){
            if(etSalg.getKjoper().equals(bruker.getFornavn() + " " + bruker.getEtternavn())) {
                salgsListe.add(etSalg);
            }
        }

        ObservableList<Salg> salgObservableList = FXCollections.observableArrayList(salgsListe);
        table_SaleHistory.setItems(salgObservableList);
    }

    public void forlatOversikt(ActionEvent actionEvent) {
        openBrukerView(actionEvent, "Bruker Control - " + bruker.getFornavn() + " " + bruker.getEtternavn());
    }
}
