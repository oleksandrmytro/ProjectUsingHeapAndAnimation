package abstrTable;

import list.AbstrDoubleList;
import list.IAbstrDoubleList;
import list.ListException;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class AbstrFIFO<T> {

    private IAbstrDoubleList<T> list = new AbstrDoubleList<>();

    public void zrus() {
        list.zrus();
    }

    public boolean jePrazdny() {
        return list.jePrazdny();
    }

    public void vloz(T data) {
         list.vlozPosledni(data);
    }

    public T odeber() throws ListException {
        if (list.jePrazdny()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.odeberPrvni();
    }

    public Iterator<T> vytvorIterator() {
        return list.iterator();
    }

}
