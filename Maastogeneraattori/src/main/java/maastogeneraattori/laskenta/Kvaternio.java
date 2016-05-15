
package maastogeneraattori.laskenta;

/**
 * Kvaternio on vektori, johon on lisätty skalaari.
 * Kvaternioita käytetään tässä vektoreiden kääntämiseen.
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
     * @return käänteiskvaternio
     */
    
    public Kvaternio kaanteinen() {
        double x2 = x * x;
        double y2 = y * y;
        double z2 = z * z;
        double w2 = w * w;
        double skaala = 1.0 / (x2 + y2 + z2 + w2);
        double xUusi = - x * skaala;
        double yUusi = - y * skaala;
        double zUusi = - z * skaala;
        double wUusi = w * skaala;
        return new Kvaternio(wUusi, xUusi, yUusi, zUusi);
    }
    
    /**
     * Laskee kahden kvaternion välisen kertolaskun.
     * 
     * @param k
     * @return kertolaskun tuloskvaternio
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

    /**
     * @return kvaternion w-komponetti, eli skalaariosa
     */

    public double getW() {
        return w;
    }

    /**
     * @return kvaternion x-komponetti
     */
    
    public double getX() {
        return x;
    }

    /**
     * @return kvaternion y-komponetti
     */
    
    public double getY() {
        return y;
    }

    /**
     * @return kvaternion z-komponetti
     */
    
    public double getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.w) ^ (Double.doubleToLongBits(this.w) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kvaternio other = (Kvaternio) obj;
        if (Double.doubleToLongBits(this.w) != Double.doubleToLongBits(other.w)) {
            return false;
        }
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        return true;
    }
    
    
}
