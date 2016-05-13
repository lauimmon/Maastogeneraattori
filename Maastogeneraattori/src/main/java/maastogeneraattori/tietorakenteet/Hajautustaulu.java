
package maastogeneraattori.tietorakenteet;

/**
 *
 * @author lauimmon
 */
public class Hajautustaulu {
    private int koko;
    Object[] lista;

    public Hajautustaulu(int koko) {
        if (koko > 0) {
            this.koko = koko;
        } else {
            this.koko = 16;
        }
        lista = new Object[koko];
    }

    public Hajautustaulu() {
        koko = 16;
        lista = new Object[koko];
    }
    
    
}
