/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.grafiikka;



import java.awt.Graphics;
import javax.swing.JPanel;
import maastogeneraattori.laskenta.Kolmio;
import maastogeneraattori.laskenta.Kvaternio;
import maastogeneraattori.laskenta.Maasto;
import maastogeneraattori.laskenta.Vektori;

/**
 *
 * @author lauimmon
 */
public class Grafiikka extends JPanel{
    Maasto maasto;
    Vektori katsoja = new Vektori(0.5, -2.0, 3.0);
    
    public Grafiikka(Maasto maasto) {
        this.maasto = maasto;
        Vektori[][] katsojanNakyma = new Vektori[maasto.getPituus()][maasto.getLeveys()];
        Kvaternio kaanto = Kvaternio.uusiKaanto(-0.82, 1.0, 0.0, 0.0);
        for (int i = 0; i < maasto.getPituus(); i++) {
            for (int j = 0; j < maasto.getLeveys(); j++) {
                Vektori p = maasto.getMaastoVektorit()[i][j];
                Vektori t = p.vahenna(katsoja);
                Vektori r = kaanto.kaanna(t);
                katsojanNakyma[i][j] = r;
            }
        }
        
        double etaisyys = 0.1;
        double nakyma = Math.PI / 3;
        XY[][] ruudunNakyma = new XY[maasto.getPituus()][maasto.getLeveys()];
        int leveys = 1000, korkeus = 1000;
        double skaala = leveys / 2 / Math.tan(nakyma / 2);
        for (int i = 0; i < maasto.getPituus(); i++) {
            for (int j = 0; j < maasto.getLeveys(); j++) {
                Vektori p = katsojanNakyma[i][j];
                double x = p.getX(), y = p.getY(), z = p.getZ();
                if (y >= etaisyys) {
                    double vali = skaala / z;
                    ruudunNakyma[i][j] = new XY(leveys / 2 + (int) (x * vali), korkeus / 2 - (int) (y * vali));
                } else {
                    ruudunNakyma[i][j] = null;
                }
            }
        }
        
        maasto.setPiirrettavaKartta(ruudunNakyma);
        
    }
    
    public void piirra() {
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < maasto.getKolmiomaasto().length; i++) {
            Kolmio k = maasto.getKolmiomaasto()[i];
            XY[][] piirrettava = maasto.getPiirrettava();
            
            XY xy0 = piirrettava[k.getXKoordinaatti(0)][k.getYKoordinaatti(0)];
            XY xy1 = piirrettava[k.getXKoordinaatti(1)][k.getYKoordinaatti(1)];
            XY xy2 = piirrettava[k.getXKoordinaatti(2)][k.getYKoordinaatti(2)];
            double pistetulo = - maasto.getMaastoVektorit()[k.getXKoordinaatti(0)][k.getYKoordinaatti(0)].vahenna(katsoja).normalisoi().pistetulo(k.getNormaali());
            if (pistetulo > 0.0) {
                int[] x = {xy0.x, xy1.x, xy2.x}, y = {xy0.y, xy1.y, xy2.y};
                g.setColor(k.getVari());
                g.fillPolygon(x, y, 3);
            }
        }
    }
}
