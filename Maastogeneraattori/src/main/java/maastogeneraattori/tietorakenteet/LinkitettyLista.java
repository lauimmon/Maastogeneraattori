
package maastogeneraattori.tietorakenteet;

/**
 * Tietorakenne linkitetyn listan esittämiseen. Listan alkiot ovat solmuja,
 * joilla on avain ja arvo. Listaan on tallennettu ensimmäinen solmu, jos lista ei ole
 * tyhjä, ja solmuun on talletettu sitä seuraava solmu.
 * 
 * @author lauimmon
 */
public class LinkitettyLista {
    private Solmu alku;

    /**
     * Luo tyhjän linkitetyn listan.
     */
    
    public LinkitettyLista() {
        alku = null;
    }
    
    /**
     * Etsii linkitetystä listasta solmun, jonka avain on parametrina annettu
     * arvo. Palauttaa solmun arvon. Jos avainta ei ole listassa, palauttaa null.
     * 
     * @param avain solmun avain
     * @return solmun arvo
     */
    
    public Object etsi(Object avain) {
        Solmu solmu = alku;
        while (solmu != null && !solmu.getAvain().equals(avain)) {
            solmu = solmu.getSeuraava();
        }
        if (solmu == null) {
            return null;
        }
        return solmu.getArvo();
    }
    
    /**
     * Lisää solmun linkitetyn listan alkuun.
     * 
     * @param avain solmun avain
     * @param arvo solmun arvo
     */
    
    public void lisaa(Object avain, Object arvo) {
        if (alku == null) {
            alku = new Solmu(avain, arvo);
            return;
        }
        Solmu seuraava = alku;
        alku = new Solmu(avain, arvo);
        alku.asetaSeuraava(seuraava);
    }
    
    /**
     * Poistaa listasta solmun, jonka avain on annettu parametrina. Jos solmua
     * ei ole listassa, ei poista mitään.
     * 
     * @param avain solmun avain
     */
    
    public void poista(Object avain) {
        Solmu edellinen = alku;
        if (edellinen == null) {
            return;
        }
        if (edellinen.getAvain().equals(avain)) {
            alku = edellinen.getSeuraava();
            return;
        }
        
        Solmu seuraava = edellinen.getSeuraava();
        while (seuraava != null) {
            if (seuraava.getAvain().equals(avain)) {
                edellinen.asetaSeuraava(seuraava.getSeuraava());
                return;
            }
            edellinen = seuraava;
            seuraava = seuraava.getSeuraava();
        }
    }
    
    /**
     * Tarkistaa onko linkitetty lista tyhjä.
     * 
     * @return true jos tyhjä, false jos ei tyhjä
     */
    
    public boolean onkoTyhja() {
        return alku == null;
    }

    public Solmu getAlku() {
        return alku;
    }
    
    
    
}
