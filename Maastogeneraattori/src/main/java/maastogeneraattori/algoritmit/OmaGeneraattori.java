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
    private double maastonKeskikohtaX;
    private double maastonKeskikohtaY;
    private int resoluutio;
    private int nakyvatBlokit;
    
    /**
     * Tässä luotavassa maastossa luodaan ensin nxn kokoisia Perlinkohinataulukoita,
     * joita yhdistämällä saadaan lopullinen maasto. Maastosta tehdään kerrallaan
     * vain pieni pala koko Perlintaukon koosta.
     * 
     * @param perlinKoko Perlinkohinataulukon koko, eli maaston blokkien määrä per sivu
     * @param resoluutio eli miten monta pistettä yhden blokin sisällä määritellään
     * @param nakyvatBlokit eli miten monen blokin kokoinen alue kerralla tehdään
     */

    public OmaGeneraattori(int perlinKoko, int resoluutio, int nakyvatBlokit) {
        p1 = new Perlin(perlinKoko);
        p2 = new Perlin(perlinKoko * 2);
        p3 = new Perlin(perlinKoko * 4);
        p4 = new Perlin(perlinKoko * 8);
        p5 = new Perlin(perlinKoko * 16);
        p6 = new Perlin(perlinKoko * 32);
        
        maasto = new double[nakyvatBlokit * resoluutio][nakyvatBlokit * resoluutio];
        maastonKeskikohtaX = (double) perlinKoko / 2;
        maastonKeskikohtaY = (double) perlinKoko / 2;
        this.resoluutio = resoluutio;
        this.nakyvatBlokit = nakyvatBlokit;
    }
    
    public double[][] luoMaasto() {
        maasto = new double[maasto.length][maasto[0].length];
        int koko = maasto.length;
        
        double alkuX = maastonKeskikohtaX - (double) nakyvatBlokit / 2;
        
        double alkuY = maastonKeskikohtaY - (double) nakyvatBlokit / 2;
        
        double[][] maasto1 = p1.luoMaasto(koko, alkuX, alkuY, alkuX + (double) nakyvatBlokit, alkuY + (double) nakyvatBlokit);
        double[][] maasto2 = p2.luoMaasto(koko, alkuX * 2, alkuY * 2, (alkuX + (double) nakyvatBlokit) * 2, (alkuY + (double) nakyvatBlokit) * 2);
        double[][] maasto3 = p3.luoMaasto(koko, alkuX * 4, alkuY * 4, (alkuX + (double) nakyvatBlokit) * 4, (alkuY + (double) nakyvatBlokit) * 4);
        double[][] maasto4 = p4.luoMaasto(koko, alkuX * 8, alkuY * 8, (alkuX + (double) nakyvatBlokit) * 8, (alkuY + (double) nakyvatBlokit) * 8);
        double[][] maasto5 = p5.luoMaasto(koko, alkuX * 16, alkuY * 16, (alkuX + (double) nakyvatBlokit) * 16, (alkuY + (double) nakyvatBlokit) * 16);
        double[][] maasto6 = p6.luoMaasto(koko, alkuX * 32, alkuY * 32, (alkuX + (double) nakyvatBlokit) * 32, (alkuY + (double) nakyvatBlokit) * 32);
        
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                maasto[i][j] = maasto1[i][j] + maasto2[i][j] / 4 + maasto3[i][j] / 8 + maasto4[i][j] / 16 + maasto5[i][j] / 32 + maasto6[i][j] / 64;
            }
        }
        
        return maasto;
    }
        
    public void liikuta(double dx, double dy) {
        maastonKeskikohtaX += dx * (double) nakyvatBlokit;
        if (maastonKeskikohtaX < (double) nakyvatBlokit / 2) {
            maastonKeskikohtaX = (double) nakyvatBlokit / 2;
        } else if (maastonKeskikohtaX > p1.getRandomVektorit().length - 1 - (double) nakyvatBlokit / 2) {
            maastonKeskikohtaX = p1.getRandomVektorit().length - 1 - (double) nakyvatBlokit / 2;
        }
        maastonKeskikohtaY += dy * (double) nakyvatBlokit;
        if (maastonKeskikohtaY < (double) nakyvatBlokit / 2) {
            maastonKeskikohtaY = (double) nakyvatBlokit / 2;
        } else if (maastonKeskikohtaY > p1.getRandomVektorit()[0].length - 1 - (double) nakyvatBlokit / 2) {
            maastonKeskikohtaY = p1.getRandomVektorit().length - 1 - (double) nakyvatBlokit / 2;
        }
    }

    public int getNakyvaMaasto() {
        return nakyvatBlokit;
    }

    public double getmaastonKeskikohtaX() {
        return maastonKeskikohtaX;
    }

    public double getmaastonKeskikohtaY() {
        return maastonKeskikohtaY;
    }
}
