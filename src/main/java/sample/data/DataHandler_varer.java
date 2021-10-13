package sample.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import sample.model.Vare;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;


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

            // convert JSON string to Vare object
            //Vare vare = mapper.readValue(new File("/varer.JSON"), Vare.class);
            String json = "{\"id\": \"1\",\"navn\": \"test\",\"beskrivelse\": \"test\",\"butikk\": \"test\",\"pris\": \"69\"}";
            String path = new File("").getAbsolutePath() + "/src/main/java/resources/JSON/varer.JSON";
            Vare vare = mapper.readValue(new FileReader(path), Vare.class);

            return vare;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
