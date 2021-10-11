package sample.model;

import java.net.URI;
import java.time.LocalDateTime;

public class Vare {
    int id;
    String navn, beskrivelse, butikk;
    double pris;
    LocalDateTime tidspunkt;
    URI bildeURL;

    /**
     * Konstruktør med all data for varen
     * @param id unik id for hver vare
     * @param navn navnet på varen
     * @param beskrivelse beskrivelse av varen
     * @param butikk navnet på butikken som har lagt varen ut for salg
     * @param pris prisen på varen i NOK
     * @param tidspunkt tidspunktet varen ble lagt ut
     * @param bildeURL pathen der bildet hentes fra
     */
    public Vare(int id, String navn, String beskrivelse, String butikk, double pris, LocalDateTime tidspunkt, URI bildeURL) {
        this.id = id;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.butikk = butikk;
        this.pris = pris;
        this.tidspunkt = tidspunkt;
        this.bildeURL = bildeURL;
    }

    //get-set-metoder

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNavn() {return navn;}
    public void setNavn(String navn) {this.navn = navn;}

    public String getBeskrivelse() {return beskrivelse;}
    public void setBeskrivelse(String beskrivelse) {this.beskrivelse = beskrivelse;}

    public String getButikk() {return butikk;}
    public void setButikk(String butikk) {this.butikk = butikk;}

    public double getPris() {return pris;}
    public void setPris(double pris) {this.pris = pris;}

    public LocalDateTime getTidspunkt() {return tidspunkt;}
    public void setTidspunkt(LocalDateTime tidspunkt) {this.tidspunkt = tidspunkt;}

    public URI getBildeURL() {return bildeURL;}
    public void setBildeURL(URI bildeURL) {this.bildeURL = bildeURL;}
}
