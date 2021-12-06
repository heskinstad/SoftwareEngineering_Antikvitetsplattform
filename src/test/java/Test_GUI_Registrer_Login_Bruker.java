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
import sample.data.DataHandlerBruker;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerKlage;
import sample.data.DataHandlerPaths;
import sample.model.Butikk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.testfx.api.FxToolkit.hideStage;


public class Test_GUI_Registrer_Login_Bruker extends ApplicationTest {

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
    public void test_Registrer_Login_Bruker() {

        clickOn("#btnLoginCustomer");
        clickOn("#tilbakeTilLoggInn");
        clickOn("#btnLoginCustomer");

        login_Bruker();
        type(KeyCode.ENTER);
        registrer_Bruker();
        clickOn("#btnBackToLogin");

        clickOn("#btnLoginCustomer");

        registrer_Bruker();
        type(KeyCode.ENTER);
        login_Bruker();
    }

    private void registrer_Bruker() {
        clickOn("#registrerFornavn");
        write("Test");
        clickOn("#btnRegistrerNyBruker");
        type(KeyCode.ENTER);
        clickOn("#registrerEtternavn");
        write("Testeren");
        clickOn("#btnRegistrerNyBruker");
    }

    private void login_Bruker() {
        clickOn("#loggInnFornavn");
        write("Test");
        clickOn("#btnLoggInnBruker");
        type(KeyCode.ENTER);
        clickOn("#loggInnEtternavn");
        write("Testeren");
        clickOn("#btnLoggInnBruker");
    }
}
