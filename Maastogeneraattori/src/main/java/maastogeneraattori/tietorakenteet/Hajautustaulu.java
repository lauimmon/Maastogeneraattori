
package maastogeneraattori.tietorakenteet;

/**
 *
 * @author lauimmon
 */
public class Hajautustaulu {
    private int lkm;
    private String[] avaimet;
    private LinkitettyLista[] taulukko = new LinkitettyLista[10];

    public Hajautustaulu() {
        lkm = 16;
        avaimet = new String[lkm];
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = new LinkitettyLista();
        }
    }
    
    public int hajautusfunktio(Object avain) {
        return 0;
    }
    
    public void lisaa(Object avain, Object arvo) {
        int i = hajautusfunktio(avain);
        taulukko[i].lisaa(avain, arvo);
    }
    
    public Object hae(Object avain) {
        int i = hajautusfunktio(avain);
        return taulukko[i].etsi(avain);
    }
}
