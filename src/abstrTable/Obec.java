package abstrTable;

import enumsTypy.ePorovnani;

public class Obec implements Comparable<Obec> {

    private int cisloKraje;

    private int PSC;

    private String obec;

    private int pocetMuzu;

    private int pocetZen;

    private int celkem;

    private static ePorovnani aktualniPorovnani = ePorovnani.POCET_OBYVATELU;

    public Obec(int cisloKraje, int PSC, String obec, int pocetMuzu, int pocetZen, int celkem) {
        this.cisloKraje = cisloKraje;
        this.PSC = PSC;
        this.obec = obec;
        this.pocetMuzu = pocetMuzu;
        this.pocetZen = pocetZen;
        this.celkem = celkem;
    }

    public Obec(String obec) {
        this.obec = obec;
    }

    public static void setAktualniPorovnani(ePorovnani ePorovnani) {
        Obec.aktualniPorovnani = ePorovnani;
    }

    public int getCisloKraje() {
        return cisloKraje;
    }

    public void setCisloKraje(int cisloKraje) {
        this.cisloKraje = cisloKraje;
    }

    public int getPSC() {
        return PSC;
    }

    public void setPSC(int PSC) {
        this.PSC = PSC;
    }

    public String getObec() {
        return obec;
    }

    public void setObec(String obec) {
        this.obec = obec;
    }

    public int getPocetMuzu() {
        return pocetMuzu;
    }

    public void setPocetMuzu(int pocetMuzu) {
        this.pocetMuzu = pocetMuzu;
    }

    public int getPocetZen() {
        return pocetZen;
    }

    public void setPocetZen(int pocetZen) {
        this.pocetZen = pocetZen;
    }

    public int getCelkem() {
        return celkem;
    }

    public void setCelkem(int celkem) {
        this.celkem = celkem;
    }

    @Override
    public String toString() {
        return "cisloKraje=" + cisloKraje +  ", PSC=" + PSC +
                ", obec=" + obec + ", pocetMuzu=" + pocetMuzu +
                ", pocetZen=" + pocetZen +  ", celkem=" + celkem;
    }


    @Override
    public int compareTo(Obec o) {
        if (aktualniPorovnani == ePorovnani.POCET_OBYVATELU) {
            return Integer.compare(celkem, o.celkem);
        } else {
            return this.obec.compareTo(o.obec);
        }
    }
}
