import org.junit.Test;
import sample.data.*;
import sample.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test_Read_Write_JSON {

    @Test
    public void test_Vare_Read_Write() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/testVarer.JSON");
        writer.close();

        Vare vare = new Vare("testVare", "testBeskrivelse", "testButikk", 1001, "");
        DataHandlerVare.leggInnVare(vare, "/src/test/resources/testVarer.JSON", new File(""));
        Vare vare2 = DataHandlerVare.hentVarer("/src/test/resources/testVarer.JSON").get(0);
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
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/testKlager.JSON");
        writer.close();

        Klage klage = new Klage("Ola Nordmann", "Butikken deres er tull og humbug", "Krakkel og spetakkel og andre antivkiteter");
        DataHandlerKlage.leggInnKlage(klage, "/src/test/resources/testKlager.JSON");
        Klage klage2 = DataHandlerKlage.hentKlager("/src/test/resources/testKlager.JSON").get(0);
        assertEquals(klage.getId(), klage2.getId());
        assertEquals(klage.getNavn(), klage2.getNavn());
        assertEquals(klage.getMelding(), klage2.getMelding());
        assertEquals(klage.getButikk(), klage2.getButikk());
        assertEquals(klage.getTidspunkt(), klage2.getTidspunkt());

        DataHandlerKlage.slettKlage(klage, "/src/test/resources/testKlager.JSON");
        assertTrue(DataHandlerKlage.hentKlager("/src/test/resources/testKlager.JSON").isEmpty());
    }

    @Test
    public void test_Butikk_Read_Write() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/testButikker.JSON");
        writer.close();

        Butikk butikk = new Butikk("test", "testing","test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikk(butikk, "/src/test/resources/testButikker.JSON");
        Butikk butikk2 = DataHandlerButikk.hentButikker("/src/test/resources/testButikker.JSON").get(0);
        assertEquals(butikk.getNavn(), butikk2.getNavn());
        assertEquals(butikk.getSpesialitet(), butikk2.getSpesialitet());
        assertEquals(butikk.getDagligLeder(), butikk2.getDagligLeder());
        assertEquals(butikk.getTidspunkt(), butikk2.getTidspunkt());



    }

    @Test
    public void test_Butikk_Holder_Vare() throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/testButikker.JSON");
        PrintWriter writer2 = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/testVarer.JSON");
        writer.close();
        writer2.close();

        Butikk butikk = new Butikk("test", "testing","test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikk(butikk, "/src/test/resources/testButikker.JSON");

        Vare vare = new Vare("testVare", "testBeskrivelse", "test", 1001, "");
        DataHandlerVare.leggInnVare(vare, "/src/test/resources/testVarer.JSON", new File(""));

        butikk.setVarerIButikk("/src/test/resources/testVarer.JSON");
        ArrayList<Vare> vareListe = butikk.getVareListe();
        Vare testVare = vareListe.get(0);
        assertEquals(testVare.getButikk(), butikk.getNavn());
        assertEquals(testVare.getNavn(), vare.getNavn());

    }

    @Test
    public void test_Registrer_Salg_Fjern_Vare() throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/testButikker.JSON");
        PrintWriter writer2 = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/testVarer.JSON");
        PrintWriter writer3 = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/testSalg.JSON");
        PrintWriter writer4 = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/testBruker.JSON");
        writer.close();
        writer2.close();
        writer3.close();
        writer4.close();

        Butikk butikk = new Butikk("test", "testing","test kompani", "Denne butikken er kun en test");
        DataHandlerButikk.registrerButikk(butikk, "/src/test/resources/testButikker.JSON");

        Bruker bruker = new Bruker("Test", "Testeren");
        DataHandlerBruker.leggInnBruker(bruker, "/src/test/resources/testBruker.JSON");

        Vare vare = new Vare("testVare", "testBeskrivelse", "test", 1001, "");
        DataHandlerVare.leggInnVare(vare, "/src/test/resources/testVarer.JSON", new File(""));

        Vare vare2 = new Vare("testVare2", "testBeskrivelse", "test", 1001, "");
        DataHandlerVare.leggInnVare(vare2, "/src/test/resources/testVarer.JSON", new File(""));

        butikk.setVarerIButikk("/src/test/resources/testVarer.JSON");

        Salg salg = new Salg(bruker, butikk, vare);
        DataHandlerSalg.registrerSalg(salg, "/src/test/resources/testSalg.JSON", "/src/test/resources/testVarer.JSON");

        ArrayList<Salg> salgListe = DataHandlerSalg.hentSalg("/src/test/resources/testSalg.JSON");
        Salg testSalg = salgListe.get(0);

        assertEquals(salg.getKjoper().getFornavn(),testSalg.getKjoper().getFornavn());
        assertEquals(salg.getSelger().getNavn(),testSalg.getSelger().getNavn());
        assertEquals(salg.getSolgtVare().getNavn(),testSalg.getSolgtVare().getNavn());

        ArrayList<Vare> vareListe = DataHandlerVare.hentVarer("/src/test/resources/testVarer.JSON");
        Vare testVare = vareListe.get(0);

        assertEquals(vare2.getNavn(), testVare.getNavn());

    }


}
