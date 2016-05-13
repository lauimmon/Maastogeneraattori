
package maastogeneraattori.tietorakenteet;

/**
 *
 * @author lauimmon
 */
class Solmu {
    private Object avain;
    private Object arvo;
    private Solmu seuraava;

    public Solmu(Object avain, Object arvo) {
        this.avain = avain;
        this.arvo = arvo;
        this.seuraava = null;
    }
    
    public void asetaSeuraava(Solmu seuraava) {
        this.seuraava = seuraava;
    }
    
    
    
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
