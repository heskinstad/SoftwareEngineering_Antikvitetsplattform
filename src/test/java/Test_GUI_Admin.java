import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.data.*;
import sample.model.Butikk;
import sample.model.Klage;
import sample.model.Salg;
import sample.model.Vare;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Test_GUI_Admin extends ApplicationTest {

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

        DataHandlerKlage.leggInnKlage(new Klage("Test Testeren", "TestMelding", "TestButikk"));
        DataHandlerButikk.registrerButikk(new Butikk("TestButikk", "TestSpesialitet", "TestLeder", "TestBeskrivelse"));
        DataHandlerVare.leggInnVareTest(new Vare("TestVare", "TestBeskrivelse", "TestButikk", 100, "testImage.jpg"));
        DataHandlerSalg.registrerSalg(new Salg("Test Testeren", "TestButikk", DataHandlerVare.hentVarer().get(0)));

        Parent root = FXMLLoader.load(getClass().getResource("/view/homeView.fxml"));
        primaryStage.setTitle("Antikvitetsplattform");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    @Test
    public void test_Registrer_Bruker_Send_Klage_Slett_Klage() {




        login_Admin_Refresh_Slett_Klage();
    }

    public void login_Admin_Refresh_Slett_Klage() {

        clickOn("#btnLoginAdmin");

        Assert.assertEquals("Test Testeren", DataHandlerKlage.hentKlager().get(0).getNavn());
        Assert.assertEquals("TestMelding", DataHandlerKlage.hentKlager().get(0).getMelding());
        Assert.assertEquals("TestButikk", DataHandlerKlage.hentKlager().get(0).getButikk());

        clickOn("#btnRefresh");
        clickOn("#table_Complaints");
        moveBy(0,-50);
        clickOn();
        clickOn("#sletteKlage");

        Assert.assertTrue(DataHandlerKlage.hentKlager().isEmpty());

        clickOn("#btnChangeUser");
    }
}
