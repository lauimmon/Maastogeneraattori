/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * Luo Kolmion, jonka kulmien koordinaatit ovat (x1, y1), (x2, y2) ja (x3, y3)
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

    public void setNormaali(Vektori v) {
        normaali = v;
    }
    
    public void setVari(int i, RGB vari) {
        varit[i] = vari;
    }
    
    public void setVari(Color vari) {
        this.vari = vari;
    }
    
    public int getXKoordinaatti(int i) {
        return x[i];
    }
    
    public int getYKoordinaatti(int i) {
        return y[i];
    }

    public Vektori getNormaali() {
        return normaali;
    }

    public Color getVari() {
        return vari;
    }
    
    
    
}
