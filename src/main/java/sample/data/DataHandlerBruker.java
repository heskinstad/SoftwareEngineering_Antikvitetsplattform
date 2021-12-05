package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sample.model.Bruker;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataHandlerBruker extends DataHandlerPaths {

    public static void leggInnBruker(Bruker bruker) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + brukerPath;

            //Henter gamle klager
            ArrayList<Bruker> brukere = hentBrukere();

            //Legger ny klage etter de gamle klagene
            brukere.add(bruker);

            objectMapper.writeValue(new File(path), brukere);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Bruker> hentBrukere() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String path = new File("").getAbsolutePath() + brukerPath;

        try {
            ArrayList<Bruker> brukere = (ArrayList<Bruker>) objectMapper.readValue(new File(path), new TypeReference<List<Bruker>>(){});
            return brukere;
        }
        catch (JsonMappingException e) {
            System.out.println("Klarte ikke å lese data fra brukere-JSON");
        }
        catch (Exception e) {
            return null;
        }
        return new ArrayList<Bruker>();
    }

    public static void oppdaterSisteInnlogging(Bruker bruker) {
        String fornavn = bruker.getFornavn();
        String etternavn = bruker.getEtternavn();
        ArrayList<Bruker> brukere = DataHandlerBruker.hentBrukere();

        int i;
        for (i = 0; i < brukere.size() ; i++) {
            if (Objects.equals(brukere.get(i).getFornavn(), fornavn) && Objects.equals(brukere.get(i).getEtternavn(), etternavn)) {
                break;
            }
        }
        if (i < brukere.size()) {
            brukere.get(i).setSisteInnlogging(LocalDateTime.now());
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + brukerPath;

            objectMapper.writeValue(new File(path), brukere);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
