import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerPaths;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Test_GUI_Registrer_Login_Butikk extends ApplicationTest {

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

        Parent root = FXMLLoader.load(getClass().getResource("/view/homeView.fxml"));
        primaryStage.setTitle("Antikvitetsplattform");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    @Test
    public void test_Registrer_Login_Butikk() {

        clickOn("#btnLoginUser");
        clickOn("#tilbakeTilLoggInn");
        clickOn("#btnLoginUser");

        registrer_Butikk();
        clickOn("#btnChangeUser");

        clickOn("#btnLoginUser");

        login_Butikk();
    }

    public void registrer_Butikk() {
        clickOn("#registrerButikk");
        type(KeyCode.ENTER);
        clickOn("#butikkNavn");
        write("TestButikk");
        clickOn("#butikkSpesialtiet");
        write("TestSpesialitet");
        clickOn("#butikkLeder");
        write("TestLeder");
        clickOn("#beskrivelseText");
        write("TestBeskrivelse");
        clickOn("#registrerButikk");
        type(KeyCode.ENTER);

        Assert.assertEquals(DataHandlerButikk.hentButikker().get(0).getNavn(), "TestButikk");
        Assert.assertEquals(DataHandlerButikk.hentButikker().get(0).getSpesialitet(), "TestSpesialitet");
        Assert.assertEquals(DataHandlerButikk.hentButikker().get(0).getDagligLeder(), "TestLeder");
        Assert.assertEquals(DataHandlerButikk.hentButikker().get(0).getBeskrivelse(), "TestBeskrivelse");
    }

    public void login_Butikk() {
        clickOn("#butikkLoggInn");
        type(KeyCode.ENTER);
        clickOn("#velgButikkSlide");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#butikkLoggInn");

        Assert.assertEquals(DataHandlerButikk.hentButikker().get(0).getNavn(), "TestButikk");
        Assert.assertEquals(DataHandlerButikk.hentButikker().get(0).getSpesialitet(), "TestSpesialitet");
        Assert.assertEquals(DataHandlerButikk.hentButikker().get(0).getDagligLeder(), "TestLeder");
        Assert.assertEquals(DataHandlerButikk.hentButikker().get(0).getBeskrivelse(), "TestBeskrivelse");
    }
}
