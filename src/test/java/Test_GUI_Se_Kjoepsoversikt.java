import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.controller.homeController;
import sample.data.*;
import sample.model.Bruker;
import sample.model.Butikk;
import sample.model.Salg;
import sample.model.Vare;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Test_GUI_Se_Kjoepsoversikt extends ApplicationTest {

    static {
        DataHandlerPaths.setPathsTest();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Test_Read_Write_JSON.deleteAllInAllFiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        homeController.setBruker(new Bruker("Test", "Testeren"));
        DataHandlerButikk.registrerButikk(new Butikk("TestButikk", "TestSpesialitet", "TestLeder", "TestBeskrivelse"));
        DataHandlerVare.leggInnVareTest(new Vare("TestVare", "TestBeskrivelse", "TestButikk", 100, "testImage.jpg"));
        DataHandlerSalg.registrerSalg(new Salg("Test Testeren", "TestButikk", DataHandlerVare.hentVarer().get(0)));

        Parent root = FXMLLoader.load(getClass().getResource("/view/brukerView.fxml"));
        primaryStage.setScene(new Scene(root, 676, 624));
        primaryStage.show();
    }

    /* Tester krav:
           - En bruker/butikk/admin skal kunne se et kjøps-/salgshistorikk som passer til brukertypen, ved å trykke på en knapp i applikasjonen
         */
    @Test
    public void test_Se_Kjoepsoversikt() {
        clickOn("#btnAapneSalgsOversikt");
        clickOn("#btnForlatOversikt");
    }
}
