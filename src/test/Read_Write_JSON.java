import org.junit.Test;
import sample.data.DataHandlerVare;
import sample.model.Vare;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;


public class Read_Write_JSON {

    @Test
    public void test_Vare_Read_Write() {
        Vare vare1 = new Vare(0, "testVare", "testBeskrivelse", "testButikk", 1001, LocalDateTime.of(2012,12,21,12,12,12), "");

        Vare vare2 = DataHandlerVare.lastInnVare("/test/resources/testVarer.JSON");
        assertEquals(vare1.getId(), vare2.getId());
        assertEquals(vare1.getBeskrivelse(), vare2.getBeskrivelse());
        assertEquals(vare1.getButikk(), vare2.getButikk());
        assertEquals(vare1.getPris(), vare2.getPris());
        assertEquals(vare1.getTidspunkt(), vare2.getTidspunkt());
    }

    @Test
    public void test_Klage_Read_Write() {

    }
}
