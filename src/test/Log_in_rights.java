import org.junit.Test;
import sample.data.DataHandler;
import sample.model.Vare;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;


public class Log_in_rights {

    @Test
    public void Check_Item_Succesfull_Load() {
        Vare vare1 = new Vare(0, "testVare", "testBeskrivelse", "testButikk", 1001, LocalDateTime.of(2012,12,21,12,12,12), "");

        Vare vare2 = DataHandler.lastInnVare("/test/resources/testVarer.JSON");
        assertEquals(vare1.getId(), vare2.getId());
        assertEquals(vare1.getBeskrivelse(), vare2.getBeskrivelse());
        assertEquals(vare1.getButikk(), vare2.getButikk());
        assertEquals(vare1.getPris(), vare2.getPris());
        assertEquals(vare1.getTidspunkt(), vare2.getTidspunkt());
    }
}
