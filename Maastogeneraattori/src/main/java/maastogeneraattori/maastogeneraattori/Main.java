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
import maastogeneraattori.algoritmit.OmaGeneraattori;
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

       
        
        Kayttoliittyma kali = new Kayttoliittyma(5, 50, 5);
        kali.run();
        
        
        
    }
    
}
