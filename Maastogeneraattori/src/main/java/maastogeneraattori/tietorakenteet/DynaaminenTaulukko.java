
package maastogeneraattori.tietorakenteet;

/**
 * Dynaaminen taulukko on taulukko, jolle ei tarvitse määrittää kokoa,
 * ja sen koko muuttuu tarvittaessa.
 * 
 * @author lauimmon
 */
public class DynaaminenTaulukko {
    private Object[] taulukko;
    private int indeksi;

    /**
     * Alustaa taulukon 16 pituiseksi
     */
    
    public DynaaminenTaulukko() {
        taulukko = new Object[16];
        indeksi = 0;
    }
    
    /**
     * Lisää alkion taulukon perälle.
     * 
     * @param alkio 
     */
    
    public void lisaa(Object alkio) {
        taulukko[indeksi] = alkio;
        indeksi++;
        if (indeksi == taulukko.length) {
            kasvata();
        }
    }
    
    /**
     * Lisää alkion haluttuun paikkaan taulukossa.
     * 
     * @param indeksi
     * @param alkio 
     */
    
    public void lisaa(int indeksi, Object alkio) {
        if (indeksi < 0 || indeksi > this.indeksi) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = taulukko.length - 1; i > indeksi; i--) {
            taulukko[i] = taulukko[i - 1];
        }
        taulukko[indeksi] = alkio;
        this.indeksi++;
        if (this.indeksi == taulukko.length) {
            kasvata();
        }
    }
    
    private void kasvata() {
        Object[] uusi = new Object[taulukko.length * 2];
        System.arraycopy(taulukko, 0, uusi, 0, taulukko.length);
        taulukko = uusi;
    }
    
    /**
     * Poistaa halutun alkion taulukosta. Jos alkiota ei esiinny taulukossa,
     * ei tapahdu mitään. Jos taulukossa on useampia saman alkion esiintymiä,
     * poistaa vain näistä ensimmäisen.
     * 
     * @param alkio 
     */
    
    public void poista(Object alkio) {
        for (int i = 0; i < taulukko.length; i++) {
            if (taulukko[i] == null) {
                return;
            }
            if (taulukko[i].equals(alkio)) {
                for (int j = i; j < taulukko.length - 1; j++) {
                    taulukko[j] = taulukko[j + 1];
                }
                indeksi--;
                return;
            }
        }
    }
    
    /**
     * Hakee alkion tietystä paikasta taulukossa.
     * 
     * @param indeksi mistä alkio haetaan
     * @return alkio
     */
    
    public Object hae(int indeksi) {
        if (indeksi < 0 || indeksi > this.indeksi) {
            throw new IndexOutOfBoundsException();
        }
        return taulukko[indeksi];
    }
    
    /**
     * Tarkistaa, että esiintyykö alkio taulukossa.
     * 
     * @param alkio
     * @return true jos alkio löytyy taulukosta, false jos ei
     */
    
    public boolean sisaltaa(Object alkio) {
        for (Object o : taulukko) {
            if (o == null) {
                return false;
            }
            if (o.equals(alkio)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Tarkistaa missä kohdassa taulukkoa alkio on. Jos alkio esiintyy 
     * taulukossa, palauttaa sen ensimmäisen esiintymisen indeksin. 
     * Jos alkio ei esiinny taulukossa, palauttaa -1.
     * 
     * @param alkio
     * @return alkion indeksi
     */
    
    public int indeksi(Object alkio) {
        for (int i = 0; i < taulukko.length; i++) {
            if (taulukko[i] == null) {
                return -1;
            }
            if (taulukko[i].equals(alkio)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Tarkistaa onko taulukko tyhjä.
     * 
     * @return true jos taulukko tyhjä, false jos ei
     */
    
    public boolean onTyhja() {
        for (Object o : taulukko) {
            if (o != null) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Palauttaa taulukon koon.
     * 
     * @return taulukon koko
     */
    
    public int koko() {
        return indeksi;
    }
    
}
