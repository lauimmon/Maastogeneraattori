/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.maailma;

import java.awt.Color;
import maastogeneraattori.grafiikka.RGB;
import maastogeneraattori.laskenta.Kolmio;
import maastogeneraattori.laskenta.Vektori;

/**
 * Luokan avulla tallennetaan maasto sekä korkeusarvojen matriisina, kolmioituna pintana, että pinnan pisteiden vektorikokoelmana.
 * 
 * Myös valo-olosuhteet pitää tallentaa piirtämistä varten.
 *
 * @author lauimmon
 */
public class Maailma {
    private double[][] maasto;
    private double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
    private Vektori[][] kartta;
    private Kolmio[] kolmiomaasto;
    private RGB[][] varit;
    private Vektori aurinko = new Vektori(3.6, 3.9, 0.6);
    private double[][] varjot;
    
    /**
     * Luodaan korkeusmatriisin 'ruudukko' avulla maastolle vektoriesitys 'kartta', kolmioitu pinta 'kolmiomaasto' sekä värit.
     * 
     * @param maasto jollain algoritmilla luotu maasto, jossa maaston korkeusarvot esitetty ruudukossa
     */

    public Maailma(double[][] maasto) {
        this.maasto = maasto;
        int k = this.maasto.length, l = this.maasto[0].length;
        this.kartta = new Vektori[k][l];
        this.kolmiomaasto = new Kolmio[(k - 1) * (l - 1) * 2];
        this.varit = new RGB[k][l];
        this.varjot = new double[k][l];
        
        asetaMinJaMax();
        
        asetaKarttaJaVarit();
        
        kolmioiMaasto();
        lisaaVarjot();
        lisaaValo();
    }
    
    private void asetaMinJaMax() {
        for (int i = 0; i < maasto.length; i++) {
            for (int j = 0; j < maasto[0].length; j++) {
                min = Math.min(maasto[i][j], min);
                max = Math.max(maasto[i][j], max);
            }
        }
    }

    private void asetaKarttaJaVarit() {
        for (int i = 0; i < kartta.length; i++) {
            for (int j = 0; j < kartta[0].length; j++) {
                double x = (double) i / (kartta.length - 1), z = (double) j / (kartta[0].length - 1);
                double korkeus = suhteellinenKorkeus(x, z);
                kartta[i][j] = new Vektori(x, korkeus * 0.7, z);
                varit[i][j] = getVari(x, z);
            }
        }
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
    
    private void lisaaValo() {
        double heijastus = .3;
        double diffuusiValo = 4.0;
        
        Vektori[][] normaalit = new Vektori[getPituus()][getLeveys()];
        
        for (int i = 0; i < getPituus(); i++) {
            for (int j = 0; j < getLeveys(); j++) {
                normaalit[i][j] = new Vektori(0.0, 0.0, 0.0);
            }
        }
        
        // lasketaan normaalit jokaiselle maaston pisteelle
        for (Kolmio k : kolmiomaasto) {
            Vektori v0 = kartta[k.getXKoordinaatti(0)][k.getYKoordinaatti(0)];
            Vektori v1 = kartta[k.getXKoordinaatti(1)][k.getYKoordinaatti(1)];
            Vektori v2 = kartta[k.getXKoordinaatti(2)][k.getYKoordinaatti(2)];
            Vektori normaali = v0.vahenna(v1).ristitulo(v2.vahenna(v1)).normalisoi();
            k.setNormaali(normaali);
            for (int j = 0; j < 3; j++) {
                normaalit[k.getXKoordinaatti(j)][k.getYKoordinaatti(j)] = normaalit[k.getXKoordinaatti(j)][k.getYKoordinaatti(j)].lisaa(k.getNormaali());
            }
        }
        
        // lasketaan värit jokaiselle pisteelle ja keskiarvot kolmioille
        for (Kolmio k : kolmiomaasto) {
            RGB keskiarvo = new RGB(0.0, 0.0, 0.0);
            for (int j = 0; j < 3; j++) {
                int x = k.getXKoordinaatti(j);
                int y = k.getYKoordinaatti(j);
                Vektori piste = kartta[x][y];
                RGB vari = varit[x][y];
                //RGB vari = new RGB(0.8, 0.7, 0.5);
                Vektori normaali = normaalit[x][y].normalisoi();
                Vektori valo = piste.vahenna(aurinko);
                double etaisyys = valo.pituus();
                double pistetulo = valo.normalisoi().pistetulo(normaali);
                double varjo = varjot[x][y];
                double valaistus = heijastus + diffuusiValo * ((pistetulo < 0.0) ? - pistetulo : 0.0) / etaisyys * varjo;
                vari = vari.skaalaa(valaistus);
                k.setVari(j, vari);
                keskiarvo = keskiarvo.summa(vari);
            }
            k.setVari(new Color(keskiarvo.skaalaa(1.0/3.0).toRGB()));
        }
    }
    
    private void lisaaVarjot() {
        for (int i = 0; i < getPituus(); i++) {
            for (int j = 0; j < getLeveys(); j++) {
                varjot[i][j] = 1.0;
                Vektori piste = kartta[i][j];
                Vektori valo = aurinko.vahenna(piste);
                double etaisyys = (getPituus()) * Math.sqrt(valo.getX() * valo.getX() + valo.getZ() * valo.getZ());
                for (int paikka = 1; paikka < etaisyys; paikka++) {
                    Vektori v = piste.lisaa(valo.skaalaa((double) paikka / etaisyys));
                    double sx = v.getX(), sy = v.getY(), sz = v.getZ();
                    if (sx < 0 || sz < 0 || sx > 1.0 || sz > 1.0) {
                        break;
                    }
                    double maanKorkeus = 0.7 * suhteellinenKorkeus(sx, sz);
                    if (maanKorkeus >= sy) {
                        varjot[i][j] = 0.0;
                        break;
                    }
                }
            }   
        }
    }
    
    
    private double suhteellinenKorkeus(double i, double j) {
        double korkeus = maasto[(int) (i * (maasto.length - 1))][(int) (j * (maasto.length-1))];
        return (korkeus - this.min) / (this.max - this.min);
    }
    
    private RGB sininen = new RGB (0.0, 0.0, 1.0);
    private RGB vihrea = new RGB (0.0, 1.0, 0.0);
    private RGB valkoinen = new RGB (1.0, 1.0, 1.0);
    
    
    private RGB getVari(double i, double j) {
      double a = suhteellinenKorkeus(i, j);
      if (a < .5)
        return sininen.summa(vihrea.erotus(sininen).skaalaa((a - 0.0) / 0.5));
      else
        return vihrea.summa(valkoinen.erotus(vihrea).skaalaa((a - 0.5) / 0.5));
    }

    public void setAurinko(Vektori aurinko) {
        this.aurinko = aurinko;
    }
    
    public double getKorkeus(int i, int j){
        return kartta[i][j].getY();
    }

    public Vektori[][] getMaasto() {
        return kartta;
    }

    public Kolmio[] getKolmiomaasto() {
        return kolmiomaasto;
    }
    
    public int getPituus() {
        return getMaasto().length;
    }
    
    public int getLeveys() {
        return getMaasto()[0].length;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public Vektori[][] getKartta() {
        return kartta;
    }

    public RGB[][] getVarit() {
        return varit;
    }

    public Vektori getAurinko() {
        return aurinko;
    }

    public double[][] getVarjot() {
        return varjot;
    }
    
    
}
