package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sample.model.Butikk;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandlerButikk extends DataHandlerPaths {

    static Butikk valgtButikk;

    public static void registrerButikk(Butikk butikk){
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + butikkPath;

            if (!new File(path).isFile()) {
                return;
            }

            ArrayList<Butikk> butikker = hentButikker();

            butikker.add(butikk);

            objectMapper.writeValue(new File(path), butikker);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Butikk> hentButikker(){
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String path = new File("").getAbsolutePath() + butikkPath;

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

}
