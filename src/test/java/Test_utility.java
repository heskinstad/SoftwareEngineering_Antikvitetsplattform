import sample.model.Vare;
import sample.util.AntikkUtil;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Test_utility {

    @Test
    public void Test_get_index() {
        assertEquals(0, AntikkUtil.getTrueVareArrayStartIndex(1, 1));
        assertEquals(90, AntikkUtil.getTrueVareArrayStartIndex(10, 10));
        assertEquals(10, AntikkUtil.getTrueVareArrayStartIndex(3, 5));
        assertEquals(44, AntikkUtil.getTrueVareArrayStartIndex(5, 11));
        assertEquals(42, AntikkUtil.getTrueVareArrayStartIndex(3, 21));
        assertEquals(32, AntikkUtil.getTrueVareArrayStartIndex(2, 32));
        assertEquals(32, AntikkUtil.getTrueVareArrayStartIndex(3, 16));
        assertEquals(32, AntikkUtil.getTrueVareArrayStartIndex(5, 8));
        assertEquals(32, AntikkUtil.getTrueVareArrayStartIndex(9, 4));
        assertEquals(32, AntikkUtil.getTrueVareArrayStartIndex(17, 2));
        assertEquals(32, AntikkUtil.getTrueVareArrayStartIndex(33, 1));
    }

    @Test
    public void Test_get_vare_med_butikk_navn() {
        ArrayList<Vare> testArr = new ArrayList();
        testArr.add(new Vare("", "", "antikk & co", 0, ""));
        testArr.add(new Vare("", "", "not antikk & co", 1, ""));
        testArr.add(new Vare("", "", "antikk & co", 2, ""));
        testArr.add(new Vare("", "", "ikkje antikk & co", 3, ""));
        testArr.add(new Vare("", "", "ye olde' antikk & co", 4, ""));

        String butikk = "antikk & co";

        testArr = AntikkUtil.GetVareMedButikkNavn(testArr, butikk);

        assertEquals(butikk, testArr.get(0).getButikk());
        assertEquals(butikk, testArr.get(1).getButikk());
        assertEquals(2, testArr.size());
    }
}
