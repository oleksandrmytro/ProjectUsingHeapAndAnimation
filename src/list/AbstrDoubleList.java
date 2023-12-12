package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbstrDoubleList<T> implements IAbstrDoubleList<T>{
    private Node<T> first;
    private Node<T> last;
    private Node<T> current;


    private int size;
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node temp = first;
            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = (T) temp.data;
                    temp = temp.next;
                    return data;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }



    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data) {
            this.data = data;
        }
    }
    @Override
    public void vlozPrvni(T data) {
        Node<T> newNode = new Node<>(data);
        if (jePrazdny()) {
            first = newNode;
            last = newNode;
            current = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    @Override
    public void vlozPosledni(T data) {
        Node<T> newNode = new Node<>(data);
        if (jePrazdny()) {
            first = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
        }
        last = newNode;
        size++;
    }

    @Override
    public void vlozNaslednika(T data) throws ListException {
        if (data == null) {
            throw new IllegalArgumentException("Nelze vložit prvek s hodnotou null.");
        }

        if (jePrazdny()) {
            throw new IllegalStateException("Aktuální prvek není nastaven.");
        }

        Node<T> newNode = new Node<>(data);
        newNode.prev = current;
        newNode.next = current.next;

        if (current.next != null) {
            current.next.prev = newNode;
        } else {
            last = newNode;
        }

        current.next = newNode;
        size++;
    }

    @Override
    public void vlozPredchudce(T data) throws ListException {
        if (data == null) {
            throw new IllegalArgumentException("Nelze vložit prvek s hodnotou null.");
        }

        if (jePrazdny()) {
            throw new IllegalStateException("Aktuální prvek není nastaven.");
        }

        Node<T> newNode = new Node<>(data);
        newNode.next = current;
        newNode.prev = current.prev;

        if (current.prev != null) {
            current.prev.next = newNode;
        } else {
            first = newNode;
        }

        current.prev = newNode;
        size++;
    }

    @Override
    public T zpristupniAktualni() throws ListException {
        if (jePrazdny() || current == null) {
            throw new NoSuchElementException();
        }
        return current.data;
    }

    @Override
    public T zpristupniPrvni() throws ListException {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        current = first;
        return current.data;
    }

    @Override
    public T zpristupniPosledni() throws ListException {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
        current = last;
        return current.data;
    }

    @Override
    public T zpristupniNaslednika() throws ListException {
        if (jePrazdny() || current == null || current.next == null) {
            throw new NoSuchElementException("Následník aktuálního prvku není k dispozici.");
        }

        current = current.next;
        return current.data;
    }

    @Override
    public T zpristupniPredchudce() throws ListException {
        if (jePrazdny() || current == null || current.prev == null) {
            throw new NoSuchElementException("Předchůdce aktuálního prvku není k dispozici.");
        }

        current = current.prev;
        return current.data;
    }

    @Override
    public T odeberAktualni() throws ListException {
        if (jePrazdny() || current == null) {
            throw new NoSuchElementException("Nelze odebrat aktuální prvek, protože seznam je prázdný nebo aktuální prvek není nastaven.");
        }

        T data = current.data;
        Node<T> prev = current.prev;
        Node<T> next = current.next;

        if (prev != null) {
            prev.next = next;
        } else {
            first = next;
        }

        if (next != null) {
            next.prev = prev;
        } else {
            last = prev;
        }

        current = first;
        size--;

        return data;
    }

    @Override
    public T odeberPrvni() {
        if (jePrazdny()) {
            throw new NoSuchElementException("Nelze odebrat první prvek, protože seznam je prázdný.");
        }

        T data = first.data;
        Node<T> next = first.next;

        if (next != null) {
            next.prev = null;
        }

        first = next;
        if (first == null) {
            last = null;
        }

        current = first;
        size--;

        return data;
    }

    @Override
    public T odeberPosledni() {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }

        T data = last.data;
        Node<T> prev = last.prev;

        if (prev != null) {
            prev.next = null;
        }

        last = prev;
        if (last == null) {
            first = null;
        }

        current = first;
        size--;

        return data;
    }

    @Override
    public T odeberNaslednika() {
        if (jePrazdny() || current == null || current.next == null) {
            throw new NoSuchElementException("Nelze odebrat následníka aktuálního prvku, protože seznam е prázdný nebo následník aktuálního prvku není k dispozici.");
        } else {
            Node<T> nodeToRemove = current.next;
            T data = nodeToRemove.data;

            current.next = nodeToRemove.next;

            if (nodeToRemove.next != null) {
                nodeToRemove.next.prev = current;
            }

            size--;

            return data;
        }
    }

    @Override
    public T odeberPredchudce() {
        if (jePrazdny() || current == null || current.prev == null) {
            throw new NoSuchElementException("Nelze odebrat předchůdce aktuálního prvku, protože seznam je prázdný nebo předchůdce aktuálního prvku není k dispozici.");
        } else {

            Node<T> nodeToRemove = current.prev;
            T data = nodeToRemove.data;

            current.prev = nodeToRemove.prev;

            if (nodeToRemove.prev != null) {
                nodeToRemove.prev.next = current;
            }

            size--;

            return data;
        }
    }
    @Override
    public void zrus() {
        first = null;
        last = null;
        current = null;
        size = 0;
    }

    @Override
    public boolean jePrazdny() {
        return size == 0;
    }

    public static <T> void DataException(T data) {
        if(data == null) {
            throw new NullPointerException("Vlozeni prvek je null");
        }
    }

    private void PrazdnyException() throws ListException {
        if(jePrazdny()) {
            throw new ListException("Seznam is empty");
        }
    }
}

