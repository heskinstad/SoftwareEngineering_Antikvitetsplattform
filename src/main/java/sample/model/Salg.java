package sample.model;

import java.time.LocalDateTime;

public class Salg {
    private Bruker kjoper;
    private Butikk selger;
    private Vare solgtVare;
    private LocalDateTime tidspunkt;

    public Salg(Bruker kjoper, Butikk selger, Vare solgtVare) {
        this.kjoper = kjoper;
        this.selger = selger;
        this.solgtVare = solgtVare;
        this.tidspunkt = LocalDateTime.now();
    }

    public Salg(){

    }

    public Bruker getKjoper() {
        return kjoper;
    }

    public void setKjoper(Bruker kjoper) {
        this.kjoper = kjoper;
    }

    public Butikk getSelger() {
        return selger;
    }

    public void setSelger(Butikk selger) {
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
