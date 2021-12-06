package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.scene.image.Image;
import sample.model.Vare;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DataHandlerVare extends DataHandlerPaths {

    public static void leggInnVare(Vare vare, File imagePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + varePath;

            //Henter gamle klager
            ArrayList<Vare> varer = hentVarer();

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

    public static void leggInnVareTest(Vare vare) { leggInnVare(vare, new File("")); }

    public static ArrayList<Vare> hentVarer() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String path = new File("").getAbsolutePath() + varePath;

        try {
            ArrayList<Vare> varer = (ArrayList<Vare>) objectMapper.readValue(new File(path), new TypeReference<List<Vare>>(){});
            return varer;
        }
        catch (JsonMappingException e) {
            //System.out.println("Klarte ikke å lese data fra vare-JSON");
        }
        catch (Exception e) {
            return null;
        }
        return new ArrayList<Vare>();
    }

    public static void slettVare(Vare varer) {
        String varerId = varer.getId().toString();

        ArrayList<Vare> aVarer = hentVarer();
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
            String path = new File("").getAbsolutePath() + varePath;

            objectMapper.writeValue(new File(path), aVarer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Image hentVareBilde(String bildenavn) {
        String path = new File("").getAbsolutePath() + imagePath + bildenavn;
        File file = new File(path);
        if (!file.isFile()) {
            System.out.println("Fant ikke følgende bilde: " + path);
            return null;
        }
        Image image = new Image(file.toURI().toString());
        return image;
    }
}
