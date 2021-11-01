package sample.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Klage {
    UUID id;
    String navn, melding, butikk, slettValgteKlage;
    LocalDateTime tidspunkt;

    public Klage() {

    }

    /**
     * Konstruktør med all data for en klage
     * @param navn navn på bruker som har sendt inn klagen
     * @param melding klagemelding fra brukeren
     * @param butikk navn på butikken som brukeren har klaget på
     */
    public Klage(String navn, String melding, String butikk) {

        this.id = UUID.randomUUID();
        this.navn = navn;
        this.melding = melding;
        this.butikk = butikk;
        this.tidspunkt = LocalDateTime.now();
        this.slettValgteKlage = slettValgteKlage;
    }

    //get-set-metoder

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    public String getNavn() {return navn;}
    public void setNavn(String navn) {this.navn = navn;}

    public String getMelding() {return melding;}
    public void setMelding(String melding) {this.melding = melding;}

    public String getButikk() {return butikk;}
    public void setButikk(String butikk) {this.butikk = butikk;}

    public LocalDateTime getTidspunkt() {return tidspunkt;}
    public void setTidspunkt(LocalDateTime tidspunkt) {this.tidspunkt = tidspunkt;}

    public String getSlettValgteKlage() {
        return slettValgteKlage;
    }
    public void setSlettValgteKlage(String slettValgteKlage) {
        this.slettValgteKlage = slettValgteKlage;
    }
}
