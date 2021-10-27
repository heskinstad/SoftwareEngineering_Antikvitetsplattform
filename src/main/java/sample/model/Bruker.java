package sample.model;

import java.time.LocalDateTime;

public class Bruker {
    String fornavn, etternavn;
    LocalDateTime brukerOpprettet, sisteInnlogging;

    /**
     * Konstruktør for bruker. id er unik for alle brukere, og kombinasjonen navn + etternavn må også være unik (brukes til innlogging)
     * @param fornavn fornavn på bruker
     * @param etternavn etternavn på bruker
     */
    public Bruker(String fornavn, String etternavn) {

        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.brukerOpprettet = LocalDateTime.now();
        this.sisteInnlogging = LocalDateTime.now();
    }

    //get-set-metoder

    public String getFornavn() {return fornavn;}
    public void setFornavn(String fornavn) {this.fornavn = fornavn;}

    public String getEtternavn() {return etternavn;}
    public void setEtternavn(String etternavn) {this.etternavn = etternavn;}

    public LocalDateTime getBrukerOpprettet() {return brukerOpprettet;}
    public void setBrukerOpprettet(LocalDateTime brukerOpprettet) {this.brukerOpprettet = brukerOpprettet;}

    public LocalDateTime getSisteInnlogging() {return sisteInnlogging;}
    public void setSisteInnlogging(LocalDateTime sisteInnlogging) {this.sisteInnlogging = sisteInnlogging;}
}
