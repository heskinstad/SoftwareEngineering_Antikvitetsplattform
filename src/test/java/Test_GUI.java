import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerKlage;
import sample.data.DataHandlerPaths;
import sample.model.Butikk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.testfx.api.FxToolkit.hideStage;


public class Test_GUI extends ApplicationTest {

    static {
        DataHandlerPaths.setPathsTest();

        try {
            Test_Read_Write_JSON.deleteAllInAllFiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DataHandlerButikk.registrerButikk(new Butikk("TestButikk", "TestSpesialitet", "TestLeder", "TestBeskrivelse"));
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/homeView.fxml"));
        primaryStage.setTitle("Antikvitetsplattform");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @After
    public void afterEachTest() throws Exception {
        hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void test_Registrer_Bruker_Send_Klage_Slett_Klage() {
        registrer_Bruker();
        login_Bruker();
        avbryt_Klage();
        send_Klage();

        Assert.assertEquals("Test Testeren", DataHandlerKlage.hentKlager().get(0).getNavn());
        Assert.assertEquals("TestButikk", DataHandlerKlage.hentKlager().get(0).getButikk());
        Assert.assertEquals("TestMelding", DataHandlerKlage.hentKlager().get(0).getMelding());

        clickOn("#btnBackToLogin");

        login_Admin_Refresh_Slett_Klage();
    }

    private void registrer_Bruker() {
        clickOn("#btnLoginCustomer");
        clickOn("#registrerFornavn");
        write("Test");
        clickOn("#registrerEtternavn");
        write("Testeren");
        clickOn("#btnRegistrerNyBruker");
        clickOn("#btnBackToLogin");
    }

    private void login_Bruker() {
        clickOn("#btnLoginCustomer");
        clickOn("#loggInnFornavn");
        write("Test");
        clickOn("#loggInnEtternavn");
        write("Testeren");
        clickOn("#btnLoggInnBruker");
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
        clickOn("#input_Melding");
        write("TestMelding");
        clickOn("#btnSendKlage");
        type(KeyCode.ENTER);
    }

    private void login_Admin_Refresh_Slett_Klage() {
        clickOn("#btnLoginAdmin");
        clickOn("#btnRefresh");
        clickOn("#table_Complaints");
        moveBy(0,-50);
        clickOn();
        clickOn("#sletteKlage");

        Assert.assertTrue(DataHandlerKlage.hentKlager().isEmpty());

        clickOn("#btnChangeUser");
    }
}
