/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.algoritmit;

import java.util.Random;
import maastogeneraattori.laskenta.Vektori;

/**
 * Luokka Perlin-kohinan luontiin.
 * 
 * Perlinkohinaa luodaan tekemällä ruudukko, jonka jokaisessa risteyspisteessä on
 * satunnaisesti luotu vektori. Näiden vektorien avulla mille tahansa ruudukon sisäiselle
 * pisteelle voidaan laskea arvo. Arvo on yhdelle pisteelle aina sama.
 *
 * @author lauimmon
 */
public class Perlin {
    
    private Vektori[][] satunnaisvektorit;
    private Random rand = new Random();

    /**
     * Tehdään perusta vektoritaulukolle. Jokainen taulukon piste saa arvokseen satunnaisvektorin, jonka pituus 1.
     * 
     * @param n taulukon koko
     */
    
    public Perlin(int n) {
        satunnaisvektorit = new Vektori[n + 1][n + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                satunnaisvektorit[i][j] = luoRandomYksikkovektori();
            }
        }
    }
    
    private Vektori luoRandomYksikkovektori() {
        double kulma = rand.nextDouble() * 2 * Math.PI;
        
        return new Vektori(Math.cos(kulma), Math.sin(kulma));
    }

    
    /**
     * Luo nxn kokoisen maaston korkeuskartan käyttämällä siihen Perlinkohinataulukon 
     * pisteiden (x1, y1), (x1, y2), (x2, y1) ja (x2, y2) sisältä löytyvien pisteiden
     * vektoreita. Tietyn pisteen p arvo lasketaan ottamalla pistetulot pistettä 
     * ympäröivien vektorien ja niiden sijaintien ja p:n sijainnin välisten vektorien
     * väliltä ja interpoloimalla näiden neljän arvon kesken.
     * 
     * @param n kartan koko
     * @param x1 kartan pienin x-koordinaatti
     * @param y1 kartan pienin y-koordinaatti
     * @param x2 kartan suurin x-koordinaatti
     * @param y2 kartan suurin y-koordinaatti
     * @return korkeuskartta
     */
    
    public double[][] luoMaasto(int n, double x1, double y1, double x2, double y2) {
        double[][] maasto = new double[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maasto[i][j] = maasto[i][j] + perlin((x1 + (x2 - x1) * (i * 2 + 1) / (2 * n)), (y1 + (y2 - y1) * (j * 2 + 1) / (2 * n)));
            }
        }
        
        return maasto;
    }
    
    private double lineaarinenInterpolointi(double a1, double a2, double paino) {
        return (1.0 - paino) * a1 + paino * a2;
    }
    
    private double perlin(double x, double y) {
        int x0 = (int) Math.floor(x);
        int y0 = (int) Math.floor(y);
        
        double sx = x - (double) x0;
        double sy = y - (double) y0;
        
        Vektori v1 = new Vektori(x - (double) x0, y - (double) y0);
        Vektori v2 = new Vektori(x - (double) (x0 + 1), y - (double) y0);
        Vektori v3 = new Vektori(x - (double) x0, y - (double) (y0 + 1));
        Vektori v4 = new Vektori(x - (double) (x0 + 1), y - (double) (y0 + 1));
        
        double n00 = v1.pistetulo(satunnaisvektorit[x0][y0]);
        double n10 = v2.pistetulo(satunnaisvektorit[x0 + 1][y0]);
        double n01 = v3.pistetulo(satunnaisvektorit[x0][y0 + 1]);
        double n11 = v4.pistetulo(satunnaisvektorit[x0 + 1][y0 + 1]);
        
        sx = haivyta(sx);
        sy = haivyta(sy);
        
        double nx0 = lineaarinenInterpolointi(n00, n10, sx);
        double nx1 = lineaarinenInterpolointi(n01, n11, sx);
        
        return lineaarinenInterpolointi(nx0, nx1, sy);
    }

    private double haivyta(double i) {
        return i * i * i * (10 + i * (-15 + i * 6));
    }

    /**
     * 
     * @return taulukko satunnaisVektoreista
     */
    
    public Vektori[][] getRandomVektorit() {
        return satunnaisvektorit;
    }
    
    
}
