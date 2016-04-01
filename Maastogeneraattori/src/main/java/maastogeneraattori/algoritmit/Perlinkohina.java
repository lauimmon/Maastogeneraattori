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

    private double[] luoRandomYksikkoVektori() {
        Random rand = new Random();
        
        double kulma = rand.nextDouble() * 2 * Math.PI;
        
        return new double[] {Math.cos(kulma), Math.sin(kulma)};
    }
    
    private double interpoloi(double a1, double a2, double paino) {
        return (1.0 - paino) * a1 + paino * a2;
    }
    
    private double pistetulo(int ix, int iy, double x, double y) {
        double dx = x - (double) ix;
        double dy = y - (double) iy;
        
        return (dx * randomVektorit[iy][ix][0] + dy * randomVektorit[iy][ix][1]);
    }
    
    private double perlin(double x, double y) {
        int x0 = (int) x;
        int y0 = (int) y;
        
        if (x <= 0) {
            x0--;
        }
        int x1 = x0 + 1;
        
        if (y <= 0) {
            y0--;
        }
        int y1 = y0 + 1;
        
        double sx = x - (double) x0;
        double sy = y - (double) y0;
        
        double n0 = pistetulo(x0, y0, x, y);
        double n1 = pistetulo(x1, y0, x, y);
        double ix0 = interpoloi(n0, n1, sx);
        n0 = pistetulo(x0, y1, x, y);
        n1 = pistetulo(x1, y1, x, y);
        double ix1 = interpoloi(n0, n1, sx);
        return interpoloi(ix0, ix1, sy);
    }

    public double[][][] getRandomVektorit() {
        return randomVektorit;
    }
    
}
