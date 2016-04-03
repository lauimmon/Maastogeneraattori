/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.algoritmit;

import java.util.Random;

/**
 *
 * @author lauimmon
 */
public class Perlinkohina {
    
    private double[][][] randomVektorit;

    /**
     * Tehdään perusta vektoriruudukolle. Jokainen ruudukon piste saa arvokseen vektorin, jonka pituus 1.
     * 
     * @param n ruudukon koko
     */
    
    public Perlinkohina(int n) {
        randomVektorit = new double[n][n][2];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                randomVektorit[i][j] = luoRandomYksikkoVektori();
            }
        }
    }
    
    /**
     * Luo nxn korkeuskartan. Funktio käyttää tähän randomVektoreita koordinaateista
     * (x1, y1), (x1 + 1, y1), (x1, y1 + 1), (x1 + 1, y1 + 1)
     * 
     * @param n kartan koko
     * @param x1 alavasemman x-koordinaatti
     * @param y1 alavasemman y-koordinaatti
     * @return korkeuskartta
     */
    
    public double[][] luoMaasto(int n, int x1, int y1) {
        double[][] maasto = new double[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maasto[i][j] = perlin((double) x1 + (double) (i * 2 + 1) / (2 * n), (double) y1 + (double) (j * 2 + 1) / (2 * n));
            }
        }
        
        return maasto;
    }

    private double[] luoRandomYksikkoVektori() {
        Random rand = new Random();
        
        double kulma = rand.nextDouble() * 2 * Math.PI;
        
        return new double[] {Math.cos(kulma), Math.sin(kulma)};
    }
    
    private double lineaarinenInterpolointi(double a1, double a2, double paino) {
        return (1.0 - paino) * a1 + paino * a2;
    }
    
    private double pistetulo(int ix, int iy, double x, double y) {
        double dx = x - (double) ix;
        double dy = y - (double) iy;
        
        return (dx * randomVektorit[iy][ix][0] + dy * randomVektorit[iy][ix][1]);
    }
    
    private double perlin(double x, double y) {
        int x0 = (int) Math.floor(x);
        int y0 = (int) Math.floor(y);
        
        int x1 = x0 + 1;
        int y1 = y0 + 1;
        
        double sx = x - (double) x0;
        double sy = y - (double) y0;
        
        double n00 = pistetulo(x0, y0, x, y);
        double n10 = pistetulo(x1, y0, x, y);
        double n01 = pistetulo(x0, y1, x, y);
        double n11 = pistetulo(x1, y1, x, y);
        
        //sx = haivyta(sx);
        //sy = haivyta(sy);
        
        double nx0 = lineaarinenInterpolointi(n00, n10, sx);
        double nx1 = lineaarinenInterpolointi(n01, n11, sx);
        
        return lineaarinenInterpolointi(nx0, nx1, sy);
    }

    private double haivyta(double i) {
        return i * i * i * (10 + i * (-15 + i * 6));
    }

    public double[][][] getRandomVektorit() {
        return randomVektorit;
    }
    
}
