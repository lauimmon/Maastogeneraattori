/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.algoritmit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Luokalla generoidaan maasto timantti-neliö-algoritmilla.
 * 
 * Timantti-neliö-algoritmi toimii niin, että annetaan neliön kulmille arvot, otetaan
 * piste neliön keskeltä ja annetaan sille arvo, joka on näiden nurkkien keskiarvo
 * lisättynä pieneen satunnaisarvoon. Sitten lasketut pisteet timanttien nurkkina
 * otetaan timenttien keskeltä pisteitä ja lasketaan samalla tavalla niille arvo
 * niiden nurkkien keskiarvosta ja satunnaisarvosta. Näin jatketaan vuorotellen
 * neliö- ja timanttiaskeleita, kunnes on saatu haluttu määrä pisteitä.
 * 
 * @author lauimmon
 */
public class TimanttiNelio {
    private double[][] maasto;
    private double jyrkkyys;
    Random rand = new Random();
    private int iteraariot;

    /**
     * Luo taulukon, johon myöhemmin sijoitetaan timantti-neliö-algoritmilla tuotetut korkeusarvot.
     * 
     * @param koko on haluttu kokoluokka maastolle. Luodussa maastossa on 
     * (2^n + 1)*(2^n + 1) pistettä, jossa n on kokonaisluku, jolla 2^n + 1 >= koko.
     * Luodun maaston koko on siis syötetty arvo koko, tai suurempi, mutta kuitenkin
     * korkeintaan kaksi kertaa suurempi.
     */
    public TimanttiNelio(int koko) {
        int i = 0;
        while (true) {
            if (Math.pow(2, i) + 1 >= koko) {
                koko = (int) Math.pow(2, i) + 1;
                break;
            }
            i++;
        }
        iteraariot = i;
        
        maasto = new double[koko][koko];
    }
    
    /**
     * Laskee korkeusarvot taulukkoon timantti-neliö-algoritmilla.
     * 
     * 
     * @param min minimiarvo nurkkien korkeudelle
     * @param max maksimiarvo nurkien korkeudelle
     * @param jyrkkyys kuvaa maaston jyrkkyyttä. Arvo on liukuluku 0.0 ja 1.0 väliltä. 
     * Tasainen maasto arvolla 0, vuoristoinen maasto arvolla 1.
     */

    public void luoMaasto(double min, double max, double jyrkkyys) {
        this.jyrkkyys = jyrkkyys;
        
        asetaNurkat(min, max);
        
        jaaKartta(maasto.length - 1);
    }

    private void asetaNurkat(double min, double max) {
        double arvo = rand.nextDouble() * (max-min) + min;
        asetaArvo(0, 0, arvo);
        
        arvo = rand.nextDouble() * (max-min) + min;
        asetaArvo(0, maasto.length - 1, arvo);
        
        arvo = rand.nextDouble() * (max-min) + min;
        asetaArvo(maasto.length - 1, 0, arvo);
        
        arvo = rand.nextDouble() * (max-min) + min;
        asetaArvo(maasto.length - 1, maasto.length - 1, arvo);
    }
    
    private void asetaArvo(int i, int j, double arvo) {
        maasto[i][j] = arvo;
    }

    private void jaaKartta(int koko) {
        int puolet = koko / 2;
        double vaihtelu = jyrkkyys * koko;
        if (puolet < 1) {
            return;
        }
        
        for (int y = puolet; y < maasto.length - 1; y += koko) {
            for (int x = puolet; x < maasto.length - 1; x += koko) {
                nelioaskel(x, y, puolet, rand.nextDouble() * vaihtelu * 2 - vaihtelu);
            }
        }
        
        for (int y = 0; y < maasto.length; y += puolet) {
            for (int x = (y + puolet) % koko; x < maasto.length; x += koko) {
                timanttiaskel(x, y, puolet, rand.nextDouble() * vaihtelu * 2 - vaihtelu);
            }
        }
        
        jaaKartta(puolet);
    }

    private void nelioaskel(int x, int y, int koko, double offset) {
        double yht = maasto[x + koko][y + koko];
        yht += maasto[x + koko][y - koko];
        yht += maasto[x - koko][y + koko];
        yht += maasto[x - koko][y - koko];
        yht /= 4;
        asetaArvo(x, y, yht + offset);
    }
    
    private void timanttiaskel(int x, int y, int koko, double offset) {
        double yht = 0;
        int i = 0;
        if (y - koko >= 0) {
            yht += maasto[x][y - koko];
            i++;
        }
        if (x + koko < maasto.length) {
            yht += maasto[x + koko][y];
            i++;
        }
        if (y + koko < maasto.length) {
            yht += maasto[x][y + koko];
            i++;
        }
        if (x - koko >= 0) {
            yht += maasto[x - koko][y];
            i++;
        }
        yht /= i;
        asetaArvo(x, y, yht + offset);
    }

    /**
     * 
     * @return luotu maasto 
     */
    
    public double[][] getMaasto() {
        return maasto;
    }
    
    /**
     * 
     * @return luotavan maaston koko 
     */
    
    public int getKoko() {
        return maasto.length;
    }
    
}
