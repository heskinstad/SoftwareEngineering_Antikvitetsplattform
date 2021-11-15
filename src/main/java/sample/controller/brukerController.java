package sample.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML public TextArea vareBeskrivelse1;
    @FXML public TextArea vareBeskrivelse2;
    @FXML public TextArea vareBeskrivelse3;
    @FXML BorderPane borderPane;
    @FXML
    public ChoiceBox<Butikk> ButikkValgBox;

    public Butikk valgtButikk;
    String path = "/src/main/resources/JSON/varer.JSON";

    @FXML
    public void initialize() {

        Platform.runLater(new Runnable() {
            public void run() {
                Scene scene = borderPane.getScene();
                refreshVarer(scene, 1);
            }
        });

        ArrayList<Butikk> butikkListe = DataHandlerButikk.hentButikker();
        ObservableList<Butikk> observableButikkListe = FXCollections.observableArrayList(butikkListe);
        ButikkValgBox.setItems(observableButikkListe);

        //DataHandlerVare.leggInnVare(new Vare("Saft", "Ille god", "Hei btuikken", 300, "aaa"), "/src/main/resources/JSON/varer.JSON");
        //DataHandlerKlage.leggInnKlage(new Klage(3, "Per", "Dette fungerte ikke", "Heiabutikken", LocalDateTime.now()), "/src/main/resources/JSON/klager.JSON");
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
            Text vareBeskrivelse = (Text) scene.lookup("#vare_Beskrivelse_" + i);


            //sjekker om det er flere varer i varelista
            if (vareArrayStartIndex + i >= varer.size()) {
                if (i == 0 && side != 1) {
                    Text sideTal = (Text) scene.lookup("#sideTal");
                    sideTal.setText(Integer.toString(side - 1));
                    refreshVarer(scene, side - 1);
                    break;
                }
                vareTittel.setText(null);
                vareBeskrivelse.setText(null);
                continue;
            }

            //inserts the data to the window
            vareTittel.setText(varer.get(vareArrayStartIndex + i).getNavn());
            vareBeskrivelse.setText(varer.get(vareArrayStartIndex + i).getBeskrivelse());
            //TODO resolve image insert, first get real urls in the JSON
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

    public void kjopVare1(ActionEvent actionEvent) {
        //openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
        Platform.runLater(new Runnable() {
            public void run() {
                Scene thisScene = borderPane.getScene();

                Text vareTittel = (Text) thisScene.lookup("#vare_navn_0");

                String navnTilVare = vareTittel.getText();
                Vare kjoptVare = new Vare();
                ArrayList<Vare> butikkVareListe = valgtButikk.getVareListe();

                for(Vare vareIListe: butikkVareListe){
                    if(navnTilVare.equals(vareIListe.getNavn()))
                        kjoptVare = vareIListe;

                }

                Salg nyttSalg = new Salg(bruker, valgtButikk, kjoptVare);

                DataHandlerSalg.registrerSalg(nyttSalg);
            }
        });

        Platform.runLater(new Runnable() {
            public void run() {
                Scene scene = borderPane.getScene();
                refreshVarer(scene, 1);
            }
        });
    }

    public void kjopVare2(ActionEvent actionEvent) {
        //openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
        Platform.runLater(new Runnable() {
            public void run() {
                Scene thisScene = borderPane.getScene();

                Text vareTittel = (Text) thisScene.lookup("#vare_navn_1");

                String navnTilVare = vareTittel.getText();
                Vare kjoptVare = new Vare();
                ArrayList<Vare> butikkVareListe = valgtButikk.getVareListe();

                for(Vare vareIListe: butikkVareListe){
                    if(navnTilVare.equals(vareIListe.getNavn()))
                        kjoptVare = vareIListe;

                }

                Salg nyttSalg = new Salg(bruker, valgtButikk, kjoptVare);

                DataHandlerSalg.registrerSalg(nyttSalg);
            }
        });

        Platform.runLater(new Runnable() {
            public void run() {
                Scene scene = borderPane.getScene();
                refreshVarer(scene, 1);
            }
        });
    }

    public void kjopVare3(ActionEvent actionEvent) {
        //openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
        Platform.runLater(new Runnable() {
            public void run() {
                Scene thisScene = borderPane.getScene();

                Text vareTittel = (Text) thisScene.lookup("#vare_navn_2");

                String navnTilVare = vareTittel.getText();
                Vare kjoptVare = new Vare();
                ArrayList<Vare> butikkVareListe = valgtButikk.getVareListe();

                for(Vare vareIListe: butikkVareListe){
                    if(navnTilVare.equals(vareIListe.getNavn()))
                        kjoptVare = vareIListe;

                }

                Salg nyttSalg = new Salg(bruker, valgtButikk, kjoptVare);

                DataHandlerSalg.registrerSalg(nyttSalg);
            }
        });

        Platform.runLater(new Runnable() {
            public void run() {
                Scene scene = borderPane.getScene();
                refreshVarer(scene, 1);
            }
        });
    }

    public void backToLogin(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/sample.fxml", "Complaints", 600, 400);
    }

    public void aapneKlageSkjema(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/klage.fxml", "Klageskjema", 600, 310);
    }

    public void aapneSalgsOversikt(ActionEvent actionEvent){
        openNewInterface(actionEvent, "/view/brukerSalgOversikt.fxml", "Salgsoversikt", 600, 400);
    }


}
