package sample.data;

import sample.model.Butikk;
import sample.model.Vare;

import java.util.ArrayList;

public class DataHandlerVaretoButikk {

    private static ArrayList<Vare> varerIButikk = new ArrayList<Vare>();

    public static void setVarerIButikk(Butikk butikk){
        setVarerIButikk(butikk, "/src/main/resources/JSON/varer.JSON");
    }

    public static void setVarerIButikk(Butikk butikk, String path){
        for(Vare vare : DataHandlerVare.hentVarer(path)){
            if(vare.getButikk().equals(butikk.getNavn())){
                varerIButikk.add(vare);
            }
        }
    }

    public static ArrayList<Vare> getVarerIButikk(){
        return varerIButikk;
    }
}
