
package maastogeneraattori.grafiikka;



import maastogeneraattori.maailma.Maailma;
import java.awt.Graphics;
import javax.swing.JPanel;
import maastogeneraattori.algoritmit.OmaGeneraattori;
import maastogeneraattori.laskenta.Kolmio;
import maastogeneraattori.laskenta.Kvaternio;
import maastogeneraattori.laskenta.Vektori;

/**
 * Luokan avulla voidaan esittää maasto graafisesti.
 * 
 * Luokkaan säilötään tieto katsojan paikasta ja katseen suunnasta, 
 * sekä itse maastosta ja sen esityksestä ruudulla.
 * 
 * @author lauimmon
 */
public class Grafiikka extends JPanel{
    private OmaGeneraattori generaattori;
    private Maailma maailma;
    private XY[][] piirrettava;
    private Vektori katsojanPaikka = new Vektori(0.5, 1.5, -1.0);
    private Kvaternio katsojanSuunta = Kvaternio.uusiKaanto(-0.82, 1.0, 0.0, 0.0);
    
    /**
     * Luo ruudulla esitettävän esityksen maaston tietojen pohjalta. Maaston vektoriesityksetä
     * luodaan ensin vektoriesitys katsojan näkymästä ja sitten siitä ruudulle
     * piirrettävästä näkymästä pikseliesitys.
     * 
     * @param o on generaattori, jolla maasto luodaan
     */
    
    public Grafiikka(OmaGeneraattori o) {
        this.generaattori = o;
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
                Vektori p = maailma.getKartta()[i][j];
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
     * Piirtää maaston kolmioidun pinnan ruudulle pikseliesityksen perusteella.
     * 
     * @param g grafiikka
     */
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        maailma = new Maailma(generaattori.luoMaasto());
        
        luoPiirrettavaKartta();
        
        
        Kolmio[] kolmiot = maailma.getKolmiomaasto();
        
        for (int i = 0; i < kolmiot.length; i++) {
            Kolmio k = kolmiot[kolmiot.length - 1 - i];
            
            XY xy0 = piirrettava[k.getXKoordinaatti(0)][k.getYKoordinaatti(0)];
            XY xy1 = piirrettava[k.getXKoordinaatti(1)][k.getYKoordinaatti(1)];
            XY xy2 = piirrettava[k.getXKoordinaatti(2)][k.getYKoordinaatti(2)];
            double pistetulo = - maailma.getKartta()[k.getXKoordinaatti(0)][k.getYKoordinaatti(0)].vahenna(katsojanPaikka).normalisoi().pistetulo(k.getNormaali());
            
            if (pistetulo > 0) {
                int[] x = {xy0.x, xy1.x, xy2.x}, y = {xy0.y, xy1.y, xy2.y};
                g.setColor(k.getVari());
                g.fillPolygon(x, y, 3);
            }
        }
    }
}
