package sample.model;

import sample.data.DataHandlerID_Counter;

import java.time.LocalDateTime;

public class Bruker {
    int id;
    String fornavn, etternavn;
    LocalDateTime brukerOpprettet, sisteInnlogging;

    public Bruker(String fornavn, String etternavn) {

        DataHandlerID_Counter.oekBrukerID();

        this.id = DataHandlerID_Counter.hentIDer().getBruker();
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.brukerOpprettet = LocalDateTime.now();
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getFornavn() {return fornavn;}
    public void setFornavn(String fornavn) {this.fornavn = fornavn;}

    public String getEtternavn() {return etternavn;}
    public void setEtternavn(String etternavn) {this.etternavn = etternavn;}

    public LocalDateTime getBrukerOpprettet() {return brukerOpprettet;}
    public void setBrukerOpprettet(LocalDateTime brukerOpprettet) {this.brukerOpprettet = brukerOpprettet;}

    public LocalDateTime getSisteInnlogging() {return sisteInnlogging;}
    public void setSisteInnlogging(LocalDateTime sisteInnlogging) {this.sisteInnlogging = sisteInnlogging;}
}
