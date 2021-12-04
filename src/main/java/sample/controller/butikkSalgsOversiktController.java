package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import sample.data.DataHandlerSalg;
import sample.model.Bruker;
import sample.model.Salg;
import sample.model.Vare;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class butikkSalgsOversiktController extends homeController {
    @FXML
    TableView<Salg> table_SaleHistory;
    @FXML
    TableColumn<Salg, String> col_Salg_Kjoper;
    @FXML TableColumn<Salg, Vare> col_Salg_Vare;
    @FXML TableColumn<Salg, Integer> col_Salg_Pris;
    @FXML TableColumn<Salg, LocalDateTime> col_Salg_Tidspunkt;
    @FXML
    Text pengerTjentText;

    @FXML
    public void initialize() {

        refreshButikkSalgsListe();
        int tjentePenger = kalkulerTotalPengeTjent();
        pengerTjentText.setText(String.valueOf(tjentePenger) + " kr tjent fra salg (95 %)");
        col_Salg_Pris.setCellValueFactory(tf -> tf.getValue().getSolgtVare().prisProperty());

    }

    private void refreshButikkSalgsListe() {
        ArrayList<Salg> salgsListe = new ArrayList<Salg>();
        ArrayList<Salg> alleSalg = DataHandlerSalg.hentSalg();

        for(Salg etSalg: alleSalg){
            if(etSalg.getSelger().equals(butikk.getNavn())) {
                salgsListe.add(etSalg);
            }
        }

        ObservableList<Salg> salgObservableList = FXCollections.observableArrayList(salgsListe);
        table_SaleHistory.setItems(salgObservableList);
    }

    private int kalkulerTotalPengeTjent(){
        ArrayList<Salg> alleSalg = DataHandlerSalg.hentSalg();
        int tjentePenger = 0;

        for(Salg etSalg : alleSalg){
            if(etSalg.getSelger().equals(butikk.getNavn())){
                tjentePenger += Math.floor(etSalg.getSolgtVare().getPris() * 0.95);
            }
        }

        return tjentePenger;
    }

    public void forlatOversikt(ActionEvent actionEvent) { openButikkView(actionEvent); }
}
