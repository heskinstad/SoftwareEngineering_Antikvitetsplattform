package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import sample.model.Klage;
import sample.model.Vare;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class DataHandlerVare {

    public static void leggInnVare(Vare vare, String localPath, File imagePath) {
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

            //Kopierer over bilde til resources/images/
            if (!Objects.equals(imagePath.toString(), "")) {
                File file = new File(new File("").getAbsolutePath() + "/src/main/resources/images/" + vare.getBildeURL());
                Files.copy(imagePath.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void leggInnVare(Vare vare, File imagePath) {
        leggInnVare(vare, "/src/main/resources/JSON/varer.JSON", imagePath);
    }

    public static ArrayList<Vare> hentVarer(String localPath) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String path = new File("").getAbsolutePath() + localPath;

        if (!new File(path).isFile()) {
            System.out.println("Fil eksisterer ikke. return null");
            return null;
        }

        try {
            ArrayList<Vare> varer = (ArrayList<Vare>) objectMapper.readValue(new File(path), new TypeReference<List<Vare>>(){});
            return varer;
        }
        catch (JsonMappingException e) {
            System.out.println("Klarte ikke å lese data fra vare-JSON");
        }
        catch (Exception e) {
            return null;
        }
        return new ArrayList<Vare>();
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

    public static Image hentVareBilde(String navn) {
        String path = new File("").getAbsolutePath() + "\\src\\main\\resources\\images\\" + navn;
        File file = new File(path);
        System.out.println("Henter bilde fra " + path);
        Image image = new Image(file.toURI().toString());
        return image;
    }





}
