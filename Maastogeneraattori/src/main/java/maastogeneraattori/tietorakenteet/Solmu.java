
package maastogeneraattori.tietorakenteet;


/**
 * Solmurakenteeseen talletetaan linkitetyn listan alkiot. Solmulla on avain ja 
 * arvo, ja sillä on viite seuraavaan solmuun.
 * 
 * @author lauimmon
 */
public class Solmu {
    private Object avain;
    private Object arvo;
    private Solmu seuraava;

    /**
     * Luo solmun, jonka avain ja arvo on annettu parametreillä ja viite seuraavaan
     * solmuun on null.
     * 
     * @param avain avain
     * @param arvo arvo
     */
    
    public Solmu(Object avain, Object arvo) {
        this.avain = avain;
        this.arvo = arvo;
        this.seuraava = null;
    }
    
    /**
     * Asettaa solmu viittaamaan seuraavaan solmuun.
     * 
     * @param seuraava seuraava solmu
     */
    
    public void asetaSeuraava(Solmu seuraava) {
        this.seuraava = seuraava;
    }
    
    /**
     * Asettaa solmun arvon.
     * 
     * @param arvo asetettava arvo
     */
    
    public void asetaArvo(Object arvo) {
        this.arvo = arvo;
    }

    public Object getAvain() {
        return avain;
    }

    public Object getArvo() {
        return arvo;
    }

    public Solmu getSeuraava() {
        return seuraava;
    }

    
    
    
}
