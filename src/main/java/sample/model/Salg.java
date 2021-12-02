package sample.model;

import java.time.LocalDateTime;

public class Salg {
    private String kjoper;
    private String selger;
    private Vare solgtVare;
    private LocalDateTime tidspunkt;

    public Salg(String kjoper, String selger, Vare solgtVare) {
        this.kjoper = kjoper;
        this.selger = selger;
        this.solgtVare = solgtVare;
        this.tidspunkt = LocalDateTime.now();
    }

    public Salg(){

    }

    public String getKjoper() {
        return kjoper;
    }

    public void setKjoper(String kjoper) {
        this.kjoper = kjoper;
    }

    public String getSelger() {
        return selger;
    }

    public void setSelger(String selger) {
        this.selger = selger;
    }

    public LocalDateTime getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
    }

    public Vare getSolgtVare() {
        return solgtVare;
    }

    public void setSolgtVare(Vare solgtVare) {
        this.solgtVare = solgtVare;
    }
}
