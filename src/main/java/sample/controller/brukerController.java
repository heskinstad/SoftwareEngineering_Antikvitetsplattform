package sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.data.DataHandlerKlage;
import sample.data.DataHandlerVare;
import sample.model.Klage;
import sample.model.Vare;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class brukerController extends homeController {

    @FXML public Button btnLoginAdmin;
    @FXML public TextField varenavn1;
    @FXML public TextField varenavn2;
    @FXML public TextField varenavn3;
    @FXML public TextArea vareBeskrivelse1;
    @FXML public TextArea vareBeskrivelse2;
    @FXML public TextArea vareBeskrivelse3;
    @FXML BorderPane borderPane;

    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            public void run() {
                Scene scene = borderPane.getScene();
                refreshVarer(scene, 1);
            }
        });

        //DataHandlerVare.leggInnVare(new Vare("Saft", "Ille god", "Hei btuikken", 300, "aaa"), "/src/main/resources/JSON/varer.JSON");
        //DataHandlerKlage.leggInnKlage(new Klage(3, "Per", "Dette fungerte ikke", "Heiabutikken", LocalDateTime.now()), "/src/main/resources/JSON/klager.JSON");
    }


    private void refreshVarer(Scene scene, int side) {

        ArrayList<Vare> varer = new ArrayList<>(DataHandlerVare.hentVarer());
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

    public void kjopVare1(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
    }

    public void kjopVare2(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
    }

    public void kjopVare3(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
    }

    public void backToLogin(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Complaints", 600, 400);
    }

    public void aapneKlageSkjema(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/klage.fxml", "Klageskjema", 600, 310);
    }


}
