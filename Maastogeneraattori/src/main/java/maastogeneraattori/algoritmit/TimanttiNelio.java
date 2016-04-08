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
import java.util.logging.Level;
import java.util.logging.Logger;
import maastogeneraattori.grafiikka.RGB;
import maastogeneraattori.laskenta.Maasto;

/**
 * Luokalla generoidaan maasto timantti-neliö-algoritmilla.
 * 
 * @author lauimmon
 */
public class TimanttiNelio {
    private Maasto maasto;
    private double jyrkkyys;
    private Random rand;
    private int iteraariot;
    private double min, max;

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
        
        this.maasto = new Maasto(koko, koko);
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
        this.min = Double.MIN_VALUE;
        this.max = Double.MAX_VALUE;
        
        asetaNurkat(min, max);
        
        jaaKartta(maasto.getPituus() - 1);
        
        maasto.kolmioiJaLisaaValoJaVarjot();
    }

    private void asetaNurkat(double min, double max) {
        double arvo = rand.nextDouble() * (max-min) + min;
        asetaArvo(0, 0, arvo);
        
        arvo = rand.nextDouble() * (max-min) + min;
        asetaArvo(0, maasto.getPituus() - 1, arvo);
        
        arvo = rand.nextDouble() * (max-min) + min;
        asetaArvo(maasto.getPituus() - 1, 0, arvo);
        
        arvo = rand.nextDouble() * (max-min) + min;
        asetaArvo(maasto.getPituus() - 1, maasto.getPituus() - 1, arvo);
    }
    
    private void asetaArvo(int i, int j, double arvo) {
        maasto.setArvo(i, j, arvo);
        this.min = Math.min(arvo, this.min);
        this.max = Math.max(arvo , this.max);
    }

    private void jaaKartta(int koko) {
        int puolet = koko / 2;
        double vaihtelu = jyrkkyys * koko;
        if (puolet < 1) {
            return;
        }
        
        for (int y = puolet; y < maasto.getPituus() - 1; y += koko) {
            for (int x = puolet; x < maasto.getPituus() - 1; x += koko) {
                nelioaskel(x, y, puolet, rand.nextDouble() * vaihtelu * 2 - vaihtelu);
            }
        }
        
        for (int y = 0; y < maasto.getPituus(); y += puolet) {
            for (int x = (y + puolet) % koko; x < maasto.getPituus(); x += koko) {
                timanttiaskel(x, y, puolet, rand.nextDouble() * vaihtelu * 2 - vaihtelu);
            }
        }
        
        jaaKartta(puolet);
    }

    private void nelioaskel(int x, int y, int koko, double offset) {
        double yht = maasto.getArvo(x + koko, y + koko);
        yht += maasto.getArvo(x + koko, y - koko);
        yht += maasto.getArvo(x - koko, y + koko);
        yht += maasto.getArvo(x - koko, y - koko);
        yht /= 4;
        asetaArvo(x, y, yht + offset);
    }
    
    private void timanttiaskel(int x, int y, int koko, double offset) {
        double yht = 0;
        int i = 0;
        if (y - koko >= 0) {
            yht += maasto.getArvo(x, y - koko);
            i++;
        }
        if (x + koko < maasto.getPituus()) {
            yht += maasto.getArvo(x + koko, y);;
            i++;
        }
        if (y + koko < maasto.getPituus()) {
            yht += maasto.getArvo(x, y + koko);;
            i++;
        }
        if (x - koko >= 0) {
            yht += maasto.getArvo(x - koko, y);;
            i++;
        }
        yht /= i;
        asetaArvo(x, y, yht + offset);
    }
    
    /**
     * Tulostaa generoidun maaston 2D-taulukkona.
     */
    public void tulosta() {
        for (int i = 0; i < maasto.getPituus(); i++) {
            for (int j = 0; j < maasto.getPituus(); j++) {
                System.out.print(maasto.getArvo(i, j) + " ");
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
            
            
            for (int i = 0; i < maasto.getPituus(); i++) {
                for (int j = 0; j < maasto.getPituus(); j++) {
                    bw.write(String.valueOf(maasto.getArvo(i, j)) + " ");
                }
                bw.newLine();
            }
            
            bw.close();
            
        } catch (IOException ex) {
            System.out.println("Tiedoston kirjoitus epäonnistui");
        }
    }

    public Maasto getMaasto() {
        return maasto;
    }
    
    public int getKoko() {
        return maasto.getPituus();
    }
    
}
