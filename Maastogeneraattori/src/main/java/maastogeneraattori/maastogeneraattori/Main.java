/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.maastogeneraattori;

import java.awt.Dimension;
import java.util.Random;
import maastogeneraattori.algoritmit.Perlin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import maastogeneraattori.algoritmit.TimanttiNelio;
import maastogeneraattori.grafiikka.Grafiikka;
import maastogeneraattori.maailma.Maailma;



/**
 *
 * @author lauimmon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        TimanttiNelio m = new TimanttiNelio(100);
//        
//        m.luoMaasto(0, 1, 1.0);
//        
//        Maailma maailma = new Maailma(m.getMaasto());
//
//        
//        JFrame frame = new JFrame("Maasto");
//        
//        frame.setPreferredSize(new Dimension(1000, 1000));
//        
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        
//        Grafiikka g = new Grafiikka(maailma);
//        frame.getContentPane().add(g);
//        
//        frame.pack();
//        frame.setVisible(true);
//        
//        g.piirra();
//        
//        for (int i = 0; i < 18; i++) {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//
//            g.liikutaKatsojaa(0, -0.2, 0.2);
//            g.piirra();
//        }
        
        //---------------------------------------------//
        
        int perlinKoko = 100;
        int maastoKoko = 200;
        
        Perlin p1 = new Perlin(perlinKoko);
        Perlin p2 = new Perlin(perlinKoko);
        Perlin p3 = new Perlin(perlinKoko);
        Perlin p4 = new Perlin(perlinKoko);
        Perlin p5 = new Perlin(perlinKoko);
        
        double[][] maasto1 = p1.luoMaasto(maastoKoko, 0, 0, 63, 63);
        double[][] maasto2 = p2.luoMaasto(maastoKoko, 0, 0, 31, 31);
        double[][] maasto3 = p3.luoMaasto(maastoKoko, 0, 0, 15, 15);
        double[][] maasto4 = p4.luoMaasto(maastoKoko, 0, 0, 7, 7);
        double[][] maasto5 = p5.luoMaasto(maastoKoko, 0, 0, 3, 3);

        double[][] maasto = maasto5;
        

        
        for (int i = 0; i < maastoKoko / 2; i++) {
            for (int j = 0; j < maastoKoko / 2; j++) {
                maasto[i][j] = maasto[i][j] + maasto4[i][j];
            }
        }
        
        Maailma maailma = new Maailma(maasto);

        
    
        JFrame frame = new JFrame("Maasto");
        
        frame.setPreferredSize(new Dimension(1000, 1000));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Grafiikka g = new Grafiikka(maailma);
        frame.getContentPane().add(g);
        
        frame.pack();
        frame.setVisible(true);
        
        g.piirra();
        
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }


            //g.liikutaKatsojaa(0, -0.2, 0.2);
            g.piirra();
        }
        
        
        
        
    }
    
}
