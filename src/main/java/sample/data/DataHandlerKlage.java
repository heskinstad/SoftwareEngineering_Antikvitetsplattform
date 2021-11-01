package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.ObservableList;
import sample.model.Bruker;
import sample.model.Klage;
import sample.model.Vare;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


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
    public static void leggInnKlage(Klage klage) {
        leggInnKlage(klage, "/src/main/resources/JSON/klager.JSON");
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
                System.out.println("Klarte ikke å lese data fra klage-JSON");
            }
            return new ArrayList<Klage>();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Klage> hentKlager() {
        return hentKlager("/src/main/resources/JSON/klager.JSON");
    }


    public static void slettKlage(Klage klager, String localPath) {
        String klagerId = klager.getId().toString();

        ArrayList<Klage> aKlager = hentKlager();
        int i;
        for (i = 0; i < aKlager.size() ; i++) {
            if (aKlager.get(i).getId().toString().equals(klagerId)) {
                break;
            }
        }
        if (i < aKlager.size()) {
            aKlager.remove(i);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + localPath;

            objectMapper.writeValue(new File(path), aKlager);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void slettKlage(Klage klage) {
        slettKlage(klage, "/src/main/resources/JSON/klager.JSON");
    }
}
