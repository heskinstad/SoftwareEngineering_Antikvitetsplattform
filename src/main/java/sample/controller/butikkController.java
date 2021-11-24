package sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.model.Butikk;
import sample.model.Vare;

import java.util.ArrayList;


public class butikkController extends homeController {

    private boolean isEditDesc = false; // verdi for å sjekke om editMode på beskrivelse er aktivt
    @FXML
    AnchorPane anchorPane;
    @FXML
    public Text butikkNavn;
    @FXML
    public TextArea beskrivelseText;

    public Butikk valgtButikk;

    @FXML
    public void initialize() {
        valgtButikk = butikk;

        butikkNavn.setText(valgtButikk.getNavn());
        beskrivelseText.setText(valgtButikk.getBeskrivelse());

        valgtButikk.setVarerIButikk();

        Platform.runLater(new Runnable() {
            public void run() {
                Scene scene = anchorPane.getScene();
                refreshVarer(scene, 1);
            }
        });
    }

    private void refreshVarer(Scene scene, int side) {

        ArrayList<Vare> varer = new ArrayList<>(valgtButikk.getVareListe());
        for (int i = 0; i < 4; i++) {
            int vareArrayStartIndex = getTrueVareArrayStartIndex(side);

            Text vareTittel = (Text) scene.lookup("#vare_tittel_" + i);
            Text vareBeskrivelse = (Text) scene.lookup("#vare_beskrivelse_" + i);
            ImageView vareURL = (ImageView) scene.lookup("#vare_url_" + i);

            //sjekker om det ikkje er flere varer i varelista
            if (vareArrayStartIndex + i >= varer.size()) {
                // sjekker om siden vi er på blir tom
                if (i == 0 && side != 1) {
                    Text sideTal = (Text) scene.lookup("#sideTal");
                    sideTal.setText(Integer.toString(side - 1));
                    refreshVarer(scene, side - 1);
                    break;
                }
                vareTittel.setText(null);
                vareBeskrivelse.setText(null);
                vareURL.setImage(null);
                continue;
            }

            //inserts the data to the window
            vareTittel.setText(varer.get(vareArrayStartIndex + i).getNavn());
            vareBeskrivelse.setText(varer.get(vareArrayStartIndex + i).getBeskrivelse());
            //TODO resolve image insert, first get real urls in the JSON
            vareURL.setImage(null);
        }
    }

    private int getTrueVareArrayStartIndex(int side) {
        return (side - 1) * 4;
    }

    public void forrigeSide(ActionEvent actionEvent) {
        //knap for å vise dei 4 neste varene
        Scene scene = anchorPane.getScene();
        Text txtSide = (Text) scene.lookup("#sideTal");
        int side = Integer.parseInt(txtSide.getText());

        if (side > 1) {
            txtSide.setText(Integer.toString(side - 1));
            refreshVarer(scene, side - 1);

        }
    }

    public void nesteSide(ActionEvent actionEvent) {
        //knapp for å vise dei 4 forrige varene
        Scene scene = anchorPane.getScene();
        Text txtSide = (Text) scene.lookup("#sideTal");
        int side = Integer.parseInt(txtSide.getText());

        txtSide.setText(Integer.toString(side + 1));
        refreshVarer(scene, side + 1);
    }

    public void editDesc(ActionEvent actionEvent) {

        //TODO save changes
        Scene scene = anchorPane.getScene();
        Button btnEditDesc = (Button) scene.lookup("#btnEditDesc");
        TextArea txtDesc = (TextArea) scene.lookup("#txtDesc");

        if (!isEditDesc) {
            btnEditDesc.setText("save");
            txtDesc.setEditable(true);
        } else {
            String textTemp = txtDesc.getText();
            btnEditDesc.setText("edit");
            txtDesc.setText(textTemp);
            txtDesc.setEditable(false);

            Alert alert = new Alert(Alert.AlertType.WARNING, "Beskrivelse forandret!");
            alert.showAndWait();
        }
        isEditDesc ^= true;
    }



        public void editSale(ActionEvent actionEvent) {
            //todo har bare legg til vare inntil videre
            openVareView(actionEvent);
        }

        public void changeUser(ActionEvent actionEvent) {
            openHomeView(actionEvent);
        }

    public void toButikkSalgsOversikt(ActionEvent actionEvent) {
        openButikkSalgsOversiktView(actionEvent);
    }
}

