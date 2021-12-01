package sample.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerSalg;
import sample.data.DataHandlerVare;
import sample.model.*;

import java.util.ArrayList;

public class brukerController extends homeController {

    @FXML public Button btnLoginAdmin;
    @FXML public TextField varenavn0;
    @FXML public TextField varenavn1;
    @FXML public TextField varenavn3;
    @FXML public Text vare_pris_0;
    @FXML public Text vare_pris_1;
    @FXML public Text vare_pris_2;
    @FXML public TextArea vareBeskrivelse1;
    @FXML public TextArea vareBeskrivelse2;
    @FXML public TextArea vareBeskrivelse3;
    @FXML BorderPane borderPane;
    @FXML public ChoiceBox<Butikk> ButikkValgBox;
    @FXML public ImageView vare_bilde_0;
    @FXML public ImageView vare_bilde_1;
    @FXML public ImageView vare_bilde_2;

    public Butikk valgtButikk;
    String path = "/src/main/resources/JSON/varer.JSON";

    @FXML
    public void initialize() {


        ArrayList<Butikk> butikkListe = DataHandlerButikk.hentButikker();
        ObservableList<Butikk> observableButikkListe = FXCollections.observableArrayList(butikkListe);
        ButikkValgBox.setItems(observableButikkListe);
        ButikkValgBox.getSelectionModel().selectFirst();

        Platform.runLater(new Runnable() {
            public void run() {
                butikkValgt();
            }
        });
    }

    private void refreshVarer(Scene scene, int side) {
        ArrayList<Vare> varer;
        try{
             varer = new ArrayList<>(valgtButikk.getVareListe());
        } catch (Exception e) {
             varer = new ArrayList<>(DataHandlerVare.hentVarer(path));
        }

        for (int i = 0; i < 3; i++) {
            int vareArrayStartIndex = getTrueVareArrayStartIndex(side);

            Text vareTittel = (Text) scene.lookup("#vare_navn_" + i);
            Text vareButikk = (Text) scene.lookup("#vare_butikk_" + i);
            Text varePris = (Text) scene.lookup("#vare_pris_" + i);
            Text vareBeskrivelse = (Text) scene.lookup("#vare_Beskrivelse_" + i);
            ImageView vareBilde = (ImageView) scene.lookup("#vare_bilde_" + i);

            //sjekker om det er flere varer i varelista
            if (vareArrayStartIndex + i >= varer.size()) {
                if (i == 0 && side != 1) {
                    Text sideTal = (Text) scene.lookup("#sideTal");
                    sideTal.setText(Integer.toString(side - 1));
                    refreshVarer(scene, side - 1);
                    break;
                }
                vareTittel.setText(null);
                vareButikk.setText(null);
                varePris.setText(null);
                vareBeskrivelse.setText(null);
                vareBilde.setImage(null);

                continue;
            }

            //inserts the data to the window
            vareTittel.setText(varer.get(vareArrayStartIndex + i).getNavn());
            vareButikk.setText(varer.get(vareArrayStartIndex + i).getButikk());
            varePris.setText("Pris: " + varer.get(vareArrayStartIndex + i).getPris() + " kr");
            vareBeskrivelse.setText(varer.get(vareArrayStartIndex + i).getBeskrivelse());

            //inserts image from path in JSON file
            try {
                vareBilde.setImage(DataHandlerVare.hentVareBilde(varer.get(vareArrayStartIndex + i).getBildeURL()));
            }
            catch (Exception e) {
                System.out.println("Kunne ikke laste inn bilde");
            }
        }
    }

    private int getTrueVareArrayStartIndex(int side){
        return (side - 1) * 3;
    }

    public void forrigeSide(ActionEvent actionEvent){
        //knap for å vise dei 4 neste varene
        Scene scene = borderPane.getScene();
        Text txtSide = (Text) scene.lookup("#sideTal");
        int side = Integer.parseInt(txtSide.getText());

        if(side > 1){
            txtSide.setText(Integer.toString(side - 1));
            refreshVarer(scene, side - 1);

        }
    }
    public void nesteSide(ActionEvent actionEvent){
        //knapp for å vise dei 4 forrige varene
        Scene scene = borderPane.getScene();
        Text txtSide = (Text) scene.lookup("#sideTal");
        int side = Integer.parseInt(txtSide.getText());

        txtSide.setText(Integer.toString(side + 1));
        refreshVarer(scene, side + 1);

    }

    public void butikkValgt(){
        valgtButikk = ButikkValgBox.getValue();
        valgtButikk.setVarerIButikk();

        Scene scene = borderPane.getScene();
        refreshVarer(scene, 1);
    }

    public void kjopVare(int i) {

        Scene thisScene = borderPane.getScene();
        Text vareTittel = (Text) thisScene.lookup("#vare_navn_" + i);

        String navnTilVare = vareTittel.getText();
        Vare kjoptVare = new Vare();
        ArrayList<Vare> butikkVareListe = valgtButikk.getVareListe();

        for(Vare vareIListe: butikkVareListe){
            if(navnTilVare.equals(vareIListe.getNavn()))
                kjoptVare = vareIListe;
        }

        Salg nyttSalg = new Salg(bruker, valgtButikk, kjoptVare);
        DataHandlerSalg.registrerSalg(nyttSalg);

        Scene scene = borderPane.getScene();
        refreshVarer(scene, 1);

    }

    public void kjopVare0(ActionEvent actionEvent) {
        kjopVare(0);
    }

    public void kjopVare1(ActionEvent actionEvent) {
        kjopVare(1);
    }

    public void kjopVare2(ActionEvent actionEvent) {
        kjopVare(2);
    }

    public void backToLogin(ActionEvent actionEvent) { openHomeView(actionEvent); }

    public void aapneKlageSkjema(ActionEvent actionEvent) { openKlageView(actionEvent); }

    public void aapneSalgsOversikt(ActionEvent actionEvent) { openBrukerKjoepsOversiktView(actionEvent); }


}
