package sample.model;

import java.time.LocalDateTime;

public class Butikk {
    int id;
    String navn, spesialitet, dagligLeder;
    LocalDateTime tidRegistret;

    public Butikk(int id, String navn, String spesialitet, String dagligLeder, LocalDateTime tidRegistret) {
        this.id = id;
        this.navn = navn;
        this.spesialitet = spesialitet;
        this.dagligLeder = dagligLeder;
        this.tidRegistret = tidRegistret;
    }

    public Butikk(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getTidRegistret() {
        return tidRegistret;
    }

    public void setTidRegistret(LocalDateTime tidRegistret) {
        this.tidRegistret = tidRegistret;
    }
}
