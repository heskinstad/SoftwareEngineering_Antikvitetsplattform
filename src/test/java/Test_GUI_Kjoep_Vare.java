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
import sample.model.Vare;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Test_GUI_Kjoep_Vare extends ApplicationTest {

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
        Assert.assertEquals(homeController.getBruker().getFornavn() + homeController.getBruker().getEtternavn(), "TestTesteren");
        DataHandlerButikk.registrerButikk(new Butikk("TestButikk", "TestSpesialitet", "TestLeder", "TestBeskrivelse"));
        DataHandlerVare.leggInnVareTest(new Vare("TestVare", "TestBeskrivelse", "TestButikk", 100, "testImage.jpg"));

        Parent root = FXMLLoader.load(getClass().getResource("/view/brukerView.fxml"));
        primaryStage.setScene(new Scene(root, 676, 624));
        primaryStage.show();
    }

    /* Tester krav:
       - En bruker skal kunne kjøpe/by på en vare ved å trykke på en knapp i applikasjonen
       - En bruker skal kunne se på varer
     */
    @Test
    public void test_Kjoep_Vare() {
        clickOn("#ButikkValgBox");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#btnSideSkiftNeste");
        clickOn("#btnSideSkiftForrige");
        clickOn("#btnButikkValgt");
        clickOn("#btnKjopVare2");
        type(KeyCode.ENTER);
        clickOn("#btnKjopVare1");
        type(KeyCode.ENTER);
        clickOn("#btnKjopVare0");

        Assert.assertEquals(DataHandlerSalg.hentSalg().get(0).getSolgtVare().getNavn(), "TestVare");
        Assert.assertEquals(DataHandlerSalg.hentSalg().get(0).getSolgtVare().getBeskrivelse(), "TestBeskrivelse");
        Assert.assertEquals(DataHandlerSalg.hentSalg().get(0).getSolgtVare().getButikk(), "TestButikk");
        Assert.assertEquals(DataHandlerSalg.hentSalg().get(0).getSolgtVare().getPris(), 100);
        Assert.assertEquals(DataHandlerSalg.hentSalg().get(0).getSolgtVare().getBildeURL(), "testImage.jpg");
        Assert.assertEquals(DataHandlerSalg.hentSalg().get(0).getKjoper(), "Test Testeren");
        Assert.assertEquals(DataHandlerSalg.hentSalg().get(0).getSelger(), "TestButikk");
    }
}
