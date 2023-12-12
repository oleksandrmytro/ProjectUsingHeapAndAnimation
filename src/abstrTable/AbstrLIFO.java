package abstrTable;

import list.AbstrDoubleList;
import list.IAbstrDoubleList;
import list.ListException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbstrLIFO<T> {
    private IAbstrDoubleList<T> list = new AbstrDoubleList<>();

    public void zrus() {
        list.zrus();
    }

    public boolean jePrazdny() {
        return list.jePrazdny();
    }

    public void vloz (T data) {
        list.vlozPosledni(data);
    }

    public T odeber() throws ListException {
        if (list.jePrazdny()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T data = list.zpristupniPosledni();
        list.odeberPosledni();
        return data;
    }

    public Iterator<T> vytvorIterator() {
        return list.iterator();
    }

}
