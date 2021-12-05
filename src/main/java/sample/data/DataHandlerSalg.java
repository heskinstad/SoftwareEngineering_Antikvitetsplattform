package sample.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sample.model.Butikk;
import sample.model.Salg;
import sample.model.Vare;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class DataHandlerSalg {

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

    public static void registrerSalg(Salg salg) { registrerSalg(salg, "/src/main/resources/JSON/salg.JSON", "/src/main/resources/JSON/varer.JSON", "/src/main/resources/JSON/butikker.JSON"); }
    public static void registrerSalgTest(Salg salg) { registrerSalg(salg, "/src/test/resources/JSON/testSalg.JSON", "/src/test/resources/JSON/testVarer.JSON", "/src/test/resources/JSON/testButikker.JSON"); }

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

    public static ArrayList<Salg> hentSalg() { return hentSalg("/src/main/resources/JSON/salg.JSON"); }
    public static ArrayList<Salg> hentSalgTest() { return hentSalg("/src/test/resources/JSON/testSalg.JSON"); }

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
