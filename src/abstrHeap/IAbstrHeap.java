package abstrHeap;

import enumsTypy.eProchazeni;

import java.util.Iterator;

public interface IAbstrHeap<T> {

    void vybuduj(T[] array);

    void reorganizace();

    void zrus();

    boolean jePrazdny();

    void vloz(T element);

    T odeberMax();

    T ZpristipniMax();

    Iterator vypis(eProchazeni typ);
}

