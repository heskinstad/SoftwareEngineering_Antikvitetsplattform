import org.junit.Test;
import sample.data.DataHandlerKlage;
import sample.data.DataHandlerVare;
import sample.model.Klage;
import sample.model.Vare;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;


public class Read_Write_JSON {

    @Test
    public void test_Vare_Read_Write() {
        Vare vare = new Vare(0, "testVare", "testBeskrivelse", "testButikk", 1001, LocalDateTime.of(2012,12,21,12,12,12), "");
        DataHandlerVare.leggInnVare(vare, "/test/resources/testVarer.JSON");
        assertEquals(vare, DataHandlerVare.hentVarer("/test/resources/testVarer.JSON").get(0));


    }

    @Test
    public void test_Klage_Read_Write() {
        Klage klage = new Klage(0, "Ola Nordmann", "Butikken deres er tull og humbug", "Krakkel og spetakkel og andre antivkiteter", LocalDateTime.now());
        DataHandlerKlage.leggInnKlage(klage, "/test/resources/testKlager.JSON");
        assertEquals(klage, DataHandlerKlage.hentKlager("/test/resources/testKlager.JSON").get(0));
    }
}
