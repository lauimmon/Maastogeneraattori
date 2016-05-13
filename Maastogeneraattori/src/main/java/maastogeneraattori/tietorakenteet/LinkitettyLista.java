
package maastogeneraattori.tietorakenteet;

/**
 *
 * @author lauimmon
 */
public class LinkitettyLista {
    private Solmu alku;

    public LinkitettyLista() {
        alku = null;
    }
    
    public Object etsi(Object avain) {
        Solmu solmu = alku;
        while (solmu != null && solmu.getAvain() != avain) {
            solmu = solmu.getSeuraava();
        }
        if (solmu == null) {
            return null;
        }
        return solmu.getArvo();
    }
    
    public void lisaa(Object avain, Object arvo) {
        if (alku == null) {
            alku = new Solmu(avain, arvo);
            return;
        }
        Solmu seuraava = alku;
        alku = new Solmu(avain, arvo);
        alku.asetaSeuraava(seuraava);
    }
    
    public void poista(Object avain) {
        Solmu edellinen = alku;
        if (edellinen == null) {
            return;
        }
        if (edellinen.getAvain() == avain) {
            alku = edellinen.getSeuraava();
            return;
        }
        
        Solmu seuraava = edellinen.getSeuraava();
        while (seuraava != null) {
            if (seuraava.getAvain() == avain) {
                edellinen.asetaSeuraava(seuraava.getSeuraava());
                return;
            }
            edellinen = seuraava;
            seuraava = seuraava.getSeuraava();
        }
    }
    
    public boolean onkoTyhja() {
        return alku == null;
    }
    
}
