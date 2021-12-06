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

    static {
        DataHandlerPaths.setPathsTest();
    }

    @Test
    public void test_Vare_Read_Write() throws FileNotFoundException {
        deleteAllInAllFiles();

        Vare vare = new Vare("testVare", "testBeskrivelse", "testButikk", 1001, "");
        DataHandlerVare.leggInnVareTest(vare);
        Vare vare2 = DataHandlerVare.hentVarer().get(0);
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
        deleteAllInAllFiles();

        Klage klage = new Klage("Ola Nordmann", "Butikken deres er tull og humbug", "Krakkel og spetakkel og andre antikviteter");
        DataHandlerKlage.leggInnKlage(klage);
        Klage klage2 = DataHandlerKlage.hentKlager().get(0);
        assertEquals(klage.getId(), klage2.getId());
        assertEquals(klage.getNavn(), klage2.getNavn());
        assertEquals(klage.getMelding(), klage2.getMelding());
        assertEquals(klage.getButikk(), klage2.getButikk());
        assertEquals(klage.getTidspunkt(), klage2.getTidspunkt());

        DataHandlerKlage.slettKlage(klage);
        assertTrue(DataHandlerKlage.hentKlager().isEmpty());
    }

    @Test
    public void test_Butikk_Read_Write() throws FileNotFoundException {
        deleteAllInAllFiles();

        Butikk butikk = new Butikk("test", "testing", "test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikk(butikk);
        Butikk butikk2 = DataHandlerButikk.hentButikker().get(0);
        assertEquals(butikk.getNavn(), butikk2.getNavn());
        assertEquals(butikk.getSpesialitet(), butikk2.getSpesialitet());
        assertEquals(butikk.getDagligLeder(), butikk2.getDagligLeder());
        assertEquals(butikk.getTidspunkt(), butikk2.getTidspunkt());


    }

    @Test
    public void test_Butikk_Holder_Vare() throws FileNotFoundException {
        deleteAllInAllFiles();

        Butikk butikk = new Butikk("test", "testing", "test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikk(butikk);

        Vare vare = new Vare("testVare", "testBeskrivelse", "test", 1001, "");
        DataHandlerVare.leggInnVareTest(vare);

        butikk.setVarerIButikk();
        ArrayList<Vare> vareListe = butikk.getVareListe();
        Vare testVare = vareListe.get(0);
        assertEquals(testVare.getButikk(), butikk.getNavn());
        assertEquals(testVare.getNavn(), vare.getNavn());

    }

    @Test
    public void test_Registrer_Salg_Fjern_Vare() throws FileNotFoundException {
        deleteAllInAllFiles();

        Butikk butikk = new Butikk("test butikk", "testing", "test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikk(butikk);

        Bruker bruker = new Bruker("Test", "Testeren");
        DataHandlerBruker.leggInnBruker(bruker);

        Vare vare = new Vare("testVare", "testBeskrivelse", "test butikk", 1001, "");
        DataHandlerVare.leggInnVareTest(vare);

        Vare vare2 = new Vare("testVare2", "testBeskrivelse", "test butikk", 1001, "");
        DataHandlerVare.leggInnVareTest(vare2);

        butikk.setVarerIButikk();

        Salg salg = new Salg(bruker.toString(), butikk.getNavn(), vare);
        DataHandlerSalg.registrerSalg(salg);

        ArrayList<Salg> salgListe = DataHandlerSalg.hentSalg();
        Salg testSalg = salgListe.get(0);

        assertEquals(salg.getKjoper(), testSalg.getKjoper());
        assertEquals(salg.getSelger(), testSalg.getSelger());
        assertEquals(salg.getSolgtVare().getNavn(), testSalg.getSolgtVare().getNavn());

        ArrayList<Vare> vareListe = DataHandlerVare.hentVarer();
        Vare testVare = vareListe.get(0);


        assertEquals(vare2.getNavn(), testVare.getNavn());

    }

    @Test
    public void test_Bruker_Read_Write() throws FileNotFoundException, InterruptedException {
        deleteAllInAllFiles();

        Bruker bruker = new Bruker("Test", "Testeren");
        DataHandlerBruker.leggInnBruker(bruker);
        Bruker bruker2 = DataHandlerBruker.hentBrukere().get(0);
        assertEquals(bruker.getFornavn(), bruker2.getFornavn());
        assertEquals(bruker.getEtternavn(), bruker2.getEtternavn());
        assertEquals(bruker.getBrukerOpprettet(), bruker2.getBrukerOpprettet());
        assertEquals(bruker.getSisteInnlogging(), bruker2.getSisteInnlogging());
        sleep(100);
        DataHandlerBruker.oppdaterSisteInnlogging(bruker);
        bruker2 = DataHandlerBruker.hentBrukere().get(0);
        assertNotEquals(bruker.getSisteInnlogging(), bruker2.getSisteInnlogging());

    }

    @Test
    public void test_Vare_Slett_Vare() throws FileNotFoundException {
        deleteAllInAllFiles();

        Vare vare = new Vare("testVare", "testBeskrivelse", "test", 1001, "");
        DataHandlerVare.leggInnVareTest(vare);
        DataHandlerVare.slettVare(vare);
        assertTrue(DataHandlerKlage.hentKlager().isEmpty());

    }

    public static void deleteAllInAllFiles() throws FileNotFoundException {
        deleteAllInFile(DataHandlerPaths.getBrukerPath());
        deleteAllInFile(DataHandlerPaths.getButikkPath());
        deleteAllInFile(DataHandlerPaths.getKlagePath());
        deleteAllInFile(DataHandlerPaths.getSalgPath());
        deleteAllInFile(DataHandlerPaths.getVarePath());
    }

    private static void deleteAllInFile(String path) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + path);
        writer.close();
    }
}
