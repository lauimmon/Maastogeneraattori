
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
        long alku = System.currentTimeMillis();
        generaattori = new OmaGeneraattori(perlinKoko, resoluutio, maastoKoko);
        long loppu = System.currentTimeMillis();
        System.out.println("Oman generaattorin luonti: " + (loppu - alku));
    }
    
    @Override
    public void run() {
        frame = new JFrame("Maasto");
        
        frame.setPreferredSize(new Dimension(1000, 1000));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        long alku = System.currentTimeMillis();
        grafiikka = new Grafiikka(generaattori);
        long loppu = System.currentTimeMillis();
        System.out.println("Grafiikan luonti: " + (loppu - alku));
        
        alku = System.currentTimeMillis();
        frame.getContentPane().add(grafiikka);
        loppu = System.currentTimeMillis();
        System.out.println("Grafiikan lisäys content paneen: " + (loppu - alku));
        
        alku = System.currentTimeMillis();
        frame.addKeyListener(this);
        loppu = System.currentTimeMillis();
        System.out.println("Näppäimistön kuuntelijan lisäys: " + (loppu - alku));
        
        alku = System.currentTimeMillis();
        frame.pack();
        loppu = System.currentTimeMillis();
        System.out.println("Pakkaa: " + (loppu - alku));
        
        alku = System.currentTimeMillis();
        frame.setVisible(true);
        loppu = System.currentTimeMillis();
        System.out.println("Aseta näkyväksi: " + (loppu - alku));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        long alku = System.currentTimeMillis();
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            generaattori.liikuta(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            generaattori.liikuta(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            generaattori.liikuta(1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            generaattori.liikuta(-1, 0);
        }
        grafiikka.piirra();
        long loppu = System.currentTimeMillis();
        System.out.println("Liikuta ja piirra: " + (loppu - alku));
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
