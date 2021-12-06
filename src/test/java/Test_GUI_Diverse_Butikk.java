import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.controller.homeController;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerPaths;
import sample.data.DataHandlerVare;
import sample.model.Butikk;
import sample.model.Vare;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Test_GUI_Diverse_Butikk extends ApplicationTest {

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
        for (int i = 0; i < 5; i++) {
            DataHandlerVare.leggInnVareTest(new Vare("TestVare"+i, "TestBeskrivelse"+i, "TestButikk", 100, "testImage.jpg"));
        }

        Parent root = FXMLLoader.load(getClass().getResource("/view/butikkView.fxml"));
        primaryStage.setScene(new Scene(root, 616, 680));
        primaryStage.show();
    }


    @Test
    public void test_Endre_Beskrivelse_Og_Naviger() {
        endre_Beskrivelse_Og_Naviger();
    }

    public void endre_Beskrivelse_Og_Naviger() {

        clickOn("#btnEditDesc");
        clickOn("#txtDesc");
        write("Ny");
        clickOn("#btnEditDesc");
        type(KeyCode.ENTER);

        Assert.assertEquals(homeController.getButikk().getBeskrivelse(), "TestBeskrivelseNy");

        clickOn("#btnSideSkiftNeste");
        clickOn("#btnSideSkiftNeste");
        clickOn("#btnSideSkiftForrige");
    }
}
