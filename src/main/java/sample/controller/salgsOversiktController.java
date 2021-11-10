package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.data.DataHandlerSalg;
import sample.model.Bruker;
import sample.model.Butikk;
import sample.model.Salg;
import sample.model.Vare;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class salgsOversiktController extends homeController {

    @FXML
    TableView<Salg> table_SaleHistory;
    @FXML
    TableColumn<Salg, Bruker> col_Salg_Kjoper;
    @FXML TableColumn<Salg, Butikk> col_Salg_Selger;
    @FXML TableColumn<Salg, Vare> col_Salg_Vare;
    @FXML TableColumn<Salg, LocalDateTime> col_Salg_Tidspunkt;

    @FXML
    public void initialize() {

        refreshBrukerSalgsListe();

    }

    private void refreshBrukerSalgsListe() {
        ArrayList<Salg> salgsListe = new ArrayList<Salg>();
        ArrayList<Salg> alleSalg = DataHandlerSalg.hentSalg();

        for(Salg etSalg: alleSalg){
            if(etSalg.getKjoper().getFornavn().equals(bruker.getFornavn()) && etSalg.getKjoper().getEtternavn().equals(bruker.getEtternavn())) {
                salgsListe.add(etSalg);
            }
        }

        ObservableList<Salg> salgObservableList = FXCollections.observableArrayList(salgsListe);
        table_SaleHistory.setItems(salgObservableList);
    }

    public void forlatOversikt(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/brukerView.fxml", "Bruker Control", 624, 648);
    }
}
