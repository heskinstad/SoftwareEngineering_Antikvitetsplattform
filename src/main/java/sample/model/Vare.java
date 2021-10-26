package sample.model;

import sample.data.DataHandlerID_Counter;

import java.time.LocalDateTime;

public class Vare {
    int id;
    String navn, beskrivelse, butikk;
    int pris;
    LocalDateTime tidspunkt;
    String bildeURL;

    public Vare() {

    }

    /**
     * Konstruktør med all data for varen
     * @param navn navnet på varen
     * @param beskrivelse beskrivelse av varen
     * @param butikk navnet på butikken som har lagt varen ut for salg
     * @param pris prisen på varen i NOK
     * @param bildeURL pathen der bildet hentes fra
     */
    public Vare(String navn, String beskrivelse, String butikk, int pris, String bildeURL) {

        DataHandlerID_Counter.oekVareID();

        this.id = DataHandlerID_Counter.hentIDer().getVare();
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.butikk = butikk;
        this.pris = pris;
        this.tidspunkt = LocalDateTime.now();
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

    public int getPris() {return pris;}
    public void setPris(int pris) {this.pris = pris;}

    public LocalDateTime getTidspunkt() {return tidspunkt;}
    public void setTidspunkt(LocalDateTime tidspunkt) {this.tidspunkt = tidspunkt;}

    public String getBildeURL() {return bildeURL;}
    public void setBildeURL(String bildeURL) {this.bildeURL = bildeURL;}
}
