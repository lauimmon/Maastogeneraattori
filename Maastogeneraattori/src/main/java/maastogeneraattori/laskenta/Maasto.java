/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.laskenta;

import java.awt.Color;
import maastogeneraattori.grafiikka.RGB;
import maastogeneraattori.grafiikka.XY;

/**
 * Luokan avulla tallennetaan maasto sekä neliöpintana, että kolmiopintana
 *
 * @author lauimmon
 */
public class Maasto {
    private double[][] maasto;
    private Vektori[][] kartta;
    private Kolmio[] kolmiomaasto;
    private RGB[][] varit;
    private double min, max;
    Vektori aurinko = new Vektori(3.6, 0.6, 3.9);
    double[][] varjot;
    XY[][] piirrettava;

    public Maasto(int i, int j) {
        this.maasto = new double[i][j];
        this.kartta = new Vektori[i][j];
        this.kolmiomaasto = new Kolmio[(maasto.length - 1) * (maasto[0].length - 1) * 2];;
        this.min = Double.MIN_VALUE;
        this.max = Double.MAX_VALUE;
        this.varit = new RGB[i][j];
        this.varjot = new double[maasto.length][maasto.length];
    }
    
    public void setArvo(int i, int j, double arvo) {
        maasto[i][j] = arvo;
        min = Math.min(arvo, min);
        max = Math.max(arvo, max);
    }

    public void setAurinko(Vektori aurinko) {
        this.aurinko = aurinko;
    }

    public void setPiirrettavaKartta(XY[][] piirrettavaKartta) {
        piirrettava = piirrettavaKartta;
    }
    
    public double getArvo(int i, int j){
        return maasto[i][j];
    }

    public double[][] getMaasto() {
        return maasto;
    }
    
    public XY[][] getPiirrettava() {
        return piirrettava;
    }

    public Kolmio[] getKolmiomaasto() {
        return kolmiomaasto;
    }

    public Vektori[][] getMaastoVektorit() {
        return kartta;
    }
    
    public int getPituus() {
        return getMaasto().length;
    }
    
    public int getLeveys() {
        return getMaasto()[0].length;
    }
    
    public double suhteellinenKorkeus(double i, double j) {
        double korkeus = maasto[(int) (i * (maasto.length - 1))][(int) (j * (maasto.length-1))];
        return (korkeus - this.min) / (this.max - this.min);
    }
    
    private RGB sininen = new RGB (0.0, 0.0, 1.0);
    private RGB vihrea = new RGB (0.0, 1.0, 0.0);
    private RGB valkoinen = new RGB (1.0, 1.0, 1.0);
    
    public RGB getVari(double i, double j) {
      double a = suhteellinenKorkeus(i, j);
      if (a < .5)
        return sininen.summa(vihrea.erotus(sininen).skaalaa((a - 0.0) / 0.5));
      else
        return vihrea.summa(valkoinen.erotus(vihrea).skaalaa((a - 0.5) / 0.5));
    }
    
    public void kolmioiJaLisaaValoJaVarjot() {
        asetaKarttaJaVarit();
        kolmioiMaasto();
        lisaaVarjot();
        lisaaValo();
    }
    
    private void kolmioiMaasto() {
        int n = 0;
        for (int i = 0; i < getPituus() - 1; i++) {
            for (int j = 0; j < getLeveys() - 1; j++) {
                kolmiomaasto[n++] = new Kolmio(i, j, i + 1, j, i, j + 1);
                kolmiomaasto[n++] = new Kolmio(i + 1, j, i + 1, j + 1, i, j + 1);
            }
        }
    }

    private void asetaKarttaJaVarit() {
        for (int i = 0; i < getPituus(); i++) {
            for (int j = 0; j < getLeveys(); j++) {
                double x = (double) i / getLeveys(), y = (double) j / getPituus();
                double korkeus = suhteellinenKorkeus(x, y);
                kartta[i][j] = new Vektori(x, y, korkeus); // korkeus * 0.7 ?
                varit[i][j] = getVari(x, y);
            }
        }
    }
    
    private void lisaaValo() {
        double heijastus = .3;
        double diffuusiValo = 4.0;
        
        Vektori[][] normaalit = new Vektori[getPituus()][getLeveys()];
        
        for (int i = 0; i < maasto.length; i++) {
            for (int j = 0; j < maasto.length; j++) {
                normaalit[i][j] = new Vektori(0.0, 0.0, 0.0);
            }
        }
        
        // lasketaan normaalit jokaiselle maaston pisteelle
        for (int i = 0; i < kolmiomaasto.length; i++) {
            Kolmio k = kolmiomaasto[i];
            Vektori v0 = kartta[k.getXKoordinaatti(0)][k.getYKoordinaatti(0)];
            Vektori v1 = kartta[k.getXKoordinaatti(1)][k.getYKoordinaatti(1)];
            Vektori v2 = kartta[k.getXKoordinaatti(2)][k.getYKoordinaatti(2)];
            Vektori normaali = v0.vahenna(v1).ristitulo(v2.vahenna(v1)).normalisoi();
            k.setNormaali(normaali);
            for (int j = 0; j < 3; j++) {
                normaalit[kolmiomaasto[i].getXKoordinaatti(j)][kolmiomaasto[i].getYKoordinaatti(j)] = normaalit[kolmiomaasto[i].getXKoordinaatti(j)][kolmiomaasto[i].getYKoordinaatti(j)].lisaa(kolmiomaasto[i].getNormaali());
            }
        }
        
        // lasketaan värit jokaiselle pisteelle ja keskiarvot kolmioille
        for (int i = 0; i < kolmiomaasto.length; i++) {
            RGB keskiarvo = new RGB(0.0, 0.0, 0.0);
            for (int j = 0; j < 3; j++) {
                int x = kolmiomaasto[i].getXKoordinaatti(j);
                int y = kolmiomaasto[i].getYKoordinaatti(j);
                Vektori piste = kartta[x][y];
                RGB vari = varit[x][y];
                Vektori normaali = normaalit[x][y].normalisoi();
                Vektori valo = piste.vahenna(aurinko);
                double etaisyys = valo.pituus();
                double pistetulo = valo.normalisoi().pistetulo(normaali);
                double varjo = varjot[x][y];
                double valaistus = heijastus + diffuusiValo * ((pistetulo < 0.0) ? - pistetulo : 0.0) / etaisyys * varjo;
                vari = vari.skaalaa(valaistus);
                kolmiomaasto[i].setVari(j, vari);
                keskiarvo = keskiarvo.summa(vari);
            }
            kolmiomaasto[i].setVari(new Color(keskiarvo.skaalaa(1.0/3.0).toRGB()));
        }
    }
    
    private void lisaaVarjot() {
        for (int i = 0; i < maasto.length; i++) {
            for (int j = 0; j < maasto.length; j++) {
                varjot[i][j] = 1.0;
                Vektori piste = kartta[i][j];
                Vektori valo = aurinko.vahenna(piste);
                double etaisyys = (maasto.length - 1) * Math.sqrt(valo.getX() * valo.getX() + valo.getY() * valo.getY());
                for (int k = 1; k < etaisyys; k++) {
                    Vektori v = piste.lisaa(valo.skaalaa((double) k / etaisyys));
                    double sx = v.getX(), sy = v.getY(), sz = v.getZ();
                    if (sx < 0 || sy < 0 || sx >= maasto.length || sy >= maasto.length) {
                        break;
                    }
                    double maanKorkeus = suhteellinenKorkeus(sx, sy);
                    if (maanKorkeus >= sz) {
                        varjot[i][j] = 0.0;
                        break;
                    }
                }
            }   
        }
    }
}
