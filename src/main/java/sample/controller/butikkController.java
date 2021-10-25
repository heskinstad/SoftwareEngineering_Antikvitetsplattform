package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class butikkController extends homeController {

    private boolean isEditDesc = false; // verdi for å sjekke om editMode på beskrivelse er aktivt

    @FXML Text vare_tittel_0;
    @FXML Text vare_tittel_1;
    @FXML Text vare_tittel_2;
    @FXML Text vare_tittel_3;
    @FXML Text vare_beskrivelse_0;
    @FXML Text vare_beskrivelse_1;
    @FXML Text vare_beskrivelse_2;
    @FXML Text vare_beskrivelse_3;
    @FXML ImageView vare_url_0;
    @FXML ImageView vare_url_1;
    @FXML ImageView vare_url_2;
    @FXML ImageView vare_url_3;


    @FXML
    public void initialize(ActionEvent actionEvent) {
        refreshVarer();
    }

    private void refreshVarer() {
    }

    public void editDesc(ActionEvent actionEvent){


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
        //TODO need model
    }

    public void changeUser(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Antikvitetsplatform", 700, 500);
    }
}
