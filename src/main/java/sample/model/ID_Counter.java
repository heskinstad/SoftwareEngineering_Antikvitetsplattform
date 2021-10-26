package sample.model;

public class ID_Counter {
    int bruker, butikk, admin, vare, klage;

    public ID_Counter() {

    }

    /**
     * Konstruktør med all data for ID_Counter
     * @param bruker holder på høyeste ID generert for bruker
     * @param butikk holder på høyeste ID generert for butikk
     * @param admin holder på høyeste ID generert for admin
     * @param vare holder på høyeste ID generert for vare
     * @param klage holder på høyeste ID generert for klage
     */
    public ID_Counter(int bruker, int butikk, int admin, int vare, int klage) {
        this.bruker = bruker;
        this.butikk = butikk;
        this.admin = admin;
        this.vare = vare;
        this.klage = klage;
    }

    //get-set-metoder

    public int getBruker() {return bruker;}
    public void setBruker(int bruker) {this.bruker = bruker;}

    public int getButikk() {return butikk;}
    public void setButikk(int butikk) {this.butikk = butikk;}

    public int getAdmin() {return admin;}
    public void setAdmin(int admin) {this.admin = admin;}

    public int getVare() {return vare;}
    public void setVare(int vare) {this.vare = vare;}

    public int getKlage() {return klage;}
    public void setKlage(int klage) {this.klage = klage;}

    //Øke ID-metoder

    public void oekBruker() {
        bruker++;
    }

    public void oekButikk() {
        butikk++;
    }

    public void oekAdmin() {
        admin++;
    }

    public void oekVare() {
        vare++;
    }

    public void oekKlage() {
        klage++;
    }
}
