package sample.model;

import sample.data.DataHandlerVare;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Butikk {
    String navn, spesialitet, dagligLeder, beskrivelse;
    LocalDateTime tidspunkt;
    ArrayList<Vare> vareListe;

    /**
     * Konstruktør for butikk. Ingen butikker skal hete det samme
     * @param navn
     * @param spesialitet
     * @param dagligLeder
     * @param beskrivelse
     * @param vareListe
     */
    public Butikk(String navn, String spesialitet, String dagligLeder, String beskrivelse) {

        this.navn = navn;
        this.spesialitet = spesialitet;
        this.dagligLeder = dagligLeder;
        this.tidspunkt = LocalDateTime.now();
        this.beskrivelse = beskrivelse;
        this.vareListe = new ArrayList<Vare>();
    }

    public Butikk(){

    }

    public String getNavn() {
        return navn;
    }
    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getSpesialitet() {
        return spesialitet;
    }
    public void setSpesialitet(String spesialitet) {
        this.spesialitet = spesialitet;
    }

    public String getDagligLeder() {
        return dagligLeder;
    }
    public void setDagligLeder(String dagligLeder) {
        this.dagligLeder = dagligLeder;
    }

    public LocalDateTime getTidspunkt() {
        return tidspunkt;
    }
    public void setTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
    }

    @Override
    public String toString() {
        return navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public ArrayList<Vare> getVareListe() {
        return vareListe;
    }

    public void setVarerIButikk(){
        setVarerIButikk("/src/main/resources/JSON/varer.JSON");
    }

    public void setVarerIButikk(String path){
        for(Vare vare : DataHandlerVare.hentVarer(path)){
            if(vare.getButikk().equals(this.getNavn())){
                vareListe.add(vare);
            }
        }
    }
}
