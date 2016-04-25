/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.maastogeneraattori;

import java.awt.Dimension;
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
//        TimanttiNelio m = new TimanttiNelio(10);
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
//            g.kaanna(0.1, 0);
//            g.piirra();
//        }
        
        //---------------------------------------------//
        
        Perlin p = new Perlin(5);
        
        Maailma maailma = new Maailma(p.luoMaasto(500, 0, 0, 4, 4));

        
    
        JFrame frame = new JFrame("Maasto");
        
        frame.setPreferredSize(new Dimension(1000, 1000));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Grafiikka g = new Grafiikka(maailma);
        frame.getContentPane().add(g);
        
        frame.pack();
        frame.setVisible(true);
        
        g.piirra();
        
        for (int i = 0; i < 18; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }


//            g.setMaailma(new Maailma(p.luoMaasto(50, 500 + i, 500)));
            g.piirra();
        }
        
        
        
        
    }
    
}
