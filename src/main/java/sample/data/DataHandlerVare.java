package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.ObservableList;
import sample.model.Klage;
import sample.model.Vare;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataHandlerVare {

    public static Vare lastInnVare(String localPath) {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();

            // convert JSON string to Vare object
            //Vare vare = mapper.readValue(new File("/varer.JSON"), Vare.class);
            String path = new File("").getAbsolutePath() + localPath;
            System.out.println("Laster inn fra " + path);
            Vare vare = mapper.readValue(new FileReader(path), Vare.class);

            return vare;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void leggInnVare(Vare vare, String localPath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + localPath;

            //Henter gamle klager
            ArrayList<Vare> varer = hentVarer(localPath);

            //Legger ny klage etter de gamle klagene
            varer.add(vare);

            objectMapper.writeValue(new File(path), varer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void leggInnVare(Vare vare) {
        leggInnVare(vare, "/src/main/resources/JSON/varer.JSON");
    }

    public static ArrayList<Vare> hentVarer(String localPath) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            String path = new File("").getAbsolutePath() + localPath;
            try {
                ArrayList<Vare> varer = (ArrayList<Vare>) objectMapper.readValue(new File(path), new TypeReference<List<Vare>>(){});
                return varer;
            }
            catch (JsonMappingException e) {
                System.out.println("Klarte ikke å lese data fra vare-JSON");
            }
            return new ArrayList<Vare>();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Vare> hentVarer() {
        return hentVarer("/src/main/resources/JSON/varer.JSON");
    }

    public static void slettVare(Vare varer, String localPath) {
        String varerId = varer.getId().toString();

        ArrayList<Vare> aVarer = hentVarer(localPath);
        int i;
        for (i = 0; i < aVarer.size() ; i++) {
            if (aVarer.get(i).getId().toString().equals(varerId)) {
                break;
            }
        }
        if (i < aVarer.size()) {
            aVarer.remove(i);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + localPath;

            objectMapper.writeValue(new File(path), aVarer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void slettVare(Vare vare) {
        slettVare(vare, "/src/main/resources/JSON/varer.JSON");
    }




}
