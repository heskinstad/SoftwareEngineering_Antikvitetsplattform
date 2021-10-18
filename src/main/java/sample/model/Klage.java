package sample.model;

import java.time.LocalDateTime;

public class Klage {
    int id;
    String navn, melding, butikk;
    LocalDateTime tidspunkt;

    public Klage() {

    }

    /**
     * Konstruktør med all data for en klage
     * @param id unik id for hver klage
     * @param navn navn på bruker som har sendt inn klagen
     * @param melding klagemelding fra brukeren
     * @param butikk navn på butikken som brukeren har klaget på
     * @param tidspunkt tidspunktet klagen ble sendt inn
     */
    public Klage(int id, String navn, String melding, String butikk, LocalDateTime tidspunkt) {
        this.id = id;
        this.navn = navn;
        this.melding = melding;
        this.butikk = butikk;
        this.tidspunkt = tidspunkt;
    }

    //get-set-metoder

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNavn() {return navn;}
    public void setNavn(String navn) {this.navn = navn;}

    public String getMelding() {return melding;}
    public void setMelding(String melding) {this.melding = melding;}

    public String getButikk() {return butikk;}
    public void setButikk(String butikk) {this.butikk = butikk;}

    public LocalDateTime getTidspunkt() {return tidspunkt;}
    public void setTidspunkt(LocalDateTime tidspunkt) {this.tidspunkt = tidspunkt;}
}
