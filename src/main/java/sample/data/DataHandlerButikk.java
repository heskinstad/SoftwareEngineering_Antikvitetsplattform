package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.ObservableList;
import sample.model.Butikk;
import sample.model.Klage;
import sample.model.Vare;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataHandlerButikk {

    static Butikk valgtButikk;

    public static void registrerButikk(Butikk butikk, String localPath){
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + localPath;

            ArrayList<Butikk> butikker = hentButikker(localPath);

            butikker.add(butikk);

            objectMapper.writeValue(new File(path), butikker);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void registrerButikk(Butikk butikk) {
        registrerButikk(butikk, "/src/main/resources/JSON/butikker.JSON");
    }

    public static ArrayList<Butikk> hentButikker(String localPath){
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String path = new File("").getAbsolutePath() + localPath;

        if (!new File(path).isFile()) {
            System.out.println("Fil eksisterer ikke. return null");
            return null;
        }

        try {
            ArrayList<Butikk> butikker = (ArrayList<Butikk>) objectMapper.readValue(new File(path), new TypeReference<List<Butikk>>(){});
            return butikker;
        }
        catch (JsonMappingException e){
            System.out.println("Klarte ikke å lese data fra butikk-JSON");
        }
        catch (Exception e){
            return null;
        }
        return new ArrayList<Butikk>();
    }

    public static ArrayList<Butikk> hentButikker() {
        return hentButikker("/src/main/resources/JSON/butikker.JSON");
    }

}
