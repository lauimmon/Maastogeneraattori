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
import maastogeneraattori.grafiikka.Maailma;

/**
 * Luokalla generoidaan maasto timantti-neliö-algoritmilla.
 * 
 * @author lauimmon
 */
public class TimanttiNelio implements Maasto {
    private double[][] maasto;
    private double jyrkkyys, min, max;
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
        iteraariot = i;
        
        min = Double.MAX_VALUE;
        max = Double.MIN_VALUE;
        
        maasto = new double[koko][koko];
        rand = new Random();
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
        min = Math.min(arvo, min);
        max = Math.max(arvo, max);
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
     * Tulostaa generoidun maaston 2D-taulukkona.
     */
    public void tulosta() {
        for (int i = 0; i < maasto.length; i++) {
            for (int j = 0; j < maasto.length; j++) {
                System.out.print(maasto[i][j] + " ");
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
            
            
            for (int i = 0; i < maasto.length; i++) {
                for (int j = 0; j < maasto.length; j++) {
                    bw.write(String.valueOf(maasto[i][j]) + " ");
                }
                bw.newLine();
            }
            
            bw.close();
            
        } catch (IOException ex) {
            System.out.println("Tiedoston kirjoitus epäonnistui");
        }
    }

    @Override
    public double[][] getMaasto() {
        return maasto;
    }
    
    public int getKoko() {
        return maasto.length;
    }
    
    /**
     * Laskee pisteen (i, j) korkeuden suhteessa maaston maksimi- ja minimikorkeuksiin
     * 
     * @param i x-koordinaatti väliltä [0.0, 1.0]
     * @param j y-koordinaatti väliltä [0.0, 1.0]
     * @return arvo väliltä [0.0, 1.0], alin kohta 0.0, korkein 1.0
     */
    
    @Override
    public double suhteellinenKorkeus(double i, double j) {
        double korkeus = maasto[(int) (i * (maasto.length - 1))][(int) (j * (maasto.length-1))];
        return (korkeus - this.min) / (this.max - this.min);
    }
    
    private RGB sininen = new RGB (0.0, 0.0, 1.0);
    private RGB vihrea = new RGB (0.0, 1.0, 0.0);
    private RGB valkoinen = new RGB (1.0, 1.0, 1.0);
    
    /**
     * Antaa pisteen värin. Piste annetaan x- ja y-koordinaatteina väliltä [0.0, 1.0]
     * 
     * @param i x-koordnaatti
     * @param j y-koordinaatti
     * @return RGB-väri
     */
    
    @Override
    public RGB getVari(double i, double j) {
      double a = suhteellinenKorkeus(i, j);
      if (a < .5)
        return sininen.summa(vihrea.erotus(sininen).skaalaa((a - 0.0) / 0.5));
      else
        return vihrea.summa(valkoinen.erotus(vihrea).skaalaa((a - 0.5) / 0.5));
    }
    
}
