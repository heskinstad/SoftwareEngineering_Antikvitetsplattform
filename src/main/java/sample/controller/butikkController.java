package sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.data.*;
import sample.model.Vare;

import java.util.ArrayList;


public class butikkController extends homeController {

    private boolean isEditDesc = false; // verdi for å sjekke om editMode på beskrivelse er aktivt
    @FXML AnchorPane anchorPane;

    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            public void run() {
                Scene scene = anchorPane.getScene();
                refreshVarer(scene);
            }
        });
    }

    private void refreshVarer(Scene scene) {
        ArrayList<Vare> varer = new ArrayList<>(DataHandlerVare.hentVarer());
        for (int i = 0; i < 4; i++) {
            Text vareTittel = (Text) scene.lookup("#vare_tittel_" + i);
            Text vareBeskrivelse = (Text) scene.lookup("#vare_beskrivelse_" + i);
            ImageView vareURL = (ImageView) scene.lookup("#vare_url_" + i);

            //sjekker om det er flere varer i varelista
            if (i >= varer.size()) {
                vareTittel.setText(null);
                vareBeskrivelse.setText(null);
                vareURL.setImage(null);
                continue;
            }

            vareTittel.setText(varer.get(i).getNavn());
            vareBeskrivelse.setText(varer.get(i).getBeskrivelse());
            //TODO resolve image insert
            //vareURL.setImage(varer.get(i).getBildeURL());
        }
    }

    public void forrigeSide(ActionEvent actionEvent){
        //TODO implement function
    }
    public void nesteSide(ActionEvent actionEvent){
        //TODO implement function
    }

    public void editDesc(ActionEvent actionEvent){

        //TODO save changes
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        Button btnEditDesc = (Button) scene.lookup("#btnEditDesc");
        TextArea txtDesc = (TextArea) scene.lookup("#txtDesc");

        if (!isEditDesc){
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

        openNewInterface(actionEvent, "../view/addVareView.fxml", "legg til vare", 700, 500);
    }

    public void changeUser(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Antikvitetsplatform", 700, 500);
    }
}
