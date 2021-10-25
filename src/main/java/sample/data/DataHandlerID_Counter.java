package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sample.model.ID_Counter;

import java.io.File;

public class DataHandlerID_Counter {

    public static ID_Counter hentIDer(String localPath) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            String path = new File("").getAbsolutePath() + localPath;
            try {
                ID_Counter IDer = objectMapper.readValue(new File(path), new TypeReference<ID_Counter>(){});
                return IDer;
            }
            catch (JsonMappingException e) {
                System.out.println("Klarte ikke å lese data fra JSON");
            }
            return new ID_Counter(1, 1, 1, 1, 1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // MÅ ALDRI KJØRES UTEN AT ID'ER HAR BLITT LASTET INN FRA JSON TIDLIGERE!!
    public static void skrivIDer(ID_Counter IDer, String localPath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + localPath;

            objectMapper.writeValue(new File(path), IDer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void oekBrukerID(String localPath) {
        ID_Counter IDer = hentIDer(localPath);
        IDer.oekBruker();
        skrivIDer(IDer ,localPath);
    }

    public static void oekButikkID(String localPath) {
        ID_Counter IDer = hentIDer(localPath);
        IDer.oekButikk();
        skrivIDer(IDer ,localPath);
    }

    public static void oekAdminID(String localPath) {
        ID_Counter IDer = hentIDer(localPath);
        IDer.oekAdmin();
        skrivIDer(IDer ,localPath);
    }

    public static void oekVareID(String localPath) {
        ID_Counter IDer = hentIDer(localPath);
        IDer.oekVare();
        skrivIDer(IDer ,localPath);
    }

    public static void oekKlageID(String localPath) {
        ID_Counter IDer = hentIDer(localPath);
        IDer.oekKlage();
        skrivIDer(IDer ,localPath);
    }

}
