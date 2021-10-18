package sample.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import sample.model.Klage;
import sample.model.Vare;

import java.io.*;


public class DataHandler {

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
            System.out.println("Skriver til " + path);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            mapper.writerWithDefaultPrettyPrinter().writeValue(out, vare);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Klage lastInnKlage(String localPath) {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();

            // convert JSON string to Klage object
            String path = new File("").getAbsolutePath() + localPath;
            System.out.println("Laster inn fra " + path);
            Klage klage = mapper.readValue(new FileReader(path), Klage.class);

            return klage;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
