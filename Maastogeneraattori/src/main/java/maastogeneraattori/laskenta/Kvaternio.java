/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maastogeneraattori.laskenta;

/**
 * Kvaternio on vektori, johon on lisätty skalaari.
 * Kvaternioita käytetään tässä vektoreiden kääntämiseen.
 * Niitä tarvitaankun tässä maastogeneraattorissa kun halutaan muuttaa näkymän suuntaa katsojan paikan lisäksi.
 * 
 * @author lauimmon
 */
public class Kvaternio {
    private double w, x, y, z;
    private Kvaternio kaanteinen;
    
    /**
     * Luo kvaternion, jonka vektoriosa x, y, z ja skalaariosa w
     * 
     * @param w
     * @param x
     * @param y
     * @param z 
     */

    public Kvaternio(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
        this.kaanteinen = null;
    }
    
    /**
     * Luo kvaterniolle käänteisen kvaternion.
     * 
     * @return 
     */
    
    public Kvaternio kaanteinen() {
        double skaala = 1.0 / (x * x + y * y + z * z + w * w);
        return new Kvaternio(w * skaala, - x * skaala, - y * skaala, - z * skaala);
    }
    
    /**
     * Laskee kahden kvaternion välisen kertolaskun.
     * 
     * @param k
     * @return 
     */
    
    public Kvaternio kerro(Kvaternio k) {
        double kx = k.x, ky = k.y, kz = k.z, kw = k.w;
        double nw = w * kw - x * kx - y * ky - z * kz;
        double nx = w * kx + x * kw + y * kz - z * ky;
        double ny = w * ky + y * kw + z * kx - x * kz;
        double nz = w * kz + z * kw + x * ky - y * kx;
        return new Kvaternio(nw, nx, ny, nz);
    }
    
    public Vektori kaanna(Vektori v) {
        if (kaanteinen == null) {
            kaanteinen = kaanteinen();
        }
        double iw = kaanteinen.w, ix = kaanteinen.x, iy = kaanteinen.y, iz = kaanteinen.z;
        double vx = v.getX(), vy = v.getY(), vz = v.getZ() ;
        double aw = - x * vx - y * vy - z * vz;
        double ax = w * vx + y * vz - z * vy;
        double ay = w * vy + z * vx - x * vz;
        double az = w * vz + x * vy - y * vx;
        double bx = aw * ix + ax * iw + ay * iz - az * iy;
        double by = aw * iy + ay * iw + az * ix - ax * iz;
        double bz = aw * iz + az * iw + ax * iy - ay * ix;
        return new Vektori(bx, by, bz);
    }
    
    public static Kvaternio uusiKaanto(double r, double x, double y, double z) {
        double d = Math.sqrt (x * x + y * y + z * z);
        double sin = Math.sin (r / 2.0);
        double cos = Math.cos (r / 2.0);
        double vali = sin / d;
        return new Kvaternio(cos, x * vali, y * vali, z * vali);
    }
}
