package list;

import java.util.Iterator;

public interface IAbstrDoubleList<T> {

    /**
     * zrušení celého seznamu
     */
    void zrus();

    /**
     * test naplněnosti seznamu
     *
     */
    boolean jePrazdny();

    /**
     * vložení prvku do seznamu na první místo
     *
     */
    void vlozPrvni(T data);

    /**
     * vložení prvku do seznamu na poslední místo
     *
     */
    void vlozPosledni(T data);

    /**
     * vložení prvku do seznamu jakožto následníka aktuálního prvku
     *
     */
    void vlozNaslednika(T data) throws ListException;

    /**
     * vložení prvku do seznamu jakožto předchůdce aktuálního prvku
     *
     */
    void vlozPredchudce(T data) throws ListException;

    /**
     * zpřístupnění aktuálního prvku seznamu
     *
     */
    T zpristupniAktualni() throws ListException;

    /**
     * zpřístupnění prvního prvku seznamu
     *
     */
    T zpristupniPrvni() throws ListException;

    /**
     * zpřístupnění posledního prvku seznamu
     *
     */
    T zpristupniPosledni() throws ListException;

    /**
     * zpřístupnění následníka aktuálního prvku
     *
     */
    T zpristupniNaslednika() throws ListException;

    /**
     * zpřístupnění předchůdce aktuálního prvku
     *
     */
    T zpristupniPredchudce() throws ListException;

    /**
     * odebrání (vyjmutí) aktuálního prvku ze seznamu poté je aktuální prvek nastaven na první prvek
     *
     */
    T odeberAktualni() throws ListException;

    /**
     * odebrání prvního prvku ze seznamu
     *
     */
    T odeberPrvni() throws ListException;

    /**
     * odebrání posledního prvku ze seznamu
     *
     */
    T odeberPosledni() throws ListException;

    /**
     * odebrání následníka aktuálního prvku ze seznamu
     *
     */
    T odeberNaslednika() throws ListException;

    /**
     * -odebrání předchůdce aktuálního prvku ze seznamu
     *
     */
    T odeberPredchudce() throws ListException;

    /**
     * vytvoří iterátor (dle rozhraní Iterable)
     *
     */
    Iterator<T> iterator();
}
