package agendaKraj;

import abstrHeap.AbstrHeap;
import abstrTable.AbstrTable;
import abstrTable.Obec;
import enumsTypy.eProchazeni;
import nacteniAulozeni.nactiAuloz;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class AgendaKraj implements IAgendaKraj {

    private AbstrTable<Obec, Obec> abstrTable = new AbstrTable<>();
    private AbstrHeap<Obec> abstrHeap = new AbstrHeap<>(new Comparator<Obec>() {
        @Override
        public int compare(Obec o1, Obec o2) {
            return o1.compareTo(o2);
        }
    });
    private Random random = new Random();

    @Override
    public void importDat() throws IOException {
        nactiAuloz.nacti(this, eProchazeni.SIRKA);
    }

    @Override
    public Obec Najdi(String key) {
        return abstrTable.najdi(new Obec(key));
    }

    @Override
    public Obec Odeber(String key) {
        return abstrTable.odeber(new Obec(key));
    }

    @Override
    public Obec OdeberMax() {
        return abstrHeap.odeberMax();
    }

    @Override
    public Obec ZpristupniMax() {
        return abstrHeap.ZpristipniMax();
    }

    @Override
    public void reorganizace() {
        abstrHeap.reorganizace();
    }


    @Override
    public void Vloz(Obec obec) throws Exception {
        abstrHeap.vloz(obec);
    }

    @Override
    public Iterator<Obec> vytvorIterator(eProchazeni typEProchazeni) {
        return abstrHeap.vypis(typEProchazeni);
    }

    public void zrus() {
        abstrHeap.zrus();
    }

    @Override
    public Obec generuj() {
        int cisloKraje = random.nextInt(1000) + 1;
        int psc = random.nextInt(90000) + 10000;
        String nazev = "Obec" + (random.nextInt(1000) + 1);
        int pocetMuzu = random.nextInt(100001);
        int pocetZen = random.nextInt(100001);
        return new Obec(cisloKraje, psc, nazev, pocetMuzu, pocetZen, pocetZen + pocetMuzu);
    }
}
