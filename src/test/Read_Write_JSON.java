import org.junit.Test;
import sample.data.DataHandlerButikk;
import sample.data.DataHandlerKlage;
import sample.data.DataHandlerVare;
import sample.data.DataHandlerID_Counter;
import sample.model.Butikk;
import sample.model.Klage;
import sample.model.Vare;
import sample.model.ID_Counter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class Read_Write_JSON {

    @Test
    public void test_Vare_Read_Write() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/test/resources/testVarer.JSON");
        writer.close();

        Vare vare = new Vare("testVare", "testBeskrivelse", "testButikk", 1001, "");
        DataHandlerVare.leggInnVare(vare, "/test/resources/testVarer.JSON");
        Vare vare2 = DataHandlerVare.hentVarer("/test/resources/testVarer.JSON").get(0);
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
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/test/resources/testKlager.JSON");
        writer.close();

        Klage klage = new Klage("Ola Nordmann", "Butikken deres er tull og humbug", "Krakkel og spetakkel og andre antivkiteter");
        DataHandlerKlage.leggInnKlage(klage, "/test/resources/testKlager.JSON");
        Klage klage2 = DataHandlerKlage.hentKlager("/test/resources/testKlager.JSON").get(0);
        assertEquals(klage.getId(), klage2.getId());
        assertEquals(klage.getNavn(), klage2.getNavn());
        assertEquals(klage.getMelding(), klage2.getMelding());
        assertEquals(klage.getButikk(), klage2.getButikk());
        assertEquals(klage.getTidspunkt(), klage2.getTidspunkt());
    }

    @Test
    public void test_Butikk_Read_Write() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/test/resources/testButikker.JSON");
        writer.close();

        Butikk butikk = new Butikk("test", "testing","test kompani");
        DataHandlerButikk.registrerButikk(butikk, "/test/resources/testButikker.JSON");
        Butikk butikk2 = DataHandlerButikk.hentButikker("/test/resources/testButikker.JSON").get(0);
        assertEquals(butikk.getId(), butikk2.getId());
        assertEquals(butikk.getNavn(), butikk2.getNavn());
        assertEquals(butikk.getSpesialitet(), butikk2.getSpesialitet());
        assertEquals(butikk.getDagligLeder(), butikk2.getDagligLeder());
        assertEquals(butikk.getTidspunkt(), butikk2.getTidspunkt());

    }

    @Test
    public void test_ID_Read_Write() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/test/resources/testID.JSON");
        writer.close();

        ID_Counter IDer = DataHandlerID_Counter.hentIDer("/test/resources/testID.JSON");
        IDer.oekBruker();
        IDer.oekBruker();
        IDer.oekAdmin();
        IDer.oekAdmin();
        IDer.oekVare();
        IDer.oekKlage();
        DataHandlerID_Counter.skrivIDer(IDer, "/test/resources/testID.JSON");

        ID_Counter IDer2 = DataHandlerID_Counter.hentIDer("/test/resources/testID.JSON");
        assertEquals(IDer.getBruker(), IDer2.getBruker());
        assertEquals(IDer.getButikk(), IDer2.getButikk());
        assertEquals(IDer.getAdmin(), IDer2.getAdmin());
        assertEquals(IDer.getVare(), IDer2.getVare());
        assertEquals(IDer.getKlage(), IDer2.getKlage());
    }
}
