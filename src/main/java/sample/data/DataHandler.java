package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import sample.model.Klage;
import sample.model.Vare;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataHandler {

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
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            String path = new File("").getAbsolutePath() + localPath;
            System.out.println("Skriver vare til " + path);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            mapper.writerWithDefaultPrettyPrinter().writeValue(out, vare);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leggInnKlage(Klage klage, String localPath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            String path = new File("").getAbsolutePath() + localPath;
            System.out.println("Skriver klage til " + path);
            PrintWriter out_Klage = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            mapper.writerWithDefaultPrettyPrinter().writeValue(out_Klage, klage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // OLD, SOON TO BE DELETED
    public static ArrayList<Klage> lastInnKlager(String localPath) {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();

            // convert JSON string to a list of Klage objects
            String path = new File("").getAbsolutePath() + localPath;
            System.out.println("Laster inn fra " + path);
            ArrayList<Klage> klager = new ArrayList<>(Arrays.asList(mapper.readValue(new FileReader(path), Klage.class)));

            return klager;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Klage> lastInnKlagerV2(String localPath) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            String path = new File("").getAbsolutePath() + localPath;
            ArrayList<Klage> klager = objectMapper.readValue(new File(path), new TypeReference<List<Klage>>(){});
            return klager;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
