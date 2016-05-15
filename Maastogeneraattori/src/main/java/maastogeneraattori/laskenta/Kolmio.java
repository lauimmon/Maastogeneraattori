
package maastogeneraattori.laskenta;

import java.awt.Color;
import maastogeneraattori.grafiikka.RGB;

/**
 * Luokkaa käytetään kolmioidun pinnan kolmioiden pisteiden tallennukseen
 * 
 * @author lauimmon
 */
public class Kolmio {
    private int[] x = new int[3];
    private int[] y = new int[3];
    private RGB[] varit = new RGB[3];
    private Vektori normaali = new Vektori(0.0, 0.0, 0.0);
    private Color vari;

    /**
     * Luo kolmion, jonka kulmien koordinaatit ovat (x1, y1), (x2, y2) ja (x3, y3)
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3 
     */
    
    public Kolmio(int x1, int y1, int x2, int y2,
            int x3, int y3) {
        x[0] = x1;
        x[1] = x2;
        x[2] = x3;
        y[0] = y1;
        y[1] = y2;
        y[2] = y3;
    }
    
    /**
     * Asettaa kolmiolle normaalivektorin, eli kolmion pinnan vastaisen vektorin.
     * 
     * @param v asetettava vektori
     */

    public void setNormaali(Vektori v) {
        normaali = v;
    }
    
    /**
     * Asetettaa kolmion nurkalle värin.
     * 
     * @param i kulman numero
     * @param vari asetettava väri
     */
    
    public void setVari(int i, RGB vari) {
        varit[i] = vari;
    }
    
    /**
     * Asettaa kolmiolle värin
     * 
     * @param vari asetettava väri
     */
    
    public void setVari(Color vari) {
        this.vari = vari;
    }
    
    /**
     * Hakee kolmion nurkan x-koordinaatin.
     * 
     * @param i nurkan numero
     * @return x-koordinaatti
     */
    
    public int getXKoordinaatti(int i) {
        return x[i];
    }
    
    /**
     * Hakee kolmion nurkan y-koordinaatin.
     * 
     * @param i nurkan numero
     * @return y-koordinaatti
     */
    
    public int getYKoordinaatti(int i) {
        return y[i];
    }

    /**
     * @return kolmion normaalivektori
     */
    
    public Vektori getNormaali() {
        return normaali;
    }
    
    /**
     * @return kolmion vari
     */

    public Color getVari() {
        return vari;
    }
    
    
    
}
