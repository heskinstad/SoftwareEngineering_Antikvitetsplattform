package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.data.DataHandlerVare;
import sample.model.Vare;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class vareController extends homeController {

    @FXML
    public TextArea input_beskrivelse;
    @FXML
    public TextField input_navn;
    @FXML
    public TextField input_pris;
    @FXML
    public TextField input_url;

    @FXML
    public void initialize() {

    }

    public void addVare(ActionEvent actionEvent) {
        if (input_beskrivelse.getText().equals("") || input_navn.getText().equals("")
                || input_pris.getText().equals("") || input_url.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ett eller flere felter er tomme");
            alert.show();
        } else {

            //todo input_butik skal ikkje bli skreve inn av buttikk
            Vare vare = new Vare(input_navn.getText(), input_beskrivelse.getText(), butikk.getNavn(),
                    Integer.parseInt(input_pris.getText()), input_url.getText());
            DataHandlerVare.leggInnVare(vare);
            butikk.getVareListe().add(vare);
            Alert alert = new Alert(Alert.AlertType.WARNING, "vare lagt til");
            alert.showAndWait();
            avbryt(actionEvent);
        }
    }

    public void avbryt(ActionEvent actionEvent) {
        openNewInterface(actionEvent, "/view/butikkView.fxml", "Butikk Control", 624, 648);
    }

    public void openExplorerImg(ActionEvent actionEvent) {
        try {


            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:\\Users\\vetle_93dri9c\\Pictures"));

            int response = fileChooser.showDialog(null, "choose");
            if(response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
            }

            input_url.setText(fileChooser.getSelectedFile().getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Fil ikke funnet");
        }
    }
}

