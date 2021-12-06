package sample.data;

public class DataHandlerPaths {

    static String brukerPath = "/src/main/resources/JSON/brukere.JSON";
    static String butikkPath = "/src/main/resources/JSON/butikker.JSON";
    static String klagePath = "/src/main/resources/JSON/klager.JSON";
    static String salgPath = "/src/main/resources/JSON/salg.JSON";
    static String varePath = "/src/main/resources/JSON/varer.JSON";
    static String imagePath = "/src/main/resources/images/";


    public static void setPathsTest() {
        brukerPath = "/src/test/resources/JSON/testBruker.JSON";
        butikkPath = "/src/test/resources/JSON/testButikker.JSON";
        klagePath = "/src/test/resources/JSON/testKlager.JSON";
        salgPath = "/src/test/resources/JSON/testSalg.JSON";
        varePath = "/src/test/resources/JSON/testVarer.JSON";
        imagePath = "/src/test/resources/images/";
    }

    public static String getBrukerPath() {
        return brukerPath;
    }

    public static String getButikkPath() {
        return butikkPath;
    }

    public static String getKlagePath() {
        return klagePath;
    }

    public static String getSalgPath() {
        return salgPath;
    }

    public static String getVarePath() {
        return varePath;
    }

    public static String getImagePath() {
        return imagePath;
    }
}
