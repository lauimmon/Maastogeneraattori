/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.laskenta;

import java.util.Comparator;

/**
 * Vektoriluokan avulla voi tehdä laskutoimituksia vektoreilla.
 * 
 * @author lauimmon
 */
public class Vektori {
    private double x, y, z;

    /**
     * Luo kolmiulotteisen vektorin, jonka pituus x-suunnassa on x, y-suunnassa y ja z-suunnassa z.
     * 
     * @param x
     * @param y
     * @param z 
     */
    
    public Vektori(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Luo kaksiulotteisen vektorin, eli kolmiuloittesen vektorin, jonka z-suuntainen pituus on 0.
     * 
     * @param x
     * @param y 
     */
    
    public Vektori(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    
    /**
     * Laskee kahden vektorin välisen yhteenlaskun.
     * 
     * @param v
     * @return vektori, joka on näiden vektorien summa
     */
    
    public Vektori lisaa(Vektori v) {
        return new Vektori(x + v.x, y + v.y, z + v.z);
    }
    
    /**
     * Laskee kahden vektorin erotuksen.
     * 
     * @param v
     * @return vektori, joka on näiden vektorien erotus
     */
    
    public Vektori vahenna(Vektori v) {
        return new Vektori(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Laskee kahden vektorin välisen pistetulon.
     * 
     * @param v
     * @return tuloksena skalaari
     */
    
    public double pistetulo(Vektori v) {
        return x * v.x + y * v.y + z * v.z;
    }
    
    /**
     * Laskee kahden vektorin välisen ristitulon.
     * 
     * @param v
     * @return vektori, joka on näiden vektoreiden ristitulon tulos
     */
    
    public Vektori ristitulo(Vektori v) {
        return new Vektori(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }
    
    /**
     * Laskee vektorin pituuden.
     * 
     * @return 
     */
    
    public double pituus(){
        return Math.sqrt(pistetulo(this));
    }
    
    /**
     * Luo tämän vektorin suuntaisen yksikkövektorin.
     * 
     * @return yksikkövektori
     */
    
    public Vektori normalisoi() {
        return skaalaa(1.0 / this.pituus());
    }

    /**
     * Luo tämän vektorin suuntaisen vektorin, jonka pituus on kerrottu parametrina annetulla luvulla
     * 
     * @param skaala pituuden kerroin
     * @return 
     */
    
    public Vektori skaalaa(double skaala) {
        return new Vektori(x * skaala, y * skaala, z * skaala);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
