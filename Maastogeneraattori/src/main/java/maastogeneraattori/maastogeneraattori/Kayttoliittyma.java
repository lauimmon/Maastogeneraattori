
package maastogeneraattori.maastogeneraattori;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import maastogeneraattori.algoritmit.OmaGeneraattori;
import maastogeneraattori.grafiikka.Grafiikka;

/**
 * Graafinen käyttöliittymä.
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
    
    /**
     * Luo oman generaattorin annettujen parametrien mukaan.
     * 
     * @param perlinKoko maailman blokkien määrä per sivu
     * @param resoluutio näkyvän maaston pisteiden määrä per sivu
     * @param maastoKoko näkyvän maaston blokkien määrä per sivu
     */
    
    public Kayttoliittyma(int perlinKoko, int resoluutio, int maastoKoko) {
        this.perlinKoko = perlinKoko;
        this.resoluutio = resoluutio;
        this.maastoKoko = maastoKoko;
        
        generaattori = new OmaGeneraattori(perlinKoko, resoluutio, maastoKoko);
        
    }
    
    /**
     * Käynnistää käyttöliittymän ja luo ikkunan.
     */
    
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

    /**
     * Nuolinäppäimiä pohjaan painettaessa kartalla liikutaan nuolta vastaavaan suuntaan.
     * 
     * @param e 
     */
    
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
