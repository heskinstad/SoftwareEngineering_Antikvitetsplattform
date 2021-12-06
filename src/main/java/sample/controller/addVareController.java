package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.data.DataHandlerVare;
import sample.model.Vare;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.ArrayList;

public class addVareController extends homeController {

    @FXML
    public TextArea input_beskrivelse;
    @FXML
    public TextField input_navn;
    @FXML
    public TextField input_pris;
    @FXML
    public TextField input_url;
    @FXML
    public ChoiceBox<Vare> velgVare;

    static File imagePath;

    public static void setImagePath(File imagePath) {
        addVareController.imagePath = imagePath;
    }

    @FXML
    public void initialize() {
        ArrayList<Vare> registrerteVarer = DataHandlerVare.hentVarer();

        for (int i = 0; i < registrerteVarer.size(); i++) {
            if (!registrerteVarer.get(i).getButikk().equals(butikk.getNavn())) {
                registrerteVarer.remove(i);
                i--;
            }
        }

        ObservableList<Vare> listeMedVarer = FXCollections.observableArrayList(registrerteVarer);
        velgVare.setItems(listeMedVarer);

    }

    public void slettVare(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, velgVare.getValue().getNavn()+ " er blitt slettet");
        alert.showAndWait();

        DataHandlerVare.slettVare(velgVare.getValue());
        velgVare.getItems().removeAll(velgVare.getValue());

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
            DataHandlerVare.leggInnVare(vare, imagePath);
            butikk.getVareListe().add(vare);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vare lagt til");
            alert.showAndWait();
            avbryt(actionEvent);
        }
    }

    public void avbryt(ActionEvent actionEvent) {
        openButikkView(actionEvent);
    }

    public void openExplorerImg(ActionEvent actionEvent) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileFilter(){

                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }

                    String extension = null;
                    String name = f.getName();
                    int indexOfDot = name.lastIndexOf('.');
                    if (indexOfDot >= 0) {
                        extension = name.substring(indexOfDot);
                    }
                    System.out.println(extension);

                    if (extension != null) {
                        return extension.toLowerCase().equals(".jpeg") ||
                                extension.toLowerCase().equals(".jpg") ||
                                extension.toLowerCase().equals(".png");
                    }

                    return false;
                }

                @Override
                public String getDescription() {
                    return "images";
                }
            });
            fileChooser.setCurrentDirectory(new File("C:\\"));

            int response = fileChooser.showDialog(null, "choose");
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getName());
                imagePath = new File(fileChooser.getSelectedFile().getAbsolutePath());

                System.out.println(file);
            }

            input_url.setText(fileChooser.getSelectedFile().getName());
        } catch (Exception e) {
            System.out.println("Fil ikke funnet");
        }
    }
}

