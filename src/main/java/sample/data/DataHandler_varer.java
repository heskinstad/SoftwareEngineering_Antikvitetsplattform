package sample.data;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sample.model.Vare;

import java.io.FileReader;
import java.nio.file.Paths;


public class DataHandler_varer {

    public static void main(String[] args) throws Exception {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON string to Vare object
            Vare vare = mapper.readValue(Paths.get("../../resources/JSON/varer.JSON").toFile(), Vare.class);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.getMessage();
        }
    }

}
