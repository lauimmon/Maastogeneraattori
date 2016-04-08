/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.laskenta;

/**
 * Vektoriluokan avulla voi tehdä laskutoimituksia vektoreilla.
 * 
 * @author lauimmon
 */
public class Vektori {
    private double x, y, z;

    public Vektori(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vektori(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    
    public Vektori lisaa(Vektori v) {
        return new Vektori(x + v.x, y + v.y, z + v.z);
    }
    
    public Vektori vahenna(Vektori v) {
        return new Vektori(x - v.x, y - v.y, z - v.z);
    }

    public double pistetulo(Vektori v) {
        return x * v.x + y * v.y + z * v.z;
    }
    
    public Vektori ristitulo(Vektori v) {
        return new Vektori(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }
    
    public double pituus(){
        return pistetulo(this);
    }
    
    public Vektori normalisoi() {
        return skaalaa(1.0 / Math.sqrt(this.pituus()));
    }

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
