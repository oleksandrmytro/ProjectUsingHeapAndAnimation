package agendaKraj;

import abstrTable.Obec;
import enumsTypy.eProchazeni;

import java.io.IOException;
import java.util.Iterator;

public interface IAgendaKraj {

    void importDat() throws IOException;

    Obec Najdi(String key);

    Obec Odeber(String key);
    Obec OdeberMax();
    Obec ZpristupniMax();
    void  reorganizace();

    void Vloz(Obec obec) throws Exception;

    Iterator<Obec> vytvorIterator(eProchazeni typEProchazeni);

    Obec generuj();
}
