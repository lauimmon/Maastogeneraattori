/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.algoritmit;

import java.util.logging.Logger;

/**
 * Tämä generaattori luo maaston yhdistämällä useita Perlinkohinoita päällekkäin.
 * 
 * @author lauimmon
 */
public class OmaGeneraattori {
    
    private Perlin p1;
    private Perlin p2;
    private Perlin p3;
    private Perlin p4;
    private Perlin p5;
    private Perlin p6;
    private double[][] maasto;
    private double x;
    private double y;
    private int resoluutio;
    private int nakyvaMaasto;
    
    /**
     * Tässä luotavassa maastossa luodaan ensin nxn kokoisia Perlinkohinataulukoita,
     * joita yhdistämällä saadaan lopullinen maasto. Maastosta tehdään kerrallaan
     * vain pieni pala koko Perlintaukon koosta.
     * 
     * @param perlinKoko Perlinkohinataulukon koko, eli maaston blokkien määrä per sivu
     * @param resoluutio eli miten monta pistettä yhden blokin sisällä määritellään
     * @param nakyvaMaasto eli miten monen blokin kokoinen alue kerralla tehdään
     */

    public OmaGeneraattori(int perlinKoko, int resoluutio, int nakyvaMaasto) {
        p1 = new Perlin(perlinKoko);
        p2 = new Perlin(perlinKoko * 2);
        p3 = new Perlin(perlinKoko * 4);
        p4 = new Perlin(perlinKoko * 8);
        p5 = new Perlin(perlinKoko * 16);
        p6 = new Perlin(perlinKoko * 32);
        
        maasto = new double[nakyvaMaasto * resoluutio][nakyvaMaasto * resoluutio];
        x = perlinKoko / 2;
        y = perlinKoko / 2;
        this.resoluutio = resoluutio;
        this.nakyvaMaasto = nakyvaMaasto;
    }
    
    public double[][] luoMaasto() {
        maasto = new double[maasto.length][maasto[0].length];
        int koko = maasto.length;
        
        double alkuX = x - (double) nakyvaMaasto / 2;
        if (alkuX < 0) {
            alkuX = 0;
        } else if (alkuX > (double) p1.getRandomVektorit().length - 1 - nakyvaMaasto / 2) {
            alkuX = (double) p1.getRandomVektorit().length - 1 - nakyvaMaasto / 2;
        }
        
        double alkuY = y - (double) nakyvaMaasto / 2;
        if (alkuY < 0) {
            alkuY = 0;
        } else if (alkuY > (double) p1.getRandomVektorit()[0].length - 1 - nakyvaMaasto / 2) {
            alkuY = (double) p1.getRandomVektorit()[0].length - 1 - nakyvaMaasto / 2;
        }
        
        double[][] maasto1 = p1.luoMaasto(koko, alkuX, alkuY, alkuX + (double) nakyvaMaasto, alkuY + (double) nakyvaMaasto);
        double[][] maasto2 = p2.luoMaasto(koko, alkuX * 2, alkuY * 2, (alkuX + (double) nakyvaMaasto) * 2, (alkuY + (double) nakyvaMaasto) * 2);
        double[][] maasto3 = p3.luoMaasto(koko, alkuX * 4, alkuY * 4, (alkuX + (double) nakyvaMaasto) * 4, (alkuY + (double) nakyvaMaasto) * 4);
        double[][] maasto4 = p4.luoMaasto(koko, alkuX * 8, alkuY * 8, (alkuX + (double) nakyvaMaasto) * 8, (alkuY + (double) nakyvaMaasto) * 8);
        double[][] maasto5 = p5.luoMaasto(koko, alkuX * 16, alkuY * 16, (alkuX + (double) nakyvaMaasto) * 16, (alkuY + (double) nakyvaMaasto) * 16);
        double[][] maasto6 = p6.luoMaasto(koko, alkuX * 32, alkuY * 32, (alkuX + (double) nakyvaMaasto) * 32, (alkuY + (double) nakyvaMaasto) * 32);
        
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                maasto[i][j] = maasto1[i][j] + maasto2[i][j] / 4 + maasto3[i][j] / 8 + maasto4[i][j] / 16 + maasto5[i][j] / 32 + maasto6[i][j] / 64;
            }
        }
        
        return maasto;
    }
        
    public void liikuta(double dx, double dy) {
        x += dx * (double) nakyvaMaasto;
        if (x < 0) {
            x = 0;
        } else if (x > p1.getRandomVektorit().length - 1) {
            x = p1.getRandomVektorit().length - 1;
        }
        y += dy * (double) nakyvaMaasto;
        if (y < 0) {
            y = 0;
        } else if (y > p1.getRandomVektorit()[0].length - 1) {
            y = p1.getRandomVektorit().length - 1;
        }
    }

    public int getNakyvaMaasto() {
        return nakyvaMaasto;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
