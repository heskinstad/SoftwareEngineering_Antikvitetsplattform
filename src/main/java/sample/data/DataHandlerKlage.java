package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sample.model.Klage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataHandlerKlage {
    public static void leggInnKlage(Klage klage, String localPath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + localPath;

            //Henter gamle klager
            ArrayList<Klage> klager = hentKlager(localPath);

            //Legger ny klage etter de gamle klagene
            klager.add(klage);

            objectMapper.writeValue(new File(path), klager);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Klage> hentKlager(String localPath) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            String path = new File("").getAbsolutePath() + localPath;
            try {
                ArrayList<Klage> klager = objectMapper.readValue(new File(path), new TypeReference<List<Klage>>(){});
                return klager;
            }
            catch (JsonMappingException e) {
                System.out.println("Klarte ikke å lese data fra JSON");
            }
            return new ArrayList<Klage>();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
