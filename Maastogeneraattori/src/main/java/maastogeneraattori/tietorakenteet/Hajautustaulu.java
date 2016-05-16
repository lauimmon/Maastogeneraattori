
package maastogeneraattori.tietorakenteet;

/**
 * Hajautustaulu, jossa ylivuotoketjut tallennetaan likitettyihin listoihin. 
 * Täyttösuhde, eli avainten määrä suhteessa taulukon pituuteen pyritään 
 * pitämään pienenä, alle 0,75. Kun täyttösuhde ylittää tämän arvon, hajautustaulun
 * kokoa kasvatetaan. Hajautuksessa käytetään hajautusfunktiota, jonka ansiosta
 * haku, lisäys ja poisto ovat keskimäärin vakioaikaisia, eli O(1).
 * 
 * @author lauimmon
 */
public class Hajautustaulu {
    private DynaaminenTaulukko avaimet;
    private LinkitettyLista[] taulukko = new LinkitettyLista[11];

    /**
     * Alustaa hajaututustaulun jokaiseen arvoon tyhjän linkitetyn listan.
     * Alustaa avaintaulukon 16 kokoiseksi. Kun se tulee täyteen, sen kokoa
     * kasvatetaan.
     */
    
    public Hajautustaulu() {
        avaimet = new DynaaminenTaulukko();
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = new LinkitettyLista();
        }
    }
    
    /**
     * Lisäää hajautustauluun avain-arvo-parin. Jos avain löytyy jo hajautustaulusta
     * ei lisää avainta uudestaan, vaan muuttaa sen arvoa. Jos avainlista täyttyy, 
     * sen kokoa kasvatetaan. Jos täyttösuhde ylittää 0,75, kasvatetaan hajautustaulun kokoa.
     * 
     * @param avain lisättävä avain
     * @param arvo lisättävä arvo
     */
    
    public void lisaa(Object avain, Object arvo) {
        if (avain == null) {
            return;
        }
        int i = hajautusfunktio(avain);
        if (taulukko[i].etsi(avain) == null) {
            taulukko[i].lisaa(avain, arvo);
            if (!avaimet.sisaltaa(avain)) {
                avaimet.lisaa(avain);
            }
            if ((double) avaimet.koko() / taulukko.length > 0.75) {
                kasvataTaulukkoa();
            }
        } else {
            Solmu s = taulukko[i].getAlku();
            while (!s.getAvain().equals(arvo)) {
                s = s.getSeuraava();
            }
            s.asetaArvo(arvo);
        }
        
    }
    
    /**
     * Poistaa avain-arvo-parin hajautuslistasta, jos avain löytyy sieltä.
     * 
     * @param avain joka halutaan poistaa
     */
    
    public void poista(Object avain) {
        if (avain == null) {
            return;
        }
        int i = hajautusfunktio(avain);
        if (taulukko[i].etsi(avain) != null) {
            taulukko[i].poista(avain);
        }
    }
    
    /**
     * Hakee avainta vastaavan arvon hajautustaulusta. Palauttaa null jos
     * avainta ei löydy hajautuslistasta.
     * 
     * @param avain millä arvoa haetaan
     * @return avaimen arvopari
     */
    
    public Object hae(Object avain) {
        if (avain == null) {
            return null;
        }
        int i = hajautusfunktio(avain);
        return taulukko[i].etsi(avain);
    }
    
    /**
     * Tarkistaa löytyykö hajautustaulusta parametrina annettua avainta.
     * 
     * @param avain etsittävä avain
     * @return true jos avain löytyy taulusta, false jos ei
     */
    
    public boolean sisaltaaAvaimen(Object avain) {
        if (avain == null) {
            return false;
        }
        return avaimet.sisaltaa(avain);
    }
    
    /**
     * Tarkistaa löytyykö hajautustaulusta alkio, jonka arvo on parametrina
     * annettu.
     * 
     * @param arvo etsittävä arvo
     * @return true jos arvo löytyy taulusta, false jos ei
     */
    
    public boolean sisaltaaArvon(Object arvo) {
        for (int i = 0; i < avaimet.koko(); i++) {
            if (hae(avaimet.hae(i)).equals(arvo)) {
                return true;
            }
        }
        return false;
    }
    
    private int hajautusfunktio(Object avain) {
        int n = avain.hashCode();
        return n % taulukko.length;
    }
    
    private void kasvataTaulukkoa() {
        Object[] arvot = new Object[avaimet.koko()];
        for (int i = 0; i < avaimet.koko(); i++) {
            arvot[i] = hae(avaimet.hae(i));
        }
        
        taulukko = new LinkitettyLista[taulukko.length * 2];
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = new LinkitettyLista();
        }
        for (int i = 0; i < avaimet.koko(); i++) {
            lisaa(avaimet.hae(i), arvot[i]);
        }
    }
}
