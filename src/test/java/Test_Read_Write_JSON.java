import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.Image;
import org.junit.Assert;
import org.junit.Test;
import sample.data.*;
import sample.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class Test_Read_Write_JSON {

    @Test
    public void test_Vare_Read_Write() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testVarer.JSON");
        writer.close();

        Vare vare = new Vare("testVare", "testBeskrivelse", "testButikk", 1001, "");
        DataHandlerVare.leggInnVare(vare, "/src/test/resources/JSON/testVarer.JSON", new File(""));
        Vare vare2 = DataHandlerVare.hentVarer("/src/test/resources/JSON/testVarer.JSON").get(0);
        assertEquals(vare.getId(), vare2.getId());
        assertEquals(vare.getNavn(), vare2.getNavn());
        assertEquals(vare.getBeskrivelse(), vare2.getBeskrivelse());
        assertEquals(vare.getButikk(), vare2.getButikk());
        assertEquals(vare.getPris(), vare2.getPris());
        assertEquals(vare.getTidspunkt(), vare2.getTidspunkt());
        assertEquals(vare.getBildeURL(), vare2.getBildeURL());
    }

    @Test
    public void test_Klage_Read_Write() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testKlager.JSON");
        writer.close();

        Klage klage = new Klage("Ola Nordmann", "Butikken deres er tull og humbug", "Krakkel og spetakkel og andre antivkiteter");
        DataHandlerKlage.leggInnKlage(klage, "/src/test/resources/JSON/testKlager.JSON");
        Klage klage2 = DataHandlerKlage.hentKlager("/src/test/resources/JSON/testKlager.JSON").get(0);
        assertEquals(klage.getId(), klage2.getId());
        assertEquals(klage.getNavn(), klage2.getNavn());
        assertEquals(klage.getMelding(), klage2.getMelding());
        assertEquals(klage.getButikk(), klage2.getButikk());
        assertEquals(klage.getTidspunkt(), klage2.getTidspunkt());

        DataHandlerKlage.slettKlage(klage, "/src/test/resources/JSON/testKlager.JSON");
        assertTrue(DataHandlerKlage.hentKlager("/src/test/resources/JSON/testKlager.JSON").isEmpty());
    }

    @Test
    public void test_Butikk_Read_Write() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testButikker.JSON");
        writer.close();

        Butikk butikk = new Butikk("test", "testing", "test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikk(butikk, "/src/test/resources/JSON/testButikker.JSON");
        Butikk butikk2 = DataHandlerButikk.hentButikker("/src/test/resources/JSON/testButikker.JSON").get(0);
        assertEquals(butikk.getNavn(), butikk2.getNavn());
        assertEquals(butikk.getSpesialitet(), butikk2.getSpesialitet());
        assertEquals(butikk.getDagligLeder(), butikk2.getDagligLeder());
        assertEquals(butikk.getTidspunkt(), butikk2.getTidspunkt());


    }

    @Test
    public void test_Butikk_Holder_Vare() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testButikker.JSON");
        PrintWriter writer2 = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testVarer.JSON");
        writer.close();
        writer2.close();

        Butikk butikk = new Butikk("test", "testing", "test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikk(butikk, "/src/test/resources/JSON/testButikker.JSON");

        Vare vare = new Vare("testVare", "testBeskrivelse", "test", 1001, "");
        DataHandlerVare.leggInnVare(vare, "/src/test/resources/JSON/testVarer.JSON", new File(""));

        butikk.setVarerIButikk("/src/test/resources/JSON/testVarer.JSON");
        ArrayList<Vare> vareListe = butikk.getVareListe();
        Vare testVare = vareListe.get(0);
        assertEquals(testVare.getButikk(), butikk.getNavn());
        assertEquals(testVare.getNavn(), vare.getNavn());

    }

    @Test
    public void test_Registrer_Salg_Fjern_Vare() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testButikker.JSON");
        PrintWriter writer2 = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testVarer.JSON");
        PrintWriter writer3 = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testSalg.JSON");
        PrintWriter writer4 = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testBruker.JSON");
        writer.close();
        writer2.close();
        writer3.close();
        writer4.close();

        Butikk butikk = new Butikk("test butikk", "testing", "test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikk(butikk, "/src/test/resources/JSON/testButikker.JSON");

        Bruker bruker = new Bruker("Test", "Testeren");
        DataHandlerBruker.leggInnBruker(bruker, "/src/test/resources/JSON/testBruker.JSON");

        Vare vare = new Vare("testVare", "testBeskrivelse", "test butikk", 1001, "");
        DataHandlerVare.leggInnVare(vare, "/src/test/resources/JSON/testVarer.JSON", new File(""));

        Vare vare2 = new Vare("testVare2", "testBeskrivelse", "test butikk", 1001, "");
        DataHandlerVare.leggInnVare(vare2, "/src/test/resources/JSON/testVarer.JSON", new File(""));

        butikk.setVarerIButikk("/src/test/resources/JSON/testVarer.JSON");

        Salg salg = new Salg(bruker.toString(), butikk.getNavn(), vare);
        DataHandlerSalg.registrerSalg(salg, "/src/test/resources/JSON/testSalg.JSON", "/src/test/resources/JSON/testVarer.JSON", "/src/test/resources/JSON/testButikker.JSON");

        ArrayList<Salg> salgListe = DataHandlerSalg.hentSalg("/src/test/resources/JSON/testSalg.JSON");
        Salg testSalg = salgListe.get(0);

        assertEquals(salg.getKjoper(), testSalg.getKjoper());
        assertEquals(salg.getSelger(), testSalg.getSelger());
        assertEquals(salg.getSolgtVare().getNavn(), testSalg.getSolgtVare().getNavn());

        ArrayList<Vare> vareListe = DataHandlerVare.hentVarer("/src/test/resources/JSON/testVarer.JSON");
        Vare testVare = vareListe.get(0);


        assertEquals(vare2.getNavn(), testVare.getNavn());

    }

    @Test
    public void test_Bruker_Read_Write() throws FileNotFoundException, InterruptedException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testBruker.JSON");
        writer.close();

        Bruker bruker = new Bruker("Test", "Testeren");
        DataHandlerBruker.leggInnBruker(bruker, "/src/test/resources/JSON/testBruker.JSON");
        Bruker bruker2 = DataHandlerBruker.hentBrukere("/src/test/resources/JSON/testBruker.JSON").get(0);
        assertEquals(bruker.getFornavn(), bruker2.getFornavn());
        assertEquals(bruker.getEtternavn(), bruker2.getEtternavn());
        assertEquals(bruker.getBrukerOpprettet(), bruker2.getBrukerOpprettet());
        assertEquals(bruker.getSisteInnlogging(), bruker2.getSisteInnlogging());
        sleep(100);
        DataHandlerBruker.oppdaterSisteInnlogging(bruker, "/src/test/resources/JSON/testBruker.JSON");
        bruker2 = DataHandlerBruker.hentBrukere("/src/test/resources/JSON/testBruker.JSON").get(0);
        assertNotEquals(bruker.getSisteInnlogging(), bruker2.getSisteInnlogging());

    }

    @Test
    public void test_Vare_Slett_Vare() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/JSON/testVarer.JSON");
        writer.close();

        Vare vare = new Vare("testVare", "testBeskrivelse", "test", 1001, "");
        DataHandlerVare.leggInnVare(vare, "/src/test/resources/JSON/testVarer.JSON", new File(""));
        DataHandlerVare.slettVare(vare, "/src/test/resources/JSON/testBruker.JSON");
        assertTrue(DataHandlerKlage.hentKlager("/src/test/resources/JSON/testVarer.JSON").isEmpty());

    }

    @Test
    public void test_regisreting_salg() {
        boolean thrown = true;
        //Denne testen failer, som vi må fikse noe her.
        try {
            DataHandlerSalg.hentSalg("/src/main/resources/JSON/salg.JSxON");

        } catch (IllegalStateException e) {
            thrown = false;

        }
        System.out.println(thrown);
        assertFalse(thrown);
    }




}
