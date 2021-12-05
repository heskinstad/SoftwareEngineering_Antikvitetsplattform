import sample.util.AntikkUtil;
import org.junit.Test;
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
}
