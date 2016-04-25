/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.grafiikka;



import maastogeneraattori.maailma.Maailma;
import java.awt.Graphics;
import java.util.Collections;
import java.util.PriorityQueue;
import javax.swing.JPanel;
import maastogeneraattori.laskenta.Kolmio;
import maastogeneraattori.laskenta.Kvaternio;
import maastogeneraattori.laskenta.Vektori;

/**
 * Luokan avulla voidaan esittää maasto ruudulla.
 * 
 * Luokkaan säilötään tieto katsojan paikasta ja katseen suunnasta, sekä itse maastosta ja sen esityksestä ruudulla.
 * 
 * @author lauimmon
 */
public class Grafiikka extends JPanel{
    Maailma maailma;
    XY[][] piirrettava;
    Vektori katsojanPaikka = new Vektori(0.5, 3.0, -2.0);
    Kvaternio katsojanSuunta = Kvaternio.uusiKaanto(-0.82, 1.0, 0.0, 0.0);
    
    /**
     * Luodaan ruudulla esitettävä esitys maaston tietojen pohjalta.
     * 
     * @param maailma , jossa tallennettu tiedot maastosta ja valo-olosuhteista
     */
    
    public Grafiikka(Maailma maailma) {
        this.maailma = maailma;
       
        luoPiirrettavaKartta();
    }
    
    public void setMaailma(Maailma maailma) {
        this.maailma = maailma;
        
        luoPiirrettavaKartta();
    }
    
    private void luoPiirrettavaKartta() {
        Vektori[][] katsojanNakyma = katsojanNakyma(maailma);
        
        
        double etaisyys = 0.1;
        double nakyma = Math.PI / 3;
        piirrettava = new XY[maailma.getPituus()][maailma.getLeveys()];
        int leveys = 1000, korkeus = 1000;
        double skaala = leveys / 2 / Math.tan(nakyma / 2);
        for (int i = 0; i < katsojanNakyma.length; i++) {
            for (int j = 0; j < katsojanNakyma[0].length; j++) {
                Vektori p = katsojanNakyma[i][j];
                double x = p.getX(), y = p.getY(), z = p.getZ();
                if (z >= etaisyys) {
                    double vali = skaala / z;
                    piirrettava[i][j] = new XY(leveys / 2 + (int) (x * vali), korkeus / 2 - (int) (y * vali));
                } else {
                    piirrettava[i][j] = null;
                }
            }
        }
    }

    private Vektori[][] katsojanNakyma(Maailma maailma) {
        Vektori[][] katsojanNakyma = new Vektori[this.maailma.getPituus()][this.maailma.getLeveys()];
        
        for (int i = 0; i < maailma.getPituus(); i++) {
            for (int j = 0; j < maailma.getLeveys(); j++) {
                Vektori p = maailma.getMaasto()[i][j];
                Vektori t = p.vahenna(katsojanPaikka);
                Vektori r = katsojanSuunta.kaanna(t);
                katsojanNakyma[i][j] = r;
            }
        }
        
        return katsojanNakyma;
    }
    
    /**
     * Piirtää katsojalle näkyvän tilanteen ruudulle.
     */
    
    public void piirra() {
        repaint();
    }
    
    /**
     * Piirtää maaston kolmioidun pinnan ruudulle katsojan näkökulmasta katsottuna.
     * 
     * @param g 
     */
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Kolmio[] kolmiot = maailma.getKolmiomaasto();
        
        for (int i = 0; i < kolmiot.length; i++) {
            Kolmio k = kolmiot[kolmiot.length - 1 - i];
            
            XY xy0 = piirrettava[k.getXKoordinaatti(0)][k.getYKoordinaatti(0)];
            XY xy1 = piirrettava[k.getXKoordinaatti(1)][k.getYKoordinaatti(1)];
            XY xy2 = piirrettava[k.getXKoordinaatti(2)][k.getYKoordinaatti(2)];
            double pistetulo = - maailma.getMaasto()[k.getXKoordinaatti(0)][k.getYKoordinaatti(0)].vahenna(katsojanPaikka).normalisoi().pistetulo(k.getNormaali());
            
            if (pistetulo > 0) {
                int[] x = {xy0.x, xy1.x, xy2.x}, y = {xy0.y, xy1.y, xy2.y};
                g.setColor(k.getVari());
                g.fillPolygon(x, y, 3);
            }
        }
    }
    
    /**
     * Liikuttaa katsojaa haluttuun suuntaan koordinaatistossa.
     * Tällöin myös ruudulle piirrettävä näkymä pitää laskea uudestaan.
     *
     * @param x matka x-suunnassa
     * @param y matka y-suunnassa
     * @param z matka z-suunnassa
     */
    
    public void liikutaKatsojaa(double x, double y, double z) {
        katsojanPaikka = katsojanPaikka.lisaa(new Vektori(x, y, z));
        
        luoPiirrettavaKartta();
    }
    
    /**
     * Kääntää katsojaa sivusuunnassa ja vertikaalisessa suunnassa. Eli katsoja voi
     * kääntyä oikealle ja vasemmalle, tai katsoa ylös tai alas.
     * Tällöin myös ruudulle piirrettävä näkymä pitää laskea uudestaan.
     * 
     * @param horisontaalinen
     * @param vertikaalinen 
     */
    
    public void kaanna(double horisontaalinen, double vertikaalinen) {
        // Täydennä myöhemmin metodi, jolla katsojan näkökenttää käännetään
    
        luoPiirrettavaKartta();
    }
}
