import org.junit.Test;
import sample.data.*;
import sample.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class Test_Read_Write_JSON {

    @Test
    public void test_Vare_Read_Write() throws FileNotFoundException {
        deleteAllInFile("/src/test/resources/JSON/testVarer.JSON");

        Vare vare = new Vare("testVare", "testBeskrivelse", "testButikk", 1001, "");
        DataHandlerVare.leggInnVareTest(vare);
        Vare vare2 = DataHandlerVare.hentVarerTest().get(0);
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
        deleteAllInFile("/src/test/resources/JSON/testKlager.JSON");

        Klage klage = new Klage("Ola Nordmann", "Butikken deres er tull og humbug", "Krakkel og spetakkel og andre antikviteter");
        DataHandlerKlage.leggInnKlageTest(klage);
        Klage klage2 = DataHandlerKlage.hentKlagerTest().get(0);
        assertEquals(klage.getId(), klage2.getId());
        assertEquals(klage.getNavn(), klage2.getNavn());
        assertEquals(klage.getMelding(), klage2.getMelding());
        assertEquals(klage.getButikk(), klage2.getButikk());
        assertEquals(klage.getTidspunkt(), klage2.getTidspunkt());

        DataHandlerKlage.slettKlageTest(klage);
        assertTrue(DataHandlerKlage.hentKlagerTest().isEmpty());
    }

    @Test
    public void test_Butikk_Read_Write() throws FileNotFoundException {
        deleteAllInFile("/src/test/resources/JSON/testButikker.JSON");

        Butikk butikk = new Butikk("test", "testing", "test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikkTest(butikk);
        Butikk butikk2 = DataHandlerButikk.hentButikkerTest().get(0);
        assertEquals(butikk.getNavn(), butikk2.getNavn());
        assertEquals(butikk.getSpesialitet(), butikk2.getSpesialitet());
        assertEquals(butikk.getDagligLeder(), butikk2.getDagligLeder());
        assertEquals(butikk.getTidspunkt(), butikk2.getTidspunkt());


    }

    @Test
    public void test_Butikk_Holder_Vare() throws FileNotFoundException {
        deleteAllInFile("/src/test/resources/JSON/testButikker.JSON");
        deleteAllInFile("/src/test/resources/JSON/testVarer.JSON");

        Butikk butikk = new Butikk("test", "testing", "test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikkTest(butikk);

        Vare vare = new Vare("testVare", "testBeskrivelse", "test", 1001, "");
        DataHandlerVare.leggInnVareTest(vare);

        butikk.setVarerIButikk("/src/test/resources/JSON/testVarer.JSON");
        ArrayList<Vare> vareListe = butikk.getVareListe();
        Vare testVare = vareListe.get(0);
        assertEquals(testVare.getButikk(), butikk.getNavn());
        assertEquals(testVare.getNavn(), vare.getNavn());

    }

    @Test
    public void test_Registrer_Salg_Fjern_Vare() throws FileNotFoundException {
        deleteAllInFile("/src/test/resources/JSON/testButikker.JSON");
        deleteAllInFile("/src/test/resources/JSON/testVarer.JSON");
        deleteAllInFile("/src/test/resources/JSON/testSalg.JSON");
        deleteAllInFile("/src/test/resources/JSON/testBruker.JSON");

        Butikk butikk = new Butikk("test butikk", "testing", "test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikkTest(butikk);

        Bruker bruker = new Bruker("Test", "Testeren");
        DataHandlerBruker.leggInnBrukerTest(bruker);

        Vare vare = new Vare("testVare", "testBeskrivelse", "test butikk", 1001, "");
        DataHandlerVare.leggInnVareTest(vare);

        Vare vare2 = new Vare("testVare2", "testBeskrivelse", "test butikk", 1001, "");
        DataHandlerVare.leggInnVareTest(vare2);

        butikk.setVarerIButikk("/src/test/resources/JSON/testVarer.JSON");

        Salg salg = new Salg(bruker.toString(), butikk.getNavn(), vare);
        DataHandlerSalg.registrerSalgTest(salg);

        ArrayList<Salg> salgListe = DataHandlerSalg.hentSalgTest();
        Salg testSalg = salgListe.get(0);

        assertEquals(salg.getKjoper(), testSalg.getKjoper());
        assertEquals(salg.getSelger(), testSalg.getSelger());
        assertEquals(salg.getSolgtVare().getNavn(), testSalg.getSolgtVare().getNavn());

        ArrayList<Vare> vareListe = DataHandlerVare.hentVarerTest();
        Vare testVare = vareListe.get(0);


        assertEquals(vare2.getNavn(), testVare.getNavn());

    }

    @Test
    public void test_Bruker_Read_Write() throws FileNotFoundException, InterruptedException {
        deleteAllInFile("/src/test/resources/JSON/testBruker.JSON");

        Bruker bruker = new Bruker("Test", "Testeren");
        DataHandlerBruker.leggInnBrukerTest(bruker);
        Bruker bruker2 = DataHandlerBruker.hentBrukereTest().get(0);
        assertEquals(bruker.getFornavn(), bruker2.getFornavn());
        assertEquals(bruker.getEtternavn(), bruker2.getEtternavn());
        assertEquals(bruker.getBrukerOpprettet(), bruker2.getBrukerOpprettet());
        assertEquals(bruker.getSisteInnlogging(), bruker2.getSisteInnlogging());
        sleep(100);
        DataHandlerBruker.oppdaterSisteInnloggingTest(bruker);
        bruker2 = DataHandlerBruker.hentBrukereTest().get(0);
        assertNotEquals(bruker.getSisteInnlogging(), bruker2.getSisteInnlogging());

    }

    @Test
    public void test_Vare_Slett_Vare() throws FileNotFoundException {
        deleteAllInFile("/src/test/resources/JSON/testVarer.JSON");

        Vare vare = new Vare("testVare", "testBeskrivelse", "test", 1001, "");
        DataHandlerVare.leggInnVareTest(vare);
        DataHandlerVare.slettVareTest(vare);
        assertTrue(DataHandlerKlage.hentKlagerTest().isEmpty());

    }

    private void deleteAllInFile(String path) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + path);
        writer.close();
    }
}
