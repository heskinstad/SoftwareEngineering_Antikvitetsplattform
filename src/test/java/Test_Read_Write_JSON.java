package java;

import org.junit.Test;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerKlage;
import sample.data.DataHandlerVare;
import sample.model.Butikk;
import sample.model.Klage;
import sample.model.Vare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test_Read_Write_JSON {

    @Test
    public void test_Vare_Read_Write() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/src/test/resources/testVarer.JSON");
        writer.close();

        Vare vare = new Vare("testVare", "testBeskrivelse", "testButikk", 1001, "");
        DataHandlerVare.leggInnVare(vare, "/src/test/resources/testVarer.JSON");
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
        DataHandlerVare.leggInnVare(vare, "/src/test/resources/testVarer.JSON");

        butikk.setVarerIButikk("/src/test/resources/testVarer.JSON");
        ArrayList<Vare> vareListe = butikk.getVareListe();
        Vare testVare = vareListe.get(0);
        assertEquals(testVare.getButikk(), butikk.getNavn());
        assertEquals(testVare.getNavn(), vare.getNavn());

    }


}
