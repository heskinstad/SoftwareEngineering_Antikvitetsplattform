package sample.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import sample.model.Vare;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.time.LocalDateTime;


public class DataHandler_varer {

    /*public static void main(String[] args) throws Exception {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON string to Vare object
            Vare vare = mapper.readValue(Paths.get("../../../resources/JSON/varer.JSON").toFile(), Vare.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static Vare lastInnVare() {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();

            // convert JSON string to Vare object
            //Vare vare = mapper.readValue(new File("/varer.JSON"), Vare.class);
            String path = new File("").getAbsolutePath() + "/src/main/java/resources/JSON/varer.JSON";
            System.out.println("Laster inn fra " + path);
            Vare vare = mapper.readValue(new FileReader(path), Vare.class);

            return vare;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void skrivTilJSON(Vare vare) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            String path = new File("").getAbsolutePath() + "/src/main/java/resources/JSON/varer.JSON";
            System.out.println("Skriver til " + path);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), vare);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
