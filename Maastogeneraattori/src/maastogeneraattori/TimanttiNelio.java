/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori;

import java.util.Random;

/**
 *
 * @author lauimmon
 */
class TimanttiNelio {
    private double[][] ruudukko;
    private int min;
    private int max;
    private Random rand;
    private int iteraariot;

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
    
    public void asetaArvot(int min, int max) {
        this.min = min;
        this.max = max;
        this.ruudukko = new double[ruudukko.length][ruudukko.length];
        
        asetaNurkat();
        
        jaaKartta(ruudukko.length - 1);
    }

    private void asetaNurkat() {
        ruudukko[0][0] = rand.nextDouble() * (max-min) + min;
        ruudukko[0][ruudukko.length-1] = rand.nextDouble() * (max-min) + min;
        ruudukko[ruudukko.length-1][0] = rand.nextDouble() * (max-min) + min;
        ruudukko[ruudukko.length-1][ruudukko.length-1] = rand.nextDouble() * (max-min) + min;
    }

    private void jaaKartta(int koko) {
        int puolet = koko / 2;
        System.out.println(puolet);
        if (puolet < 1) {
            return;
        }
        
        for (int y = puolet; y < ruudukko.length; y += koko) {
            for (int x = puolet; x < ruudukko.length; x += koko) {
                nelioaskel(x, y, puolet, (rand.nextDouble()* (max-min) + min) / 10);
            }
        }
        
        for (int y = 0; y < ruudukko.length; y += puolet) {
            for (int x = (y + puolet) % koko; x < ruudukko.length; x += koko) {
                timanttiaskel(x, y, puolet, (rand.nextDouble()* (max-min) + min) / 10);
            }
        }
        
        tulosta();
        
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
    
    public void tulosta() {
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko[0].length; j++) {
                System.out.print(ruudukko[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
