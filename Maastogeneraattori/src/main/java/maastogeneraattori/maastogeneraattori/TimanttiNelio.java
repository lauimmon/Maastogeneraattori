/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.maastogeneraattori;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lauimmon
 */
public class TimanttiNelio {
    private double[][] ruudukko;
    private double jyrkkyys;
    private Random rand;
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
        this.iteraariot = i;
        
        this.ruudukko = new double[koko][koko];
        this.rand = new Random();
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
    public void asetaArvot(double min, double max, double jyrkkyys) {
        this.jyrkkyys = jyrkkyys;
        this.ruudukko = new double[ruudukko.length][ruudukko.length];
        
        asetaNurkat(min, max);
        
        jaaKartta(ruudukko.length - 1);
    }

    private void asetaNurkat(double min, double max) {
        ruudukko[0][0] = rand.nextDouble() * (max-min) + min;
        ruudukko[0][ruudukko.length-1] = rand.nextDouble() * (max-min) + min;
        ruudukko[ruudukko.length-1][0] = rand.nextDouble() * (max-min) + min;
        ruudukko[ruudukko.length-1][ruudukko.length-1] = rand.nextDouble() * (max-min) + min;
    }

    private void jaaKartta(int koko) {
        int puolet = koko / 2;
        double vaihtelu = jyrkkyys * koko;
        if (puolet < 1) {
            return;
        }
        
        for (int y = puolet; y < ruudukko.length - 1; y += koko) {
            for (int x = puolet; x < ruudukko.length - 1; x += koko) {
                nelioaskel(x, y, puolet, rand.nextDouble() * vaihtelu * 2 - vaihtelu);
            }
        }
        
        for (int y = 0; y < ruudukko.length; y += puolet) {
            for (int x = (y + puolet) % koko; x < ruudukko.length; x += koko) {
                timanttiaskel(x, y, puolet, rand.nextDouble() * vaihtelu * 2 - vaihtelu);
            }
        }
        
        jaaKartta(puolet);
    }

    private void nelioaskel(int x, int y, int koko, double offset) {
        double yht = ruudukko[x + koko][y + koko];
        yht += ruudukko[x + koko][y - koko];
        yht += ruudukko[x - koko][y + koko];
        yht += ruudukko[x - koko][y - koko];
        yht /= 4;
        ruudukko[x][y] = yht + offset;
    }
    
    private void timanttiaskel(int x, int y, int koko, double offset) {
        double yht = 0;
        int i = 0;
        if (y - koko >= 0) {
            yht += ruudukko[x][y-koko];
            i++;
        }
        if (x + koko < ruudukko.length) {
            yht += ruudukko[x + koko][y];
            i++;
        }
        if (y + koko < ruudukko.length) {
            yht += ruudukko[x][y + koko];
            i++;
        }
        if (x - koko >= 0) {
            yht += ruudukko[x - koko][y];
            i++;
        }
        yht /= i;
        ruudukko[x][y] = yht + offset;
    }
    
    /**
     * Tulostaa generoidun maaston 2D-taulukkona.
     */
    public void tulosta() {
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko[0].length; j++) {
                System.out.print(ruudukko[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * Tallentaa generoidun maaston 2D-taulukkona tekstitiedostoon "korkeuskartta.txt".
     */
    public void tallenna() {
        String tiedosto = "korkeuskartta.txt";
        
        try {
            FileWriter fw = new FileWriter(tiedosto);
            BufferedWriter bw = new BufferedWriter(fw);
            
            
            for (int i = 0; i < ruudukko.length; i++) {
                for (int j = 0; j < ruudukko.length; j++) {
                    bw.write(String.valueOf(ruudukko[i][j]) + " ");
                }
                bw.newLine();
            }
            
            bw.close();
            
        } catch (IOException ex) {
            System.out.println("Tiedoston kirjoitus epäonnistui");
        }
    }

    public double[][] getRuudukko() {
        return ruudukko;
    }
    
    
    
}
