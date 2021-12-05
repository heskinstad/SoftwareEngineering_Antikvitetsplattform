package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sample.model.Butikk;
import sample.model.Salg;
import sample.model.Vare;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataHandlerSalg extends DataHandlerPaths {

    static void registrerSalg(Salg salg, String localPath, String localVarePath, String localButikkPath){
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + localPath;

            if (!new File(path).isFile()) {
                return;
            }

            ArrayList<Salg> salgListe = hentSalg(localPath);

            salgListe.add(salg);

            objectMapper.writeValue(new File(path), salgListe);

            ArrayList<Butikk> butikkListe = DataHandlerButikk.hentButikker(localButikkPath);

            Butikk butikkSomSelger = getButikkSomSelger(salg, butikkListe);

            butikkSomSelger.setVarerIButikk(localVarePath);

            fjernVare(salg.getSolgtVare(), butikkSomSelger, localVarePath);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void registrerSalg(Salg salg) { registrerSalg(salg, salgPath, varePath, butikkPath); }

    static Butikk getButikkSomSelger(Salg salg, ArrayList<Butikk> butikkListe) {
        Butikk butikkSomSelger = null;

        for (Butikk enButikk : butikkListe){
            if(enButikk.getNavn().equals(salg.getSelger())) {
                butikkSomSelger = enButikk;
                break;
            }
        }
        return butikkSomSelger;
    }

    static ArrayList<Salg> hentSalg(String localPath){
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String path = new File("").getAbsolutePath() + localPath;

        try {
            ArrayList<Salg> salgListe = (ArrayList<Salg>) objectMapper.readValue(new File(path), new TypeReference<List<Salg>>(){});
            return salgListe;
        }
        catch (JsonMappingException e){
            System.out.println("Klarte ikke å lese data fra salg-JSON");
            return new ArrayList<Salg>();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Salg> hentSalg() { return hentSalg(salgPath); }

    static void fjernVare(Vare solgtVare, Butikk butikk, String localPath){
        ArrayList<Vare> varerFraButikk = butikk.getVareListe();
        ArrayList<Vare> alleVarerListe = DataHandlerVare.hentVarer(localPath);

        int i;
        for(i = 0; i < varerFraButikk.size(); i++){
            if(varerFraButikk.get(i).getNavn().equals(solgtVare.getNavn())){
                break;
            }
        }

        if(i < varerFraButikk.size()){
            varerFraButikk.remove(i);
        }

        for(Vare vareIListe : alleVarerListe){
            if(!vareIListe.getButikk().equals(solgtVare.getButikk()))
                varerFraButikk.add(vareIListe);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String path = new File("").getAbsolutePath() + localPath;

            objectMapper.writeValue(new File(path), varerFraButikk);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
