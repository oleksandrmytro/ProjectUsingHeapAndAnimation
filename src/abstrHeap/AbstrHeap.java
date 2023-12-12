package abstrHeap;

import abstrTable.AbstrLIFO;
import abstrTable.Obec;
import enumsTypy.eProchazeni;
import list.ListException;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class AbstrHeap<T> implements IAbstrHeap<T> {

    private T [] heapArray;

    private int maxSize;

    private int currentSize;

    private Comparator<T> cmp;

    public AbstrHeap(Comparator<T> cmp) {
        this.maxSize = 10;
        this.currentSize = 0;
        this.cmp = cmp;
        this.heapArray = (T[]) new Object[maxSize];
    }

    @Override
    public void vybuduj(T[] array) {
        if (array.length > maxSize) {
            throw new IllegalArgumentException("Array is too large");
        }
         currentSize = array.length;
        System.arraycopy(array, 0, heapArray, 0, array.length);
        for (int i = (currentSize / 2) - 1; i >= 0 ; i--) {
            siftDown(i);
        }
    }


    @Override
    public void reorganizace() {
        for (int i = (currentSize / 2) - 1; i >= 0 ; i--) {
            siftDown(i);
        }
    }

    @Override
    public void zrus() {
        this.currentSize = 0;
    }

    @Override
    public boolean jePrazdny() {
        return heapArray == null;
    }

    @Override
    public void vloz(T element) {
        if (currentSize == maxSize) {
            throw new IllegalStateException("Heap is full");
        }
        heapArray[currentSize] = element;
        siftUp(currentSize);
        currentSize++;
    }

    @Override
    public T odeberMax() {
        if(jePrazdny()) {
            throw new NoSuchElementException("Heap je prazdny");
        }
        T maxElement = heapArray[0];
        heapArray[0] = heapArray[currentSize - 1];
        currentSize--;
        siftDown(0);
        return maxElement;
    }

    @Override
    public T ZpristipniMax() {
        if(jePrazdny()) {
            throw new NoSuchElementException("Heap is clear");
        }
        return heapArray[0];
    }

    private void siftDown(int index) {
        T element = heapArray[index];
        int half = currentSize / 2;
        while (index < half) {
            int left = leftChild(index);
            int right = rightChild(index);
            int selectedChild = left;
            if(right < currentSize && cmp.compare(heapArray[left], heapArray[right]) < 0 ) {
                selectedChild = right;
            }
            if(cmp.compare(element, heapArray[selectedChild]) >= 0) {
                break;
            }
            heapArray[index] = heapArray[selectedChild];
            index = selectedChild;
        }
        heapArray[index] = element;
    }

    private void siftUp(int index) {
        T element = heapArray[index];
        while (index > 0) {
            int parentIndex = parent(index);
            T parent = heapArray[parentIndex];
            if(cmp.compare(element, parent) > 0) {
                heapArray[index] = parent;
                index = parentIndex;
            } else {
                break;
            }
        }
        heapArray[index] = element;
    }

    @Override
    public Iterator<T> vypis(eProchazeni typ) {
        switch (typ) {
            case SIRKA -> {
                return new Iterator<>() {
                    int i = 0;

                    @Override
                    public boolean hasNext() {
                        return i < currentSize;
                    }

                    @Override
                    public T next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        return heapArray[i++];
                    }
                };
            }

            case HLOUBKA -> {
                return new Iterator<>() {
                    final AbstrLIFO<Integer> lifo = new AbstrLIFO<>();
                    boolean initialized = false;

                    @Override
                    public boolean hasNext() {
                        if (!initialized) {
                            initialize();
                            initialized = true;
                        }
                        return !lifo.jePrazdny();
                    }

                    private void initialize() {
                        int index = 0;
                        while (index < currentSize) {
                            lifo.vloz(index);
                            index = leftChild(index);
                        }
                    }

                    @Override
                    public T next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        int index = 0;
                        try {
                            index = lifo.odeber();
                        } catch (ListException e) {
                            throw new RuntimeException(e);
                        }
                        T result = heapArray[index];

                        int rightIndex = rightChild(index);
                        if (rightIndex < currentSize) {
                            int nextIndex = rightIndex;
                            while (nextIndex < currentSize) {
                                lifo.vloz(nextIndex);
                                nextIndex = leftChild(nextIndex);
                            }
                        }

                        return result;
                    }
                };
            }
            default -> throw new IllegalArgumentException("Neplatny typ prochazeni");
        }

    }

    private int leftChild(int i) { return 2 * i + 1; }

    private int rightChild(int i) { return 2 * i + 2; }

    private int parent(int i) { return (i - 1) / 2; }

    private int firstLeafIndex(int n) { return n / 2; }

}
