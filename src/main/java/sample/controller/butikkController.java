package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class butikkController {

    private boolean isEditDesc = false; // verdi for å sjekke om editMode på beskrivelse er aktivt

    @FXML
    public void initialize(ActionEvent actionEvent) {
    }


    public void editDesc(ActionEvent actionEvent){
        //TODO need model to finnish buttonEvent, need model

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
        }
        isEditDesc ^= true;
    }

    public void editSale(ActionEvent actionEvent) {
        //TODO need model
    }

    public void changeUser(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "../view/sample.fxml", "Antikvitetsplatform", 700, 500);
    }

    public void openNewInterface(ActionEvent actionEvent, String path, String windowTitle, int width, int height) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(root, width, height));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
