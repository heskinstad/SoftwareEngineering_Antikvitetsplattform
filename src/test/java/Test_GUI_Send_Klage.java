import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.controller.brukerController;
import sample.data.DataHandlerBruker;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerKlage;
import sample.data.DataHandlerPaths;
import sample.model.Bruker;
import sample.model.Butikk;
import sample.controller.homeController;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Test_GUI_Send_Klage extends ApplicationTest {

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

        Parent root = FXMLLoader.load(getClass().getResource("/view/brukerView.fxml"));
        primaryStage.setScene(new Scene(root, 676, 624));
        primaryStage.show();
    }


    @Test
    public void test_Send_Klage_Slett_Klage() {
        homeController.setBruker(new Bruker("Test", "Testeren"));
        DataHandlerButikk.registrerButikk(new Butikk("TestButikk", "TestSpesialitet", "TestLeder", "TestBeskrivelse"));

        avbryt_Klage();
        send_Klage();
    }

    private void avbryt_Klage() {
        clickOn("#btnAapneKlageSkjema");
        clickOn("#btnAvbryt");
    }

    private void send_Klage() {
        clickOn("#btnAapneKlageSkjema");
        clickOn("#input_Butikk");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#btnSendKlage");
        type(KeyCode.ENTER);
        clickOn("#input_Melding");
        write("TestMelding");
        clickOn("#btnSendKlage");
        type(KeyCode.ENTER);

        Assert.assertEquals("Test Testeren", DataHandlerKlage.hentKlager().get(0).getNavn());
        Assert.assertEquals("TestButikk", DataHandlerKlage.hentKlager().get(0).getButikk());
        Assert.assertEquals("TestMelding", DataHandlerKlage.hentKlager().get(0).getMelding());
    }
}
