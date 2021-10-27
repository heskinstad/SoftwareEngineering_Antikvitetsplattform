package sample.model;

import java.time.LocalDateTime;

public class Butikk {
    String navn, spesialitet, dagligLeder;
    LocalDateTime tidspunkt;

    /**
     * Konstruktør for butikk. Ingen butikker skal hete det samme
     * @param navn
     * @param spesialitet
     * @param dagligLeder
     */
    public Butikk(String navn, String spesialitet, String dagligLeder) {

        this.navn = navn;
        this.spesialitet = spesialitet;
        this.dagligLeder = dagligLeder;
        this.tidspunkt = LocalDateTime.now();
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
}
