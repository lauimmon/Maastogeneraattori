/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.maastogeneraattori;

import java.awt.Dimension;
import maastogeneraattori.algoritmit.Perlinkohina;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import maastogeneraattori.algoritmit.TimanttiNelio;
import maastogeneraattori.grafiikka.Grafiikka;
import maastogeneraattori.grafiikka.RGB;
import maastogeneraattori.laskenta.Kvaternio;
import maastogeneraattori.laskenta.Maasto;
import maastogeneraattori.laskenta.Vektori;



/**
 *
 * @author lauimmon
 */
public class Maastogeneraattori {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TimanttiNelio tn = new TimanttiNelio(50);
        int koko = tn.getKoko();
        
        tn.asetaArvot(0, 1, 0.5);
        
        Maasto maasto = tn.getMaasto();
        

        
        JFrame frame = new JFrame("Maasto");
        
        frame.setPreferredSize(new Dimension(1000, 1000));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Grafiikka g = new Grafiikka(maasto);
        frame.getContentPane().add(g);
        
        frame.pack();
        frame.setVisible(true);
        
        g.piirra();
    }
    
}
