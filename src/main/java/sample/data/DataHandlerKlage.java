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

    private static void leggInnKlage(Klage klage, String localPath) {
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
    public static void leggInnKlage(Klage klage) { leggInnKlage(klage, klagePath); }

    private static ArrayList<Klage> hentKlager(String localPath) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String path = new File("").getAbsolutePath() + localPath;

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

    public static ArrayList<Klage> hentKlager() { return hentKlager(klagePath); }


    private static void slettKlage(Klage klager, String localPath) {
        String klagerId = klager.getId().toString();

        ArrayList<Klage> aKlager = hentKlager(localPath);
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

    public static void slettKlage(Klage klage) { slettKlage(klage, klagePath); }
}
