/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.maastogeneraattori;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import maastogeneraattori.algoritmit.OmaGeneraattori;
import maastogeneraattori.grafiikka.Grafiikka;

/**
 * 
 * 
 * @author lauimmon
 */
public class Kayttoliittyma implements Runnable, KeyListener {

    private JFrame frame;
    private OmaGeneraattori generaattori;
    private Grafiikka grafiikka;
    private int perlinKoko;
    private int resoluutio;
    private int maastoKoko;
    
    public Kayttoliittyma(int perlinKoko, int resoluutio, int maastoKoko) {
        this.perlinKoko = perlinKoko;
        this.resoluutio = resoluutio;
        this.maastoKoko = maastoKoko;
        generaattori = new OmaGeneraattori(perlinKoko, resoluutio, maastoKoko);
    }
    
    @Override
    public void run() {
        frame = new JFrame("Maasto");
        
        frame.setPreferredSize(new Dimension(1000, 1000));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        grafiikka = new Grafiikka(generaattori);
        frame.getContentPane().add(grafiikka);
        
        frame.addKeyListener(this);
        
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            generaattori.liikuta(0, 0.01);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            generaattori.liikuta(0, -0.01);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            generaattori.liikuta(0.01, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            generaattori.liikuta(-0.01, 0);
        }
        grafiikka.piirra();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
