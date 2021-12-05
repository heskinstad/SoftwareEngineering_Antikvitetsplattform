package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sample.model.Klage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DataHandlerKlage extends DataHandlerPaths {

    public static void leggInnKlage(Klage klage) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + klagePath;

            //Henter gamle klager
            ArrayList<Klage> klager = hentKlager();

            //Legger ny klage etter de gamle klagene
            klager.add(klage);

            objectMapper.writeValue(new File(path), klager);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Klage> hentKlager() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String path = new File("").getAbsolutePath() + klagePath;

        try {
            ArrayList<Klage> klager = (ArrayList<Klage>) objectMapper.readValue(new File(path), new TypeReference<List<Klage>>(){});
            return klager;
        }
        catch (JsonMappingException e) {
            System.out.println("Klarte ikke å lese data fra klage-JSON");
        }
        catch (Exception e) {
            return null;
        }
        return new ArrayList<Klage>();
    }

    public static void slettKlage(Klage klager) {
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
            String path = new File("").getAbsolutePath() + klagePath;

            objectMapper.writeValue(new File(path), aKlager);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
