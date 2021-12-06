import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.controller.addVareController;
import sample.controller.homeController;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerPaths;
import sample.data.DataHandlerSalg;
import sample.data.DataHandlerVare;
import sample.model.Bruker;
import sample.model.Butikk;
import sample.model.Vare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Test_GUI_Legg_Til_Vare extends ApplicationTest {

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

        Butikk currentButikk = new Butikk("TestButikk", "TestSpesialitet", "TestLeder", "TestBeskrivelse");
        homeController.setButikk(currentButikk);
        DataHandlerButikk.registrerButikk(homeController.getButikk());

        Parent root = FXMLLoader.load(getClass().getResource("/view/butikkView.fxml"));
        primaryStage.setScene(new Scene(root, 616, 680));
        primaryStage.show();
    }

    /* Tester krav:
       - En butikk skal kunne legge ut varer for salg ved å registrere varen i applikasjonen
     */
    @Test
    public void test_Legg_Til_Vare_Slett_Vare() {
        legg_Til_Vare();

        slett_vare();

        DataHandlerVare.leggInnVareTest(new Vare("TestVare", "TestBeskrivelse", "TestAnnenButikk", 100, "testImage.jpg"));
        clickOn("#btnEditSale");
        clickOn("#btnAvbryt");
    }

    public void legg_Til_Vare() {
        clickOn("#btnEditSale");
        clickOn("#btnAvbryt");
        clickOn("#btnEditSale");

        clickOn("#btnAddVare");
        type(KeyCode.ENTER);

        clickOn("#input_navn");
        write("TestVare");
        clickOn("#input_pris");
        write("100");
        clickOn("#input_url");
        write("testImage.jpg");
        addVareController.setImagePath(new File(new File("").getAbsolutePath() + DataHandlerPaths.getImagePath() + "testImage.jpg"));
        clickOn("#input_beskrivelse");
        write("TestBeskrivelse");
        clickOn("#btnAddVare");
        type(KeyCode.ENTER);

        Assert.assertEquals(DataHandlerVare.hentVarer().get(0).getNavn(), "TestVare");
        Assert.assertEquals(DataHandlerVare.hentVarer().get(0).getPris(), 100);
        Assert.assertEquals(DataHandlerVare.hentVarer().get(0).getBeskrivelse(), "TestBeskrivelse");
    }

    public void slett_vare() {
        clickOn("#btnEditSale");
        clickOn("#velgVare");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#slettVare");
        type(KeyCode.ENTER);
        clickOn("#btnAvbryt");

        Assert.assertTrue(DataHandlerVare.hentVarer().isEmpty());
    }
}
