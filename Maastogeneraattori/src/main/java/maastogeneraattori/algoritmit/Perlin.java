/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.algoritmit;

import java.util.Random;
import maastogeneraattori.maailma.Maailma;
import maastogeneraattori.grafiikka.RGB;
import maastogeneraattori.laskenta.Vektori;

/**
 * Luokka Perlin-kohinan luontiin.
 * 
 * Tätä ei ole vielä käytetty maaston luontiin.
 * Tätä varten kehitetään algoritmi myöhemmin.
 *
 * @author lauimmon
 */
public class Perlin {
    
    private Vektori[][] satunnaisvektorit;
    private Random rand = new Random();

    /**
     * Tehdään perusta vektoriruudukolle. Jokainen ruudukon piste saa arvokseen satunnaisvektorin, jonka pituus 1.
     * 
     * @param n ruudukon koko
     * @param vaihtelu nurkka-arvojen vaihteluväli
     */
    
    public Perlin(int n) {
        satunnaisvektorit = new Vektori[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                satunnaisvektorit[i][j] = luoRandomYksikkovektori();
            }
        }
        
//        double x = rand.nextDouble() * 2 * vaihtelu - vaihtelu;
//        double y = rand.nextDouble() * 2 * vaihtelu - vaihtelu;
//        
//        satunnaisvektorit[0][0] = new Vektori(x,y);
//        
//        x = rand.nextDouble() * 2 * vaihtelu - vaihtelu;
//        y = rand.nextDouble() * 2 * vaihtelu - vaihtelu;
//        
//        satunnaisvektorit[0][n - 1] = new Vektori(x,y);
//        
//        x = rand.nextDouble() * 2 * vaihtelu - vaihtelu;
//        y = rand.nextDouble() * 2 * vaihtelu - vaihtelu;
//        
//        satunnaisvektorit[n - 1][0] = new Vektori(x,y);
//        
//        x = rand.nextDouble() * 2 * vaihtelu - vaihtelu;
//        y = rand.nextDouble() * 2 * vaihtelu - vaihtelu;
//        
//        satunnaisvektorit[n - 1][n - 1] = new Vektori(x,y);
//        
//        jaaKartta(n - 1);
    }
    
    private void jaaKartta(int koko) {
        int puolet = koko / 2;
        if (puolet < 1) {
            return;
        }
        
        for (int y = puolet; y < satunnaisvektorit.length - 1; y += koko) {
            for (int x = puolet; x < satunnaisvektorit.length - 1; x += koko) {
                nelioaskel(x, y, puolet);
            }
        }
        
        for (int y = 0; y < satunnaisvektorit.length; y += puolet) {
            for (int x = (y + puolet) % koko; x < satunnaisvektorit.length; x += koko) {
                timanttiaskel(x, y, puolet);
            }
        }
        
        jaaKartta(puolet);
    }
    
    private void nelioaskel(int x, int y, int koko) {
        Vektori yht = satunnaisvektorit[x + koko][y + koko];
        yht = yht.lisaa(satunnaisvektorit[x + koko][y - koko]);
        yht = yht.lisaa(satunnaisvektorit[x - koko][y + koko]);
        yht = yht.lisaa(satunnaisvektorit[x - koko][y - koko]);
        yht = yht.skaalaa(0.25);
        satunnaisvektorit[x][y] = yht.lisaa(luoRandomYksikkovektori());
    }
    
    private void timanttiaskel(int x, int y, int koko) {
        Vektori yht = new Vektori(0, 0);
        int i = 0;
        if (y - koko >= 0) {
            yht = yht.lisaa(satunnaisvektorit[x][y - koko]);
            i++;
        }
        if (x + koko < satunnaisvektorit.length) {
            yht = yht.lisaa(satunnaisvektorit[x + koko][y]);
            i++;
        }
        if (y + koko < satunnaisvektorit.length) {
            yht = yht.lisaa(satunnaisvektorit[x][y + koko]);
            i++;
        }
        if (x - koko >= 0) {
            yht = yht.lisaa(satunnaisvektorit[x - koko][y]);
            i++;
        }
        yht = yht.skaalaa(1.0 / i);
        satunnaisvektorit[x][y] = yht.lisaa(luoRandomYksikkovektori());
    }
    
//    public Perlinkohina(double[][] kartta) {
//        satunnaisvektorit = new Vektori[kartta.length][kartta[0].length];
//        
//        for (int i = 0; i < kartta.length; i++) {
//            for (int j = 0; j < kartta[0].length; j++) {
//                satunnaisvektorit[i][j] = new Vektori();
//            }
//        }
//    }
    
    private Vektori luoRandomYksikkovektori() {
        double kulma = rand.nextDouble() * 2 * Math.PI;
        
        return new Vektori(Math.cos(kulma), Math.sin(kulma));
    }
    
//    public double[][] luoMaasto(int n, int x1, int y1) {
//        double[][] maasto = new double[n][n];
//        
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                maasto[i][j] = maasto[i][j] + perlin2D(((double) x1 + (double) (i * 2 + 1) / (2 * n)), ((double) y1 + (double) (j * 2 + 1) / (2 * n)));
//            }
//        }
//        
//        return maasto;
//    }
    
//    public double perlin2D(double x, double y) {
//        double yht = 0;
//        
//        double p = .345;
//        int n = 10;
//        
//        for (int i = 0; i < n; i++) {
//            double f = Math.pow(2, i);
//            double a = Math.pow(p, i);
//            yht += interpoloituKohina(x * f, y * f) * a;
//        }
//        
//        return yht;
//    }
//    
//    private double interpoloituKohina(double x, double y) {
//        int ix = (int) Math.floor(x);
//        int iy = (int) Math.floor(y);
//        
//        double sx = x - (double) ix;
//        double sy = y - (double) iy;
//        
//        double v1 = pehmeaKohina(ix, iy);
//        double v2 = pehmeaKohina(ix + 1, iy);
//        double v3 = pehmeaKohina(ix, iy + 1);
//        double v4 = pehmeaKohina(ix + 1, iy + 1);
//        
//        double i1 = interpoloi(v1, v2, sx);
//        double i2 = interpoloi(v3, v4, sx);
//        
//        return interpoloi(i1, i2, sy);
//    }
//    
//    private double pehmeaKohina(int x, int y) {
//        double nurkat = (kohina(x - 1, y - 1) + kohina(x + 1, y - 1) + kohina(x - 1, y + 1) + kohina(x + 1, y + 1)) / 16;
//        double sivut = (kohina(x - 1, y) + kohina(x + 1, y) + kohina(x, y - 1) + kohina(x, y + 1)) / 8;
//        double keskus = kohina(x, y) / 4;
//        return nurkat + sivut + keskus;
//    }
//    
//    private double kohina(int x, int y) {
//        int n = x + y * 57;
//        int m = n << 13;
//        int k = (int) Math.pow(m, n);
//        int vali = ((k * (k * k * 15731 + 789221) + 1376312589) & 2147483647);
//        return (1.0 - (double) vali / 1073741824.0);
//    }
//    
//    private double interpoloi(double a, double b, double x) {
//        return a * (1 - x) + b * x;
//    }
    
    /**
     * Luo nxn korkeuskartan. Funktio käyttää tähän satunnaisvektoreita koordinaateista
     * (x1, y1), (x1, y2), (x2, y1), (x2, y2)
     * 
     * @param n kartan koko
     * @param x1 kartan pienin x-koordinaatti
     * @param y1 kartan pienin y-koordinaatti
     * @param x2 kartan suurin x-koordinaatti
     * @param y2 kartan suurin y-koordinaatti
     * @return korkeuskartta
     */
    
    public double[][] luoMaasto(int n, int x1, int y1, int x2, int y2) {
        double[][] maasto = new double[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maasto[i][j] = maasto[i][j] + perlin(((double) x1 + (double) (x2 - x1) * (i * 2 + 1) / (2 * n)), ((double) y1 + (double) (y2 - y1) * (j * 2 + 1) / (2 * n)));
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

    public Vektori[][] getRandomVektorit() {
        return satunnaisvektorit;
    }
    
    
}
